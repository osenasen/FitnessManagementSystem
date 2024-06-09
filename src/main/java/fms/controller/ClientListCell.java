package main.java.fms.controller;

import javafx.scene.control.ListCell;
import main.java.fms.model.ClientModel;

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
