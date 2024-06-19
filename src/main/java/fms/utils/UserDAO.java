package fms.utils;

import fms.model.UserModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public void saveUser(UserModel user) {
        String sql = "INSERT INTO User(username, password) VALUES(?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();
            System.out.println("User saved: " + user.getUsername());
        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public UserModel loadUser() {
        String sql = "SELECT * FROM User LIMIT 1";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("User loaded: " + rs.getString("username"));
                return new UserModel(rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error loading user: " + e.getMessage());
        }
        return new UserModel("defaultUser", "password");
    }

    public void updateUser(UserModel user) {
        String sql = "UPDATE User SET password = ? WHERE username = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getPassword());
            pstmt.setString(2, user.getUsername());
            pstmt.executeUpdate();
            System.out.println("User updated: " + user.getUsername());
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }
}
