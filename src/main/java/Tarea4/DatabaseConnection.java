package Tarea4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/registrousuarios";
    private static final String USERNAME = "root";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Error loading driver.");
            e.printStackTrace();
        }
    }

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, USERNAME, "");
            System.out.println("Connection successful.");
        } catch (SQLException e) {
            System.err.println("Connection error.");
            e.printStackTrace();
        }
        return connection;
    }
}
