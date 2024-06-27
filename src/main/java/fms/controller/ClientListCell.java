package fms.controller;

import fms.model.ClientModel;
import javafx.scene.control.ListCell;

/**
 * Benutzerdefinierte ListCell-Klasse zur Anzeige von Client-Daten.
 * Diese Klasse definiert, wie ClientModel-Objekte in einer ListView angezeigt werden.
 */
public class ClientListCell extends ListCell<ClientModel> {

  /**
   * Aktualisiert die Darstellung der Zelle.
   * Diese Methode wird aufgerufen, um den Inhalt und das Aussehen der Zelle zu aktualisieren.
   *
   * @param item Das ClientModel-Objekt, das in dieser Zelle dargestellt wird.
   * @param empty Ein boolescher Wert, der angibt, ob die Zelle leer ist oder nicht.
   */
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