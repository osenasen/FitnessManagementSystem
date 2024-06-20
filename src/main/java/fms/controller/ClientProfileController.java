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

      // If the fragment is NutritionFragment, pass the client ID to it
      if (fragmentPath.contains("NutritionFragment")) {
        NutritionFragmentController controller = loader.getController();
        controller.setClientId(client.getId());
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
