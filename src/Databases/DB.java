package Databases;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    Connection con;
    
    // Database configuration
    public static String url = "jdbc:mysql://localhost/IS3102";
    public static String dbdriver = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "password1";
    
    
    public DB() throws Exception {
        try {
            Class.forName(dbdriver);
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception ex) {
            System.out.println("Exception in BookDBAO: " + ex);
            throw new Exception("Couldn't open connection to database: " +
                    ex.getMessage());
        }
    }
    public Connection getConnection() {
    	return con;
    }
	
}
