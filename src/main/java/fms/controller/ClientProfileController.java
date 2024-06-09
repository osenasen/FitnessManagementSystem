package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import main.java.fms.model.ClientModel;

public class ClientProfileController {
  
  @FXML
  private TabPane tabPane;
  
  @FXML
  private AnchorPane dashboardPane;
  
  @FXML
  private AnchorPane generalInfoPane;
  
  @FXML
  private AnchorPane workoutPane;
  
  @FXML
  private AnchorPane nutritionPane;
  
  @FXML
  private Label nameLabel;
  
  @FXML
  private Label healthInfoLabel;
  
  @FXML
  private Label goalLabel;
  
  @FXML
  private Label workoutLabel;
  
  @FXML
  private Label dietLabel;
  
  @FXML
  private Label generalInfoLabel;
  
  @FXML
  private Label workoutDetailsLabel;
  
  @FXML
  private Label dietDetailsLabel;
  
  private ClientModel client;
  
  public void setClient(ClientModel client) {
    this.client = client;
    loadClientData();
  }
  
  private void loadClientData() {
    nameLabel.setText(client.getName());
    healthInfoLabel.setText(client.getHealthInfo());
    goalLabel.setText(client.getGoal());
    workoutLabel.setText(client.getWorkout());
    dietLabel.setText(client.getDiet());
    
    generalInfoLabel.setText("Health: " + client.getHealthInfo() + "\nGoal: " + client.getGoal());
    workoutDetailsLabel.setText(client.getWorkout());
    dietDetailsLabel.setText(client.getDiet());
  }
}
