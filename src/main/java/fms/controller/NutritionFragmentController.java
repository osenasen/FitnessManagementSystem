package fms.controller;

import fms.model.RecipeModel;
import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

public class NutritionFragmentController {
    @FXML
    private GridPane gridPane;

    private int clientId;

    @FXML
    public void initialize() {
        // Any other initializations you need
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
        loadRecipes();
    }

    private void loadRecipes() {
        List<Integer> clientRecipeIds = DataManager.getClientRecipeIds(clientId);
        List<RecipeModel> allRecipes = DataManager.loadRecipes();

        List<RecipeModel> clientRecipes = allRecipes.stream()
                .filter(recipe -> clientRecipeIds.contains(recipe.getId()))
                .collect(Collectors.toList());

        displayRecipes(clientRecipes);
    }

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
                // Handle the error (e.g., show an alert to the user)
            }
        }
    }

    @FXML
    public void handleOpenAddRecipeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddRecipeView.fxml"));
            Parent root = loader.load();
            
            AddRecipeController controller = loader.getController();
            controller.setClientId(this.clientId);
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Recipes");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            
            loadRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void refreshView() {
        loadRecipes();
    }
}