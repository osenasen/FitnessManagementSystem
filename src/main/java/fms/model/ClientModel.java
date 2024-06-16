package fms.model;

import fms.utils.ClientDAO;
import java.util.List;

public class ClientModel {
  private int id;
  private String name;
  private static ClientDAO clientDAO = new ClientDAO();

  public ClientModel(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() { return id; }
  public String getName() { return name; }

  public static void addClient(ClientModel client) {
    clientDAO.addClient(client);
  }

  public static List<ClientModel> getAllClients() {
    return clientDAO.getAllClients();
  }
}
