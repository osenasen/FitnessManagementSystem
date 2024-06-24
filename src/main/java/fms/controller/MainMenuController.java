package fms.controller;

import fms.model.ClientModel;
import fms.model.UserModel;
import fms.util.DataManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController {

  @FXML
  private Label usernameLabel;

  @FXML
  private ListView<ClientModel> clientListView;

  private ObservableList<ClientModel> clients = FXCollections.observableArrayList();
  private UserModel userModel;

  public void initialize() {
    userModel = DataManager.loadUser();
    usernameLabel.setText("Welcome, " + userModel.getUsername());

    loadClients();
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
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

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

  @FXML
  private void handleAddClient() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddClientView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Add Client");
      stage.setScene(new Scene(root));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();

      loadClients();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @FXML
  private void handleRemoveClient() {
    ClientModel selectedClient = clientListView.getSelectionModel().getSelectedItem();
    if (selectedClient != null) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this client?", ButtonType.YES, ButtonType.NO);
      alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.YES) {
          clients.remove(selectedClient);
          DataManager.saveClients(clients);
        }
      });
    }
  }

  private void loadClients() {
    clients.setAll(DataManager.loadClients());
    clientListView.setItems(clients);
    clientListView.setCellFactory(param -> new ClientListCell());
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