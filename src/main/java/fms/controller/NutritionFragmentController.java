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
import java.net.URL;
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
            VBox recipeBox = createRecipeBox(recipe);
            gridPane.add(recipeBox, column, row);
            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }
    }
    
    private VBox createRecipeBox(RecipeModel recipe) {
        try {
            // Adjust this path to match your project structure
            String fxmlPath = "/view/RecipeBox.fxml";
            URL location = getClass().getResource(fxmlPath);
            if (location == null) {
                throw new IllegalStateException("Cannot find FXML file: " + fxmlPath);
            }
            FXMLLoader loader = new FXMLLoader(location);
            VBox recipeBox = loader.load();
            RecipeBoxController controller = loader.getController();
            controller.setRecipe(recipe);
            return recipeBox;
        } catch (IOException e) {
            e.printStackTrace();
            // Log the error or show an alert to the user
            return new VBox(); // Return an empty VBox in case of error
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