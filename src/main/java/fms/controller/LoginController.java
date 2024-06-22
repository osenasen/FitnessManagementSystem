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

public class LoginController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label errorLabel;

  private UserModel userModel;

  @FXML
  public void initialize() {
    userModel = DataManager.loadUser();
  }

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
      errorLabel.setText("Invalid username or password.");
    }
  }

  @FXML
  private void handleDelete() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.isEmpty() && password.isEmpty()) {
      errorLabel.setText("Please fill the fields!");
    } else {
      usernameField.clear();
      passwordField.clear();
      errorLabel.setText("The input fields have been emptied!");
    }
  }

  private void openMainMenu() {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenuView.fxml"));
      Parent root = fxmlLoader.load();
      Stage stage = new Stage();
      stage.setTitle("Main Menu");
      stage.setScene(new Scene(root));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
