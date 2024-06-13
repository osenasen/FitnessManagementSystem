package fms.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label loginErrorLabel;

  public static String loggedInUser;
  public static String password = "defaultPassword"; // Default password

  @FXML
  private void handleLogin() {
    String username = usernameField.getText();
    String passwordInput = passwordField.getText();

    if ("user".equals(username) && password.equals(passwordInput)) {
      loggedInUser = username;

      try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainMenuView.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      loginErrorLabel.setText("Invalid username or password.");
    }
  }
}
