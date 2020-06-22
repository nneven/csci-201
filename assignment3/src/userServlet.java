import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	public static final String CREDENTIALS_STRING = "jdbc:mysql://google/assignment2?cloudSqlInstance=alien-device-255400:us-west1:cs201-test&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=password";
	static Connection connection = null;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page = (String) request.getParameter("page");
		System.out.println(page);
		
		java.sql.Connection conn = null;
		java.sql.ResultSet rs = null;
		java.sql.Statement st = null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(CREDENTIALS_STRING);
			st = conn.createStatement();
			HttpSession session = request.getSession();
			session.setAttribute("login", true);
			session.setAttribute("message", "");
			
			String name = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("SELECT * FROM User WHERE name = '" + name + "';");
			rs = st.executeQuery("SELECT * FROM User WHERE name = '" + name + "';");
			
			if (page.equals("Login.jsp")) {
				
				if (!rs.next()) {
					System.out.println("This user does not exist");
					session.setAttribute("login", false);
					session.setAttribute("message", "This user does not exist");
				} else {
					System.out.println("SELECT * FROM User WHERE password = '" + password +  "';");
					rs = st.executeQuery("SELECT * FROM User WHERE password = '" + password +  "';");
					if (!rs.next()) {
						System.out.println("Incorrect password");
						session.setAttribute("login", false);
						session.setAttribute("message", "Incorrect password");
					}
				}
				
				if ((boolean) session.getAttribute("login")) {
					System.out.println("Login successful");
					session.setAttribute("message", null);
					session.setAttribute("login", true);
					session.setAttribute("user", name);
					request.getRequestDispatcher("HomePage.jsp").forward(request, response);
				} else {
					System.out.println("Login unsuccessful");
					session.setAttribute("login", false);
					request.getRequestDispatcher(page).forward(request, response);

				}
			} else {
		
				String cPassword = request.getParameter("cPassword");
				
				if (!rs.next()) {
					if (!password.equals(cPassword)) {
						System.out.println("Passwords do not match");
						session.setAttribute("message", "Passwords do not match");
						session.setAttribute("login", false);
						request.getRequestDispatcher(page).forward(request, response);
					} else {
						
						st.executeUpdate("INSERT into User (name, password)" + " VALUES ('"+ name + "' , '"+ password + "');");
						System.out.println("INSERT into User (name, password)" + " VALUES ('"+ name + "' , '"+ password + "');");
						System.out.println("Successfully created a new account!");
						session.setAttribute("message", null);
						session.setAttribute("login", true);
						session.setAttribute("user", name);
						request.getRequestDispatcher("HomePage.jsp").forward(request, response);
					}
				} else {
					System.out.println("This username is already taken");
					session.setAttribute("message", "This username is already taken");
					session.setAttribute("login", false);
					request.getRequestDispatcher(page).forward(request, response);
				}
				
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

