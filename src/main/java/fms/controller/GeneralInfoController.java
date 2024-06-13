package fms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GeneralInfoController {
    
    @FXML
    private Label generalInfoLabel;
    
    public void initialize() {
        generalInfoLabel.setText("General Info");
    }
}
