package fms.controller;

import fms.model.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import java.io.IOException;

public class ClientProfileController {
  
  @FXML
  private BorderPane clientProfilePane;
  @FXML
  private Label clientNameLabel;
  @FXML
  private Label clientIdLabel;
  
  private ClientModel client;
  
  public void setClient(ClientModel client) {
    this.client = client;
    clientNameLabel.setText(client.getName());
    clientIdLabel.setText(String.valueOf(client.getId()));
    showDashboardView(); // Load dashboard by default
  }
  
  @FXML
  private void showDashboardView() {
    loadFragment("/view/DashboardFragment.fxml");
  }
  
  @FXML
  private void showGeneralInfoView() {
    loadFragment("/view/GeneralInfoFragment.fxml");
  }
  
  @FXML
  private void showNutritionView() {
    loadFragment("/view/NutritionFragment.fxml");
  }
  
  @FXML
  private void showExerciseView() {
    loadFragment("/view/ExerciseFragment.fxml");
  }
  
  private void loadFragment(String fragmentPath) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fragmentPath));
      Parent fragment = loader.load();
      clientProfilePane.setCenter(fragment);
      
      Object controller = loader.getController();
      if (controller instanceof NutritionFragmentController) {
        ((NutritionFragmentController) controller).setClientId(client.getId());
      }
      // Add similar conditions for other fragment controllers if needed
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
