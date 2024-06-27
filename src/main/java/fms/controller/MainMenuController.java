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

/**
 * Controller-Klasse für das Hauptmenü.
 * Diese Klasse behandelt die Navigation und Aktionen innerhalb der Hauptmenü-Ansicht.
 */
public class MainMenuController {

  @FXML
  private Label usernameLabel;

  @FXML
  private ListView<ClientModel> clientListView;

  private ObservableList<ClientModel> clients = FXCollections.observableArrayList();
  private UserModel userModel;

  /**
   * Initialisiert die Controller-Klasse.
   * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
   * Sie lädt den aktuellen Benutzer und initialisiert die Liste der Clients.
   */
  public void initialize() {
    userModel = DataManager.loadUser();
    usernameLabel.setText("Willkommen, " + userModel.getUsername());

    loadClients();
  }

  /**
   * Behandelt die Auswahl eines Clients in der Liste.
   * Wenn ein Client doppelt angeklickt wird, wird das Client-Profil geladen.
   *
   * @param event Das MouseEvent, das ausgelöst wurde.
   */
  @FXML
  public void handleClientSelection(MouseEvent event) {
    if (event.getClickCount() == 2) {
      ClientModel selectedClient = clientListView.getSelectionModel().getSelectedItem();
      if (selectedClient != null) {
        loadClientProfile(selectedClient);
      }
    }
  }

  /**
   * Behandelt den Klick auf die "Passwort ändern" Schaltfläche.
   * Öffnet ein Modalfenster zum Ändern des Passworts.
   */
  @FXML
  private void handleChangePassword() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ChangePasswordView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Passwort ändern");
      stage.setScene(new Scene(root));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Behandelt den Klick auf die "Abmelden" Schaltfläche.
   * Öffnet die Login-Ansicht und schließt das aktuelle Hauptmenü-Fenster.
   */
  @FXML
  private void handleLogout() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Anmelden");
      stage.setScene(new Scene(root));
      stage.show();

      // Schließt das aktuelle Hauptmenü-Fenster
      Stage currentStage = (Stage) usernameLabel.getScene().getWindow();
      currentStage.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Behandelt den Klick auf die "Client hinzufügen" Schaltfläche.
   * Öffnet ein Modalfenster zum Hinzufügen eines neuen Clients.
   * Aktualisiert die Liste der Clients nach dem Hinzufügen.
   */
  @FXML
  private void handleAddClient() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddClientView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Client hinzufügen");
      stage.setScene(new Scene(root));
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.showAndWait();

      loadClients();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Behandelt den Klick auf die "Client entfernen" Schaltfläche.
   * Zeigt eine Bestätigungsdialog an und entfernt den ausgewählten Client aus der Liste.
   * Speichert die aktualisierte Liste der Clients im DataManager.
   */
  @FXML
  private void handleRemoveClient() {
    ClientModel selectedClient = clientListView.getSelectionModel().getSelectedItem();
    if (selectedClient != null) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Möchten Sie diesen Client wirklich löschen?", ButtonType.YES, ButtonType.NO);
      alert.showAndWait().ifPresent(response -> {
        if (response == ButtonType.YES) {
          clients.remove(selectedClient);
          DataManager.saveClients(clients);
        }
      });
    }
  }

  /**
   * Lädt die Liste der Clients aus dem DataManager und zeigt sie in der ListView an.
   * Verwendet die ClientListCell zur Darstellung der Client-Daten.
   */
  private void loadClients() {
    clients.setAll(DataManager.loadClients());
    clientListView.setItems(clients);
    clientListView.setCellFactory(param -> new ClientListCell());
  }

  /**
   * Öffnet das Client-Profil für den ausgewählten Client.
   * Lädt die ClientProfileView und übergibt das ClientModel an den Controller.
   *
   * @param client Das ClientModel des ausgewählten Clients.
   */
  private void loadClientProfile(ClientModel client) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientProfileView.fxml"));
      Parent root = loader.load();
      ClientProfileController controller = loader.getController();
      controller.setClient(client);

      Stage stage = new Stage();
      stage.setTitle("Client Profil");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}