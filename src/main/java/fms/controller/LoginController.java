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
import fms.utils.UserDAO;

public class LoginController {

  @FXML
  private TextField usernameField;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Label errorLabel;

  private UserDAO userDAO = new UserDAO();
  private UserModel userModel;

  @FXML
  public void initialize() {
    userModel = userDAO.loadUser();
  }


  @FXML
  private void handleLogin() {
    String username = usernameField.getText();
    String password = passwordField.getText();

    if (username.equals(userModel.getUsername()) && password.equals(userModel.getPassword())) {
      // Set the logged-in username
      userModel.setUsername(username);

      // Proceed to the main menu
      Stage stage = (Stage) usernameField.getScene().getWindow();
      stage.close();
      openMainMenu();
    } else {
      errorLabel.setText("Invalid username or password.");
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
