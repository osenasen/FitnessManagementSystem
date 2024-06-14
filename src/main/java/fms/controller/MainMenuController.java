package fms.controller;

import fms.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import fms.model.ClientModel;

public class MainMenuController {
  @FXML
  private Label usernameLabel;

  private UserModel userModel = UserModel.getInstance();

  @FXML
  private ListView<ClientModel> clientListView;

  private ObservableList<ClientModel> clients = FXCollections.observableArrayList();

  public void initialize() {
    usernameLabel.setText("Welcome, " + userModel.getUsername());

    // Add an example client
    clients.add(new ClientModel(1, "John Doe", "Healthy", "Lose Weight", "Cardio", "Balanced Diet"));
    clientListView.setItems(clients);
    clientListView.setCellFactory(param -> new ClientListCell());
  }

  @FXML
  public void handleClientSelection(MouseEvent event) {
    if (event.getClickCount() == 2) {
      ClientModel selectedClient = clientListView.getSelectionModel().getSelectedItem();
      if (selectedClient != null) {
        loadClientProfile(selectedClient);
      }
    }
  }

  @FXML
  private void handleChangePassword() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangePasswordView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Change Password");
      stage.setScene(new Scene(root));
      stage.initModality(Modality.APPLICATION_MODAL); // Makes the pop-up modal
      stage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Method for logging out
  @FXML
  private void handleLogout() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Login");
      stage.setScene(new Scene(root));
      stage.show();

      // Close the current main menu window
      Stage currentStage = (Stage) usernameLabel.getScene().getWindow();
      currentStage.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void loadClientProfile(ClientModel client) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientProfileView.fxml"));
      Parent root = loader.load();
      ClientProfileController controller = loader.getController();
      controller.setClient(client);

      Stage stage = new Stage();
      stage.setTitle("Client Profile");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
