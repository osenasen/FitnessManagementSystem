package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import fms.model.UserModel;
import fms.util.DataManager;

/**
 * Controller-Klasse zum Ändern des Passworts.
 * Diese Klasse behandelt die Eingabe und Validierung des Passwortwechsels über eine JavaFX-Benutzeroberfläche.
 */
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

    /**
     * Initialisiert die Controller-Klasse.
     * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
     */
    @FXML
    public void initialize() {
        userModel = DataManager.loadUser();
    }

    /**
     * Behandelt die Aktion des Passwortwechsels.
     * Diese Methode validiert die eingegebenen Passwörter und aktualisiert das Passwort des Benutzers, wenn alle Prüfungen erfolgreich sind.
     */
    @FXML
    private void handleChangePassword() {
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!oldPassword.equals(userModel.getPassword())) {
            errorLabel.setText("Altes Passwort ist falsch.");
        } else if (!newPassword.equals(confirmPassword)) {
            errorLabel.setText("Die neuen Passwörter stimmen nicht überein.");
        } else if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorLabel.setText("Passwörter dürfen nicht leer sein.");
        } else {
            userModel.setPassword(newPassword);
            DataManager.saveUser(userModel);
            Stage stage = (Stage) newPasswordField.getScene().getWindow();
            stage.close();
        }
    }
}