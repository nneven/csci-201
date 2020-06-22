import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class profileServlet
 */
@WebServlet("/profileServlet")
public class profileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final String CREDENTIALS_STRING = "jdbc:mysql://google/assignment2?cloudSqlInstance=alien-device-255400:us-west1:cs201-test&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=password";
	static Connection connection = null;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			/*****************************/
			// Replace your API key here after removing the brackets---No use though
			String apiKey = ""; 
			String user = (String) session.getAttribute("user");
			
			java.sql.Connection conn = null;
			java.sql.ResultSet rs = null;
			java.sql.Statement st = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(CREDENTIALS_STRING);
				st = conn.createStatement();
				PrintWriter out = response.getWriter();
				System.out.println("SELECT * FROM Book WHERE user = '" + user + "';");
				rs = st.executeQuery("SELECT * FROM Book WHERE user = '" + user + "';");
				int size = 0;
				while(rs.next()) {
				    size++;
				}
				SearchResult results = new SearchResult();
				results.items = new Item[size];
				GsonBuilder builder = new GsonBuilder();
				builder.setPrettyPrinting();
				Gson gson = builder.create();
				rs = st.executeQuery("SELECT * FROM Book WHERE user = '" + user + "';");
				for (int i = 0; i < size; i++) {
					rs.next();
					String myUrl = "https://www.googleapis.com/books/v1/volumes/" + rs.getString("id");
					String jsonString = createJsonStringFromURL(myUrl);
					results.items[i] = gson.fromJson(jsonString, Item.class);
				}
				String json = gson.toJson(results);
				System.out.println(json);
				session.setAttribute("login", true);
				request.setAttribute("data", json);
				session.setAttribute("data", json);
				request.setAttribute("profile", json);
				session.setAttribute("profile", json);
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String createJsonStringFromURL(String desiredUrl) throws Exception {
		URL url = null;
		BufferedReader reader = null;
		StringBuilder stringBuilder;
		
		try {
			url = new URL(desiredUrl);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(15*1000);
		    connection.connect();
		    
		    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		    stringBuilder = new StringBuilder();
		    
		    String line = null;
		    while ((line = reader.readLine()) != null)
		    {
		    	stringBuilder.append(line + "\n");
		    }
			
		    return stringBuilder.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			//close your reader!
			reader.close();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		if (false) {
			session.setAttribute("message", "Not logged in");
			out.write("Favorite");
		} else {
			session.setAttribute("message", null);
			// TODO Auto-generated method stub
			java.sql.Connection conn = null;
			java.sql.ResultSet rs = null;
			java.sql.Statement st = null;
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(CREDENTIALS_STRING);
				st = conn.createStatement();
				String id = request.getParameter("id");
				String user = request.getParameter("user");
				String req = request.getParameter("request");
				System.out.println("SELECT * FROM Book WHERE id = '" + id + "' AND user = '" + user + "';");
				rs = st.executeQuery("SELECT * FROM Book WHERE id = '" + id + "' AND user = '" + user + "';");
				if (req.equals("Find")) {
					if (!rs.next()) {
						out.write("Favorite");
					} else {
						out.write("Remove");
					}
				} else if (req.equals("Favorite")) {
					st.executeUpdate("INSERT into Book (id, user)" + " VALUES ('"+ id + "' , '"+ user + "');");
					System.out.println("INSERT into Book (id, user)" + " VALUES ('"+ id + "' , '"+ user + "');");
					out.write("Remove");
				} else {
					st.executeUpdate("DELETE FROM Book WHERE id = '" + id + "' AND user = '" + user + "';");
					System.out.println("DELETE FROM Book WHERE id = '" + id + "' AND user = '" + user + "';");
					out.write("Favorite");
				}
				
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
