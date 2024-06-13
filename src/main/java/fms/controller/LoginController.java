package fms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
  
  @FXML
  private TextField usernameField;
  
  @FXML
  private PasswordField passwordField;
  
  @FXML
  public void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();
    if (authenticate(username, password)) {
      loadMainMenu();
    } else {
      showAlert("Login Failed", "Invalid username or password.");
    }
  }

  @FXML
  private void handleDelete() {
    // Clear the username and password fields
    usernameField.clear();
    passwordField.clear();
    System.out.println("Delete Button clicked");
  }
  
  private boolean authenticate(String username, String password) {
    // Placeholder for real authentication
    return "admin".equals(username) && "admin".equals(password);
  }
  
  private void showAlert(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
  
  private void loadMainMenu() {
    try {
      Stage stage = (Stage) usernameField.getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenuView.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
