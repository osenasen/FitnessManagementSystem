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

import java.util.List;

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
        List<RecipeModel> clientRecipes = getClientRecipes();
        gridPane.getChildren().clear();
        int column = 0;
        int row = 1;

        for (RecipeModel recipe : clientRecipes) {
            VBox recipeBox = createRecipeBox(recipe);
            gridPane.add(recipeBox, column++, row);

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
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/images/" + recipe.getImagePath())));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        Text proteins = new Text("Proteins: " + recipe.getProteins());
        Text carbs = new Text("Carbs: " + recipe.getCarbs());
        Text calories = new Text("Calories: " + recipe.getCalories());
        Text link = new Text("Recipe: " + recipe.getLinkPlaceholder());

        vbox.getChildren().addAll(name, imageView, proteins, carbs, calories, link);
        return vbox;
    }

    @FXML
    private void handleOpenAddRecipeView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddRecipeView.fxml"));
            Parent root = loader.load();
            AddRecipeController controller = loader.getController();
            controller.setClientId(clientId);

            Stage stage = new Stage();
            stage.setTitle("Add Recipe");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // Reload recipes after adding
            loadRecipes();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
