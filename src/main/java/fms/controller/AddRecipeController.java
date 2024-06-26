package fms.controller;

import fms.model.RecipeModel;
import fms.util.DataManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
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
        // Setup for select column
        selectColumn.setCellValueFactory(cellData -> cellData.getValue().selectedProperty());
        selectColumn.setCellFactory(column -> new TableCell<RecipeModel, Boolean>() {
            private final ToggleButton toggleButton = new ToggleButton();
            
            {
                toggleButton.getStyleClass().add("recipe-toggle");
                toggleButton.setOnAction(event -> {
                    RecipeModel recipe = getTableView().getItems().get(getIndex());
                    recipe.setSelected(toggleButton.isSelected());
                });
            }
            
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    toggleButton.setSelected(item);
                    setGraphic(toggleButton);
                }
            }
        });
        
        // Setup for other columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("linkPlaceholder"));
        
        // Optionally, add cell factories for better formatting
        proteinsColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString() + " g");
                }
            }
        });
        
        carbsColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString() + " g");
                }
            }
        });
        
        caloriesColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString() + " kcal");
                }
            }
        });
        
        linkPlaceholderColumn.setCellFactory(column -> new TableCell<RecipeModel, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText("View Recipe");
                    setTextFill(javafx.scene.paint.Color.BLUE);
                    setUnderline(true);
                }
            }
        });
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