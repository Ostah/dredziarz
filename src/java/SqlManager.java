import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlManager {
	
	private final static SqlManager instance = new SqlManager();
	
	public static SqlManager getInstance() 
	{
            System.out.println("Tworze sobie nowa instancje");
            return instance;
        }
	
	public static String userName, password, host, dbname;
	static Connection conn = null;
	static Statement query = null;
	

	Connection connect() {
		
		userName = "c10_fuzzy";
		password = "brawojasiu1";
		host = "jdbc:mysql://sauron.cyberdeus.pl/";
		dbname = "c10_fuzzy";

		try {
			
			conn = DriverManager.getConnection(host + dbname, userName, password);
			return conn;
		}

		catch (SQLException e) {
			
			System.out.println("Error bazy: "+ e.getMessage());
			return null;
		}
	}
	
	public ResultSet getSpecificData (String what) {
		ResultSet result = null;
		
		try {
			query = conn.createStatement();
			result = query.executeQuery(what);
                        System.out.println(what);
		} catch (SQLException ex) {
			System.out.println("catch");		
		}
		return result;
	}
}
