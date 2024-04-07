import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public Connection getDBconnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/company";

		return DriverManager.getConnection(url, "root", "5.Exercise!");
	}
}
