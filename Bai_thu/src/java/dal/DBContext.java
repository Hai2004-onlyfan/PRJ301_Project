package dal;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    private static final String URL = "jdbc:sqlserver://HAI99\\SQLEXPRESS:1433;databaseName=Project_Prj;trustServerCertificate=true;";
    private static final String USER = "sa"; 
    private static final String PASSWORD = "123"; 

    public Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
