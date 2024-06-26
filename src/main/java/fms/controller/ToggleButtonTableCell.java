package fms.controller;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.TableCell;
import fms.model.RecipeModel;
import javafx.scene.paint.Color;

public class ToggleButtonTableCell<S> extends TableCell<S, Boolean> {
  private ToggleButton toggleButton;

  public ToggleButtonTableCell() {
    toggleButton = new ToggleButton();
    toggleButton.setOnAction(event -> {
      if (getTableRow() != null && getTableRow().getItem() != null) {
        RecipeModel recipe = (RecipeModel) getTableRow().getItem();
        recipe.setSelected(toggleButton.isSelected());
        updateButtonStyle();
      }
    });

    // Set initial style
    updateButtonStyle();
  }

  private void updateButtonStyle() {
    if (toggleButton.isSelected()) {
      toggleButton.setStyle("-fx-background-color: #90EE90; -fx-text-fill: white;"); // Light green
    } else {
      toggleButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white;"); // Light red
    }
  }

  @Override
  protected void updateItem(Boolean item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      toggleButton.setSelected(item);
      updateButtonStyle();
      setGraphic(toggleButton);
    }
  }
}