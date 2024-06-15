package fms.utils;

import fms.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    // Method to save a user
    public void saveUser(UserModel user) {
        String sql = "INSERT INTO User(username, password) VALUES(?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseManager.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            System.out.println("User saved: " + user.getUsername());
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, null);
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

    // Method to update a user
    public void updateUser(UserModel user) {
        String sql = "UPDATE User SET password = ? WHERE username = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseManager.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
            System.out.println("User updated: " + user.getUsername());
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, null);
        }
    }

    // Method to check if the User table is empty and insert default user
    public void ensureDefaultUser() {
        String sql = "SELECT COUNT(*) FROM User";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseManager.connect();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                saveUser(new UserModel("defaultUser", "password"));
                System.out.println("Inserted default user.");
            } else {
                System.out.println("User table already contains data.");
            }
        } catch (SQLException e) {
            System.out.println("Error ensuring default user: " + e.getMessage());
        } finally {
            closeResources(conn, pstmt, rs);
        }
    }

    // Method to close all resources
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error closing resources: " + e.getMessage());
        }
    }
}
