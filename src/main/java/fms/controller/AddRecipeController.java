package fms.controller;

import fms.model.RecipeModel;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.util.List;

public class AddRecipeController {
    @FXML
    private TableView<RecipeModel> recipeTableView;
    @FXML
    private TableColumn<RecipeModel, Boolean> selectColumn;
    @FXML
    private TableColumn<RecipeModel, String> nameColumn;
    @FXML
    private TableColumn<RecipeModel, Integer> proteinsColumn;
    @FXML
    private TableColumn<RecipeModel, Integer> carbsColumn;
    @FXML
    private TableColumn<RecipeModel, Integer> caloriesColumn;
    @FXML
    private TableColumn<RecipeModel, String> linkPlaceholderColumn;

    private int clientId;

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @FXML
    public void initialize() {
        selectColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        selectColumn.setCellFactory(CheckBoxTableCell.forTableColumn(selectColumn));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("linkPlaceholder"));

        List<RecipeModel> recipes = RecipeModel.getAllRecipes();
        recipeTableView.getItems().setAll(recipes);

        // Mark the already added recipes as selected
        List<RecipeModel> clientRecipes = RecipeModel.getRecipesForClient(clientId);
        for (RecipeModel recipe : recipes) {
            for (RecipeModel clientRecipe : clientRecipes) {
                if (recipe.getId() == clientRecipe.getId()) {
                    recipe.setSelected(true);
                }
            }
        }
    }

    @FXML
    private void handleAddRecipes() {
        List<RecipeModel> recipes = recipeTableView.getItems();

        for (RecipeModel recipe : recipes) {
            if (recipe.isSelected()) {
                RecipeModel.addRecipeToClient(clientId, recipe.getId());
            } else {
                RecipeModel.removeRecipeFromClient(clientId, recipe.getId());
            }
        }

        // Close the Add Recipe window
        recipeTableView.getScene().getWindow().hide();
    }
}
