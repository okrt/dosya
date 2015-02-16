package dosya;
import java.sql.*;

public class DataBase {
	static String driverName = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/dosya?useUnicode=true&characterEncoding=UTF-8";
	static String user = "root";
	static String password = ""; 
public static Connection getConnection(){
	Connection con = null;
	try {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver ());
		con = DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		System.out.print("Veritabanina baglanirken bir hata oluştu!!");
		e.printStackTrace();
	}
	return con;
}
}
