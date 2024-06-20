package fms.controller;

import fms.model.RecipeModel;
import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.CheckBoxTableCell;

import java.util.List;
import java.util.stream.Collectors;

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

        List<RecipeModel> recipes = DataManager.loadRecipes();
        recipeTableView.getItems().setAll(recipes);

        List<RecipeModel> clientRecipes = getClientRecipes();
        for (RecipeModel recipe : recipes) {
            if (clientRecipes.stream().anyMatch(r -> r.getId() == recipe.getId())) {
                recipe.setSelected(true);
            }
        }
    }

    private List<RecipeModel> getClientRecipes() {
        List<ClientModel> clients = DataManager.loadClients();
        ClientModel client = clients.stream().filter(c -> c.getId() == clientId).findFirst().orElse(null);
        return client != null ? client.getRecipes() : List.of();
    }

    @FXML
    private void handleAddRecipes() {
        List<RecipeModel> selectedRecipes = recipeTableView.getItems().stream()
                .filter(RecipeModel::isSelected)
                .collect(Collectors.toList());

        List<ClientModel> clients = DataManager.loadClients();
        ClientModel client = clients.stream().filter(c -> c.getId() == clientId).findFirst().orElse(null);
        if (client != null) {
            client.setRecipes(selectedRecipes);
            DataManager.saveClients(clients);
        }

        recipeTableView.getScene().getWindow().hide();
    }
}
