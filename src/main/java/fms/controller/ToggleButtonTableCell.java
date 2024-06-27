package fms.controller;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.TableCell;
import fms.model.RecipeModel;

/**
 * Eine TableCell, die einen ToggleButton zur Auswahl eines Rezepts enthält.
 *
 * @param <S> Der Typ der TableView-Zeile.
 */
public class ToggleButtonTableCell<S> extends TableCell<S, Boolean> {
  private ToggleButton toggleButton;

  /**
   * Konstruktor für die ToggleButtonTableCell.
   * Initialisiert den ToggleButton und setzt einen ActionHandler für die Auswahländerung.
   */
  public ToggleButtonTableCell() {
    toggleButton = new ToggleButton();
    toggleButton.setOnAction(event -> {
      if (getTableRow() != null && getTableRow().getItem() != null) {
        RecipeModel recipe = (RecipeModel) getTableRow().getItem();
        recipe.setSelected(toggleButton.isSelected());
        updateButtonStyle();
      }
    });

    // Setzt den anfänglichen Stil des ToggleButtons
    updateButtonStyle();
  }

  /**
   * Aktualisiert den Stil des ToggleButtons basierend auf der Auswahl.
   */
  private void updateButtonStyle() {
    if (toggleButton.isSelected()) {
      toggleButton.setStyle("-fx-background-color: #90EE90; -fx-text-fill: white;"); // Hellgrün
    } else {
      toggleButton.setStyle("-fx-background-color: #FFB6C1; -fx-text-fill: white;"); // Hellrot
    }
  }

  /**
   * Aktualisiert den Zelleninhalt basierend auf dem übergebenen Wert.
   * Setzt den ToggleButton entsprechend dem Wert und aktualisiert den Stil.
   *
   * @param item  Der Wert der Zelle (Boolean).
   * @param empty Gibt an, ob die Zelle leer ist oder nicht.
   */
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