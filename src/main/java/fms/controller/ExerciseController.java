package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller-Klasse für die Trainingsansicht.
 * Diese Klasse behandelt die Initialisierung und Anzeige der Trainingsinformationen.
 */
public class ExerciseController {

  @FXML
  private Label exerciseLabel;

  /**
   * Initialisiert die Controller-Klasse.
   * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
   * Sie setzt den Text des Trainings-Labels auf eine voreingestellte Nachricht.
   */
  public void initialize() {
    exerciseLabel.setText("ExerciseModel Info");
  }
}