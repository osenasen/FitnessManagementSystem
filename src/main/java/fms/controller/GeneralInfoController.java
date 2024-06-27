package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller-Klasse für die allgemeine Informationsansicht.
 * Diese Klasse behandelt die Initialisierung und Anzeige allgemeiner Informationen.
 */
public class GeneralInfoController {

    @FXML
    private Label generalInfoLabel;

    /**
     * Initialisiert die Controller-Klasse.
     * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
     * Sie setzt den Text des allgemeinen Informations-Labels auf eine voreingestellte Nachricht.
     */
    public void initialize() {
        generalInfoLabel.setText("General Info");
    }
}