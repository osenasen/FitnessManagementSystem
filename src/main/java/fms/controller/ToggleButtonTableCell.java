package fms.controller;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.TableCell;
import fms.model.RecipeModel;
import javafx.scene.paint.Color;

public class ToggleButtonTableCell<S> extends TableCell<S, Boolean> {
  private ToggleButton toggleButton;

  public ToggleButtonTableCell() {
    toggleButton = new ToggleButton();
    toggleButton.getStyleClass().add("recipe-toggle");
    toggleButton.setOnAction(event -> {
      if (getTableRow() != null && getTableRow().getItem() != null) {
        RecipeModel recipe = (RecipeModel) getTableRow().getItem();
        recipe.setSelected(toggleButton.isSelected());
      }
    });
  }

  @Override
  protected void updateItem(Boolean item, boolean empty) {
    super.updateItem(item, empty);
    if (empty) {
      setGraphic(null);
    } else {
      toggleButton.setSelected(item);
      setGraphic(toggleButton);
    }
  }
}