package fms.controller;

import fms.model.RecipeModel;
import fms.utils.ClientNutritionDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class NutritionFragmentController {
    @FXML
    private GridPane gridPane;

    private ClientNutritionDAO clientNutritionDAO = new ClientNutritionDAO();

    public void initialize(int clientId) {
        List<RecipeModel> recipes = clientNutritionDAO.getRecipesForClient(clientId);
        displayRecipes(recipes);
    }

    private void displayRecipes(List<RecipeModel> recipes) {
        int column = 0;
        int row = 1;
        for (RecipeModel recipe : recipes) {
            VBox vBox = createRecipeVBox(recipe);
            gridPane.add(vBox, column++, row);
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }

    private VBox createRecipeVBox(RecipeModel recipe) {
        VBox vBox = new VBox();
        Text name = new Text(recipe.getName());
        Image image;
        try {
            image = new Image(getClass().getResourceAsStream("/images/" + recipe.getImagePath()));
        } catch (NullPointerException e) {
            image = new Image(getClass().getResourceAsStream("/images/default.jpg")); // Use a default image if not found
        }
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200); // Set max width
        imageView.setPreserveRatio(true); // Maintain aspect ratio
        Text proteins = new Text("Proteins: " + recipe.getProteins());
        Text carbs = new Text("Carbs: " + recipe.getCarbs());
        Text calories = new Text("Calories: " + recipe.getCalories());

        vBox.getChildren().addAll(name, imageView, proteins, carbs, calories);
        return vBox;
    }


    @FXML
    private void handleOpenAddRecipeView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/AddRecipe.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Add Recipe");
            stage.showAndWait();
            // Reload the recipes after the AddRecipe window is closed
            initialize(getCurrentClientId()); // Replace with the actual method to get the current client ID
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int getCurrentClientId() {
        // Implement this method to return the current client ID
        return 1; // Placeholder
    }
}
