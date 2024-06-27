package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller-Klasse für das Dashboard.
 * Diese Klasse behandelt die Initialisierung und Anzeige der Dashboard-Informationen.
 */
public class DashboardController {

  @FXML
  private Label dashboardLabel;

  /**
   * Initialisiert die Controller-Klasse.
   * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
   * Sie setzt den Text des Dashboard-Labels auf eine voreingestellte Nachricht.
   */
  public void initialize() {
    dashboardLabel.setText("Dashboard Info");
  }
}