package fms.controller;

import fms.model.RecipeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Controller-Klasse für das Anzeigen eines einzelnen Rezeptes in einem RecipeBox.
 */
public class RecipeBoxController {

    @FXML private VBox recipeBox;

    @FXML private Label nameLabel;

    @FXML private ImageView recipeImage;

    @FXML private Label proteinsLabel;

    @FXML private Label carbsLabel;

    @FXML private Label caloriesLabel;

    @FXML private Hyperlink recipeLink;

    /**
     * Setzt die Informationen des übergebenen Rezeptmodells in die Oberflächenelemente der RecipeBox.
     *
     * @param recipe Das Rezeptmodell, dessen Daten angezeigt werden sollen.
     */
    public void setRecipe(RecipeModel recipe) {
        nameLabel.setText(recipe.getName());
        proteinsLabel.setText(String.valueOf(recipe.getProteins()));
        carbsLabel.setText(String.valueOf(recipe.getCarbs()));
        caloriesLabel.setText(String.valueOf(recipe.getCalories()));

        Image image = new Image(getClass().getResourceAsStream(recipe.getImagePath()));
        recipeImage.setImage(image);

        // Erstellt einen Ausschnitt, um alle Ecken des Bildes abzurunden
        Rectangle clip = new Rectangle(recipeImage.getFitWidth(), recipeImage.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        recipeImage.setClip(clip);

        recipeLink.setOnAction(e -> openLink(recipe.getLinkPlaceholder()));
    }

    /**
     * Öffnet den angegebenen Link in einem Webbrowser.
     *
     * @param url Die URL des Links, der geöffnet werden soll.
     */
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Fehler behandeln (z.B. Benutzer benachrichtigen)
        }
    }
}