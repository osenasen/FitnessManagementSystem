package fms.controller;

import fms.model.RecipeModel;
import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class NutritionFragmentController {
    
    @FXML
    private GridPane gridPane;
    @FXML
    private Button addRecipeButton;
    
    private int clientId;
    
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
            if (column == 3) {
                column = 0;
                row++;
            }
        }
    }
    
    private List<RecipeModel> getClientRecipes() {
        List<ClientModel> clients = DataManager.loadClients();
        ClientModel client = clients.stream().filter(c -> c.getId() == clientId).findFirst().orElse(null);
        return client != null ? client.getRecipes() : List.of();
    }
    
    private VBox createRecipeBox(RecipeModel recipe) {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(5);
        
        Text name = new Text(recipe.getName());
        System.out.println("Loading image from path: " + recipe.getImagePath());
        ImageView imageView;
        try {
            String imagePath = recipe.getImagePath();
            if (imagePath != null && !imagePath.isEmpty()) {
                Image image = new Image(getClass().getResourceAsStream(imagePath));
                imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
            } else {
                // Use a default image or placeholder
                imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/default_recipe.png")));
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
            }
        } catch (Exception e) {
            // If there's an error loading the image, use a default or log the error
            System.err.println("Error loading image for recipe: " + recipe.getName());
            e.printStackTrace();
            imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/default_recipe.png")));
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
        }
        
        Text proteins = new Text("Proteins: " + recipe.getProteins());
        Text carbs = new Text("Carbs: " + recipe.getCarbs());
        Text calories = new Text("Calories: " + recipe.getCalories());
        Text link = new Text("Recipe: " + recipe.getLinkPlaceholder());
        
        vbox.getChildren().addAll(name, imageView, proteins, carbs, calories, link);
        return vbox;
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