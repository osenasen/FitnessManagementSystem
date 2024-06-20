package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ExerciseController {
  
  @FXML
  private Label exerciseLabel;
  
  public void initialize() {
    exerciseLabel.setText("ExerciseModel Info");
  }
}