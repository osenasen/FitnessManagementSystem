package controller;

import javafx.scene.control.ListCell;
import model.ClientModel;

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
