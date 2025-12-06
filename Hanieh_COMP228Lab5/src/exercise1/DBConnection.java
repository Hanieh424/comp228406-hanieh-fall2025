package exercise1;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {


    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String USER = "SYSTEM";       //  username
    private static final String PASS = "Hsadegh04*"; //  password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("Connected successfully!");
            try { conn.close(); } catch(Exception e){ e.printStackTrace(); }
        } else {
            System.out.println("Connection failed!");
        }
    }
}
