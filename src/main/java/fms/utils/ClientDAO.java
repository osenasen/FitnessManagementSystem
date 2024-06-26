package fms.utils;

import fms.model.ClientModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    public void addClient(ClientModel client) {
        String sql = "INSERT INTO Client(name) VALUES(?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, client.getName());
            pstmt.executeUpdate();
            System.out.println("Client added: " + client.getName());
        } catch (SQLException e) {
            System.out.println("Error adding client: " + e.getMessage());
        }
    }

    public List<ClientModel> getAllClients() {
        List<ClientModel> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ClientModel client = new ClientModel(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving clients: " + e.getMessage());
        }
        return clients;
    }
}
