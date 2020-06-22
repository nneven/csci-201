package lab8;
import java.sql.*;
public class JDBC {

	public JDBC() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws SQLException {
		//Class.forName
		java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://google/201Lab?cloudSqlInstance=alien-device-255400:us-west1:cs201-test&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=password");
		java.sql.Statement st = conn.createStatement();
		st.executeUpdate("Insert into Stats " + " VALUES ( , , , ) ");
		
		
	}
	
	public void getData() {
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("");
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * from PageVisited");
			while(rs.next()) {
				String uIP = rs.getString("uIP");
				String uPort = rs.getString("uPort");
				String page = rs.getString("page");
				int visits = rs.getInt("visits");
				System.out.println(uIP + uPort + page + visits);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
	}
	
	public void increment() {
		Connection conn = null;
		Statement st= null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("");
			st = conn.createStatement();
			rs = st.executeQuery("");
			while(rs.next()) {
				
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println(sqle.getMessage());
			}
		}
	}

}
