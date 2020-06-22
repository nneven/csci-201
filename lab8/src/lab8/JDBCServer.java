package lab8;

import java.io.IOException;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class JDBCServer
 */
@WebServlet("/JDBCServer")
public class JDBCServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CREDENTIALS_STRING = "jdbc:mysql://google/201Lab?cloudSqlInstance=alien-device-255400:us-west1:cs201-test&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=password";
	static Connection connection = null;
       
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ip = request.getRemoteAddr().toString();
		int port = request.getRemotePort();
		String requestPage = (String) request.getParameter("requestPage");
		String redirectPage = (String) request.getParameter("redirectPage");
		
		// default cause dogs!
		int page = 2;
		
		
		if (requestPage.equals("cat.jsp")) {
			page = 1;
		}
		else if (requestPage.equals("statistics.jsp")) {
			page = 1;
		}
		// sent from cat.jsp
		System.out.println(requestPage);
		
		
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		java.sql.Statement st = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CREDENTIALS_STRING);
			st = conn.createStatement();
			
			System.out.println("SELECT * FROM PageVisted WHERE PageID = " +page + " AND portnum = " + port +  ";");
			rs = st.executeQuery("SELECT * FROM PageVisited WHERE PageID = " +page + " AND portnum = " + port +  ";");
			if (!rs.next()) {
				System.out.println("This ip/port is a unique combination. Inserting a new entry with count 1");
				st.executeUpdate("Insert into PageVisited (PageID, IPAddress, portnum, count)" + 
						  " VALUES ("+ page + " , '"+  ip + "', "+ port + "," + 1 + ");");
			} else {
				// need the count from result of query
				int curr_count = rs.getInt("count");
				int next_count = curr_count + 1;
				System.out.println("UPDATE PageVisited SET count = " + next_count + " WHERE portnum = " +port + " AND IPAddress = '" + ip + "';");
				st.executeUpdate("UPDATE PageVisited SET count = " + next_count + " WHERE portnum = " +port + " AND IPAddress = '" + ip + "';");
			}
			
			if (redirectPage.equals("statistics.jsp")) {
				// we need to build a table
				String table = "<table><tr><th>User IP</th><th>User Port</th><th>Page</th><th># Visits</th></tr>";
				
				rs = st.executeQuery("SELECT * FROM PageVisited;");
				ArrayList<String> ips = new ArrayList<String>();
				ArrayList<Integer> ports = new ArrayList<Integer>();
				ArrayList<String> pages = new ArrayList<String>();
				ArrayList<Integer> counts = new ArrayList<Integer>();
				while (rs.next()) {
					table += "<tr>";
					table += "<td>" + rs.getString("IPAddress") + "</td>";
					table += "<td>" + rs.getInt("portnum") + "</td>";
					
					int pageID = rs.getInt("pageID");
					if (pageID == 2) {
						table += "<td>dog.jsp</td>";
					} else {
						table += "<td>cat.jsp</td>";
					}
					table += "<td>" + rs.getInt("count") + "</td>";
					table += "</tr>";
							
				}
				
				
				table += "</table>";
				HttpSession session = request.getSession();
				session.setAttribute("table", table);
				response.sendRedirect(redirectPage);
				
				
			} else {
				response.sendRedirect(redirectPage);
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
