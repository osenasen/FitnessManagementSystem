package fms.controller;

import fms.model.RecipeModel;
import fms.model.ClientModel;
import fms.util.DataManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectColumn.setCellFactory(column -> new CheckBoxTableCell<>());
        selectColumn.setEditable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("Proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("Carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("Calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("LinkPlaceholder"));

        recipeTableView.setEditable(true);

        loadRecipes();
    }

    private void loadRecipes() {
        List<RecipeModel> recipes = DataManager.loadRecipes();
        List<RecipeModel> clientRecipes = getClientRecipes();

        for (RecipeModel recipe : recipes) {
            recipe.setSelected(clientRecipes.stream().anyMatch(r -> r.getId() == recipe.getId()));
        }
        recipeTableView.setItems(FXCollections.observableArrayList(recipes));
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

        Stage stage = (Stage) recipeTableView.getScene().getWindow();
        stage.close();
    }
}

