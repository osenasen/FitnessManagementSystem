package fms.controller;

import fms.model.ClientModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * Controller-Klasse für das Client-Profil.
 * Diese Klasse behandelt die Anzeige und Navigation innerhalb des Client-Profils.
 */
public class ClientProfileController {

  @FXML
  private BorderPane clientProfilePane;

  @FXML
  private Label clientNameLabel;

  @FXML
  private Label clientIdLabel;

  private ClientModel client;

  /**
   * Setzt den Client und aktualisiert die Anzeige der Client-Informationen.
   *
   * @param client Das ClientModel-Objekt, das im Profil angezeigt werden soll.
   */
  public void setClient(ClientModel client) {
    this.client = client;
    clientNameLabel.setText(client.getName());
    clientIdLabel.setText(String.valueOf(client.getId()));
    showDashboardView();
  }

  /**
   * Zeigt die Dashboard-Ansicht an.
   * Diese Methode lädt das Dashboard-Fragment und zeigt es im Zentrum des BorderPane.
   */
  @FXML
  private void showDashboardView() {
    loadFragment("/view/DashboardFragment.fxml");
  }

  /**
   * Zeigt die allgemeine Informationsansicht an.
   * Diese Methode lädt das GeneralInfo-Fragment und zeigt es im Zentrum des BorderPane.
   */
  @FXML
  private void showGeneralInfoView() {
    loadFragment("/view/GeneralInfoFragment.fxml");
  }

  /**
   * Zeigt die Ernährungsansicht an.
   * Diese Methode lädt das Nutrition-Fragment und zeigt es im Zentrum des BorderPane.
   */
  @FXML
  private void showNutritionView() {
    loadFragment("/view/NutritionFragment.fxml");
  }

  /**
   * Zeigt die Trainingsansicht an.
   * Diese Methode lädt das Exercise-Fragment und zeigt es im Zentrum des BorderPane.
   */
  @FXML
  private void showExerciseView() {
    loadFragment("/view/ExerciseFragment.fxml");
  }

  /**
   * Lädt ein Fragment und zeigt es im Zentrum des BorderPane an.
   * Diese Methode lädt die angegebene FXML-Datei und setzt den entsprechenden Controller.
   *
   * @param fragmentPath Der Pfad zur FXML-Datei des zu ladenden Fragments.
   */
  private void loadFragment(String fragmentPath) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(fragmentPath));
      Parent fragment = loader.load();
      clientProfilePane.setCenter(fragment);

      Object controller = loader.getController();
      if (controller instanceof NutritionFragmentController) {
        ((NutritionFragmentController) controller).setClientId(client.getId());
      }
      // ... handle other fragment types

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}