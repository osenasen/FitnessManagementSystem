package fms.controller;
import fms.model.RecipeModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/RecipeBox.fxml"));
            VBox recipeBox = loader.load();
            RecipeBoxController controller = loader.getController();
            controller.setRecipe(recipe);

            // Add tooltip to the recipe name label
            Label nameLabel = (Label) recipeBox.lookup("#nameLabel");
            if (nameLabel != null) {
                nameLabel.setTooltip(new Tooltip(recipe.getName()));
            }

            return recipeBox;
        } catch (IOException e) {
            e.printStackTrace();
            return new VBox();
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