package fms.controller;

import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

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

        List<ClientModel> clients = DataManager.loadClients();
        int newId = clients.isEmpty() ? 1 : clients.get(clients.size() - 1).getId() + 1;
        ClientModel newClient = new ClientModel(newId, firstName + " " + lastName, null, null);
        clients.add(newClient);
        DataManager.saveClients(clients);

        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleCancel() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}


