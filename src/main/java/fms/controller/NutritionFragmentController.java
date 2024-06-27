package fms.controller;

import fms.model.RecipeModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller-Klasse für das Ernährungs-Fragment.
 * Diese Klasse verwaltet die Anzeige von Rezepten für einen bestimmten Client.
 */
public class NutritionFragmentController {
    @FXML
    private GridPane gridPane;

    private int clientId;

    /**
     * Initialisiert die Controller-Klasse.
     * Diese Methode wird automatisch nach dem Laden der FXML-Datei aufgerufen.
     */
    @FXML
    public void initialize() {
        // Weitere Initialisierungen hier
    }

    /**
     * Setzt die Client-ID und lädt die zugehörigen Rezepte.
     *
     * @param clientId Die ID des Clients, dessen Rezepte geladen werden sollen.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
        loadRecipes();
    }

    /**
     * Lädt die Rezepte für den aktuellen Client und zeigt sie an.
     */
    private void loadRecipes() {
        List<Integer> clientRecipeIds = DataManager.getClientRecipeIds(clientId);
        List<RecipeModel> allRecipes = DataManager.loadRecipes();

        List<RecipeModel> clientRecipes = allRecipes.stream()
                .filter(recipe -> clientRecipeIds.contains(recipe.getId()))
                .collect(Collectors.toList());

        displayRecipes(clientRecipes);
    }

    /**
     * Zeigt die übergebenen Rezepte in der GridPane an.
     *
     * @param recipes Die Liste der Rezepte, die angezeigt werden sollen.
     */
    private void displayRecipes(List<RecipeModel> recipes) {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;

        for (RecipeModel recipe : recipes) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecipeBox.fxml"));
                VBox recipeBox = loader.load();
                RecipeBoxController controller = loader.getController();
                controller.setRecipe(recipe);

                gridPane.add(recipeBox, column, row);
                column++;
                if (column == 4) {
                    column = 0;
                    row++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                // Fehler behandeln (z.B. Benutzer benachrichtigen)
            }
        }
    }

    /**
     * Behandelt das Öffnen der Ansicht zum Hinzufügen neuer Rezepte.
     * Öffnet ein Modalfenster zum Hinzufügen von Rezepten und aktualisiert die Ansicht danach.
     */
    @FXML
    public void handleOpenAddRecipeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddRecipeView.fxml"));
            Parent root = loader.load();

            AddRecipeController controller = loader.getController();
            controller.setClientId(this.clientId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Rezepte hinzufügen");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aktualisiert die Ansicht durch erneutes Laden der Rezepte.
     * Diese Methode kann aufgerufen werden, um die Ansicht manuell zu aktualisieren.
     */
    public void refreshView() {
        loadRecipes();
    }
}