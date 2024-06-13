package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField repeatNewPasswordField;

    @FXML
    private void handleChangePassword() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String repeatNewPassword = repeatNewPasswordField.getText();

        if (!LoginController.password.equals(currentPassword)) {
            // Current password is incorrect
            System.out.println("Current password is incorrect.");
            return;
        }

        if (!newPassword.equals(repeatNewPassword)) {
            // New passwords do not match
            System.out.println("New passwords do not match.");
            return;
        }

        // Change the password
        LoginController.password = newPassword;
        System.out.println("Password changed successfully.");

        // Close the window
        Stage stage = (Stage) currentPasswordField.getScene().getWindow();
        stage.close();
    }
}
