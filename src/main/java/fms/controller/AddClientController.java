package fms.controller;

import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;

/**
 * Controller-Klasse zum Hinzufügen eines neuen Clients.
 * Diese Klasse behandelt die Eingaben und Aktionen zum Hinzufügen eines Clients über eine JavaFX-Benutzeroberfläche.
 */
public class AddClientController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    /**
     * Behandelt die Aktion des Hinzufügens eines neuen Clients.
     * Diese Methode holt die Eingaben aus den Textfeldern, validiert sie,
     * erstellt ein neues ClientModel-Objekt und speichert es mit dem DataManager.
     * Wenn die Eingaben ungültig sind (leerer Vor- oder Nachname), kehrt die Methode frühzeitig zurück.
     */
    @FXML
    private void handleAddClient() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty()) {
            // Hier könnte eine Fehlermeldung an den Benutzer angezeigt werden
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

    /**
     * Behandelt die Aktion des Abbrechens des Client-Hinzufügens.
     * Diese Methode schließt das aktuelle Fenster, ohne Daten zu speichern.
     */
    @FXML
    private void handleCancel() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
}