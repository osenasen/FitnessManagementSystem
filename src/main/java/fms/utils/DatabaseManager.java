package fms.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:fitness.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            conn.createStatement().execute("PRAGMA busy_timeout = 5000"); // Set busy timeout to 5 seconds
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void initializeDatabase() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = connect();
            stmt = conn.createStatement();
            String createUserTable = "CREATE TABLE IF NOT EXISTS User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT NOT NULL," +
                    "password TEXT NOT NULL)";
            stmt.execute(createUserTable);
            System.out.println("User table created or already exists.");

            // Check if the User table is empty and insert default user
            String sql = "SELECT COUNT(*) FROM User";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next() && rs.getInt(1) == 0) {
                String insertDefaultUser = "INSERT INTO User(username, password) VALUES('defaultUser', 'password')";
                stmt.executeUpdate(insertDefaultUser);
                System.out.println("Inserted default user.");
            } else {
                System.out.println("User table already contains data.");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
            System.out.println("Resources closed successfully.");
        }
    }
}
