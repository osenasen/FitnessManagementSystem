package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import fms.model.UserModel;
import fms.util.DataManager;

public class ChangePasswordController {

    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label errorLabel;

    private UserModel userModel;

    @FXML
    public void initialize() {
        userModel = DataManager.loadUser();
    }

    @FXML
    private void handleChangePassword() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!oldPassword.equals(userModel.getPassword())) {
            errorLabel.setText("Old password is incorrect.");
        } else if (!newPassword.equals(confirmPassword)) {
            errorLabel.setText("New passwords do not match.");
        } else if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("Passwords cannot be empty.");
        } else {
            userModel.setPassword(newPassword);
            DataManager.saveUser(userModel);
            Stage stage = (Stage) newPasswordField.getScene().getWindow();
            stage.close();
        }
    }
}