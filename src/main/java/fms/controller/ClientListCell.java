package fms.controller;

import fms.model.RecipeModel;
import javafx.scene.control.ListCell;
import fms.model.ClientModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.ToggleButton;

public class ClientListCell extends ListCell<ClientModel> {
  @Override
  protected void updateItem(ClientModel item, boolean empty) {
    super.updateItem(item, empty);
    if (empty || item == null) {
      setText(null);
    } else {
      setText(item.getName() + " (ID: " + item.getId() + ")");
    }
  }
}
