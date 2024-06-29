package fms.controller;

import fms.model.RecipeModel;
import fms.util.DataManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("linkPlaceholder"));

        // Adjust column widths
        selectColumn.setPrefWidth(60);
        nameColumn.setPrefWidth(200);
        proteinsColumn.setPrefWidth(80);
        carbsColumn.setPrefWidth(80);
        caloriesColumn.setPrefWidth(80);
        linkPlaceholderColumn.setPrefWidth(100);

        recipeTableView.setTableMenuButtonVisible(false);


        nameColumn.setCellFactory(column -> {
            TableCell<RecipeModel, String> cell = new TableCell<RecipeModel, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setTooltip(null);
                    } else {
                        setText(item);
                        setTooltip(new Tooltip(item));
                    }
                }
            };
            return cell;
        });

        // Custom cell factories for formatting
        proteinsColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item + " g");
                }
                setAlignment(Pos.CENTER_RIGHT);
            }
        });

        carbsColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item + " g");
                }
                setAlignment(Pos.CENTER_RIGHT);
            }
        });

        caloriesColumn.setCellFactory(column -> new TableCell<RecipeModel, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item + " kcal");
                }
                setAlignment(Pos.CENTER_RIGHT);
            }
        });

        linkPlaceholderColumn.setCellFactory(column -> new TableCell<RecipeModel, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    Hyperlink link = new Hyperlink("View Recipe");
                    link.setOnAction(event -> {
                        // Add your link action here
                    });
                    setGraphic(link);
                }
                setAlignment(Pos.CENTER);
            }
        });

        // Make the table resizable
        recipeTableView.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
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