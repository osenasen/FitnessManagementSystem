package fms.controller;

import fms.model.RecipeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class RecipeBoxController {

    @FXML private VBox recipeBox;
    @FXML private Label nameLabel;
    @FXML private ImageView recipeImage;
    @FXML private Label proteinsLabel;
    @FXML private Label carbsLabel;
    @FXML private Label caloriesLabel;
    @FXML private Hyperlink recipeLink;

    public void setRecipe(RecipeModel recipe) {
        nameLabel.setText(recipe.getName());
        proteinsLabel.setText(recipe.getProteins() + " g");
        carbsLabel.setText(recipe.getCarbs() + " g");
        caloriesLabel.setText(recipe.getCalories() + " kcal");

        Image image = new Image(getClass().getResourceAsStream(recipe.getImagePath()));
        recipeImage.setImage(image);

        // Set a clip to apply rounded corners to the image
        Rectangle clip = new Rectangle(recipeImage.getFitWidth(), recipeImage.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        recipeImage.setClip(clip);

        // Ensure the image fills the ImageView
        recipeImage.setPreserveRatio(false);

        recipeLink.setOnAction(e -> openLink(recipe.getLinkPlaceholder()));
    }
    
    private void openLink(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            // Handle the error (e.g., show an alert to the user)
        }
    }
}