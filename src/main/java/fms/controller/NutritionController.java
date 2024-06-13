package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NutritionController {
  
  @FXML
  private Label nutritionLabel;
  
  public void initialize() {
    nutritionLabel.setText("Nutrition Info");
  }
}
