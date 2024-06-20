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
  private Label clientNameLabel;

  @FXML
  private Label clientIdLabel;

  private ClientModel client;

  public void setClient(ClientModel client) {
    this.client = client;
    displayClientData();
    showDashboardView();
  }

  private void displayClientData() {
    if (client != null) {
      clientNameLabel.setText(client.getName());
      clientIdLabel.setText(String.valueOf(client.getId()));
    }
  }

  @FXML
  private void showNutritionView() {
    loadView("/view/NutritionFragment.fxml");
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