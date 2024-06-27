package fms.controller;

import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddClientController {
    @FXML
    private TextField firstNameField;
    
    @FXML
    private TextField lastNameField;
    
    @FXML
    private void handleAddClient() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        
        if (firstName.isEmpty() || lastName.isEmpty()) {
            // You might want to show an error message to the user here
            return;
        }
        
        List<ClientModel> clients = DataManager.loadClients();
        int newId = clients.isEmpty() ? 1 : clients.stream().mapToInt(ClientModel::getId).max().orElse(0) + 1;
        
        ClientModel newClient = new ClientModel(newId, firstName + " " + lastName);
        newClient.setRecipeIds(new ArrayList<>());
        newClient.setExerciseIds(new ArrayList<>());
        
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