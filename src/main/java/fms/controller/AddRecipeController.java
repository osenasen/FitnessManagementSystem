package fms.controller;

import fms.model.RecipeModel;
import fms.util.DataManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        loadRecipes();
    }

    @FXML
    public void initialize() {
        String css = this.getClass().getResource("/css/styles.css").toExternalForm();
        recipeTableView.getStylesheets().add(css);

        setupTableColumns();
        recipeTableView.setEditable(true);
    }

    private void setupTableColumns() {
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectColumn.setCellFactory(column -> new ToggleButtonTableCell<>());
        selectColumn.setEditable(true);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("linkPlaceholder"));
    }

    private void loadRecipes() {
        List<RecipeModel> allRecipes = DataManager.loadRecipes();
        List<Integer> clientRecipeIds = DataManager.getClientRecipeIds(clientId);

        for (RecipeModel recipe : allRecipes) {
            recipe.setSelected(clientRecipeIds.contains(recipe.getId()));
        }

        recipeTableView.setItems(FXCollections.observableArrayList(allRecipes));
    }

    @FXML
    private void handleAddRecipes() {
        List<Integer> selectedRecipeIds = recipeTableView.getItems().stream()
                                              .filter(RecipeModel::isSelected)
                                              .map(RecipeModel::getId)
                                              .collect(Collectors.toList());
        
        DataManager.updateClientRecipes(clientId, selectedRecipeIds);
        
        Stage stage = (Stage) recipeTableView.getScene().getWindow();
        stage.close();
    }
}