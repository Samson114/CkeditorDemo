package Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author zyp
 * @Description 数据库连接公用类 数据库版本：mysql5.0
 * @created May 9, 2014 2:43:36 AM
 * @History 
 * @version v1.0
 */
public class DbConnection {

	private static String URL = "jdbc:mysql://localhost:3306/ckedit?unicode=true&charachterEncoding=utf8";
	private static String DRIVER = "com.mysql.jdbc.Driver";
	private static String USER = "root";
	private static String PWD = "root";

	public static void init(String Url, String Driver, String User, String Pwd) {
		URL = Url;
		DRIVER = Driver;
		USER = User;
		PWD = Pwd;
	}

	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeDB(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new DbConnection().getConn());
	}
}
