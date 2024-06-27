package fms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import fms.model.UserModel;
import fms.util.DataManager;

/**
 * Controller-Klasse für die Login-Ansicht.
 * Diese Klasse behandelt die Eingabe und Validierung der Anmeldedaten sowie die Navigation zum Hauptmenü.
 */
public class LoginController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label errorLabel;

  private UserModel userModel;

  /**
   * Initialisiert die Controller-Klasse.
   * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
   * Sie lädt die Benutzerdaten aus dem DataManager.
   */
  @FXML
  public void initialize() {
    userModel = DataManager.loadUser();
  }

  /**
   * Behandelt die Login-Aktion.
   * Diese Methode überprüft die eingegebenen Anmeldedaten und öffnet das Hauptmenü, wenn die Anmeldedaten korrekt sind.
   * Andernfalls wird eine Fehlermeldung angezeigt.
   */
  @FXML
  private void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword())) {
      userModel.setUsername(username);

      Stage stage = (Stage) usernameField.getScene().getWindow();
      stage.close();
      openMainMenu();
    } else {
      errorLabel.setText("Ungültiger Benutzername oder Passwort.");
    }
  }

  /**
   * Öffnet das Hauptmenü.
   * Diese Methode lädt die FXML-Datei für das Hauptmenü und zeigt es in einem neuen Fenster an.
   */
  private void openMainMenu() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenuView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Hauptmenü");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}