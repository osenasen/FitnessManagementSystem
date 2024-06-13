package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
  
  @FXML
  private Label dashboardLabel;
  
  public void initialize() {
    dashboardLabel.setText("Dashboard Info");
  }
}
