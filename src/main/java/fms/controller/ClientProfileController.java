package fms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import java.io.IOException;
import fms.model.ClientModel;

public class ClientProfileController {
  
  @FXML
  private Pane centerPane;
  
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
    displayClientData();
    showDashboardView();
  }
  
  private void displayClientData() {
    if (client != null) {
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
  
  @FXML
  private void showNutritionView() {
    loadView("/view/NutritionViewFragment.fxml");
  }
  
  @FXML
  private void showExerciseView() {
    loadView("/view/ExerciseViewFragment.fxml");
  }
  
  @FXML
  private void showDashboardView() {
    loadView("/view/DashboardViewFragment.fxml");
  }
  
  @FXML
  private void showGeneralInfoView() {
    loadView("/view/GeneralInfoViewFragment.fxml");
  }
  
  private void loadView(String fxmlFile) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
      Parent view = loader.load();
      centerPane.getChildren().clear();
      centerPane.getChildren().add(view);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
