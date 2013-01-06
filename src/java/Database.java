
import java.sql.*;

public class Database {

    private Connection connection;
    private static String databaseName = "c10_fuzzy";
    private static String databaseUsername = "c10_fuzzy";
    private static String databasePassword = "brawojasiu1";
    private static String driver = "com.mysql.jdbc.Driver";
    static Statement query = null;

    public Connection getConnection() {
        createConnection();
        return connection;
    }

    public void createConnection() {
        if (connection == null) {
            try {
                Class.forName(driver);
                String url = "jdbc:mysql://sauron.cyberdeus.pl/" + databaseName;
                connection = DriverManager.getConnection(
                        url, databaseUsername, databasePassword);
            } catch (Exception e) {
                e.printStackTrace();
                connection = null;
            }
        }
    }

    public ResultSet getSpecificData(String what) {
        ResultSet result = null;

        try {
            query = connection.createStatement();
            result = query.executeQuery(what);
            System.out.println(what);
        } catch (SQLException ex) {
            System.out.println("catch");
        }
        return result;
    }
}