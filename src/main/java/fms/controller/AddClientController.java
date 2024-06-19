package fms.controller;

import fms.model.ClientModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddClientController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private void handleAddClient() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            return;
        }

        ClientModel client = new ClientModel(0, firstName + " " + lastName);
        ClientModel.addClient(client);

        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}
