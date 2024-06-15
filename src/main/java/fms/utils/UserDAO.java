package fms.utils;

import fms.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    // Method to save a user with retry mechanism
    public void saveUser(UserModel user) {
        String sql = "INSERT INTO User(username, password) VALUES(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int retries = 5;
        while (retries > 0) {
            try {
                conn = DatabaseManager.connect();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getUsername());
                pstmt.setString(2, user.getPassword());
                pstmt.executeUpdate();
                System.out.println("User saved: " + user.getUsername());
                break; // Break if successful
            } catch (SQLException e) {
                System.out.println("Error saving user: " + e.getMessage());
                if (!e.getMessage().contains("SQLITE_BUSY")) {
                    break; // Break if error is not SQLITE_BUSY
                }
                retries--;
                try {
                    Thread.sleep(100); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                closeResources(conn, pstmt, null);
            }
        }
    }

    // Method to load a user
    public UserModel loadUser() {
        String sql = "SELECT * FROM User LIMIT 1";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseManager.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("User loaded: " + rs.getString("username"));
                return new UserModel(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return new UserModel("defaultUser", "password"); // Default user if none found
    }

    // Method to update a user with retry mechanism
    public void updateUser(UserModel user) {
        String sql = "UPDATE User SET password = ? WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        int retries = 5;
        while (retries > 0) {
            try {
                conn = DatabaseManager.connect();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, user.getPassword());
                pstmt.setString(2, user.getUsername());
                pstmt.executeUpdate();
                System.out.println("User updated: " + user.getUsername());
                break; // Break if successful
            } catch (SQLException e) {
                System.out.println("Error updating user: " + e.getMessage());
                if (!e.getMessage().contains("SQLITE_BUSY")) {
                    break; // Break if error is not SQLITE_BUSY
                }
                retries--;
                try {
                    Thread.sleep(100); // Wait before retrying
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            } finally {
                closeResources(conn, pstmt, null);
            }
        }
    }

    // Method to close all resources
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
            System.out.println("Resources closed successfully.");
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}
