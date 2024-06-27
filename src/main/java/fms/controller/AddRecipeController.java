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

/**
 * Controller-Klasse zum Hinzufügen von Rezepten zu einem Client.
 * Diese Klasse behandelt die Anzeige und Auswahl von Rezepten über eine JavaFX-Benutzeroberfläche.
 */
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

    /**
     * Setzt die Client-ID und lädt die zugehörigen Rezepte.
     *
     * @param clientId Die ID des Clients, zu dem Rezepte hinzugefügt werden sollen.
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
        loadRecipes();
    }

    /**
     * Initialisiert die Controller-Klasse.
     * Diese Methode wird automatisch aufgerufen, nachdem die FXML-Datei geladen wurde.
     */
    @FXML
    public void initialize() {
        String css = this.getClass().getResource("/css/styles.css").toExternalForm();
        recipeTableView.getStylesheets().add(css);

        setupTableColumns();
        recipeTableView.setEditable(true);
    }

    /**
     * Konfiguriert die Tabellenspalten.
     * Diese Methode richtet die Zellfabriken und Wertfabriken für die Tabellenspalten ein.
     */
    private void setupTableColumns() {
        // Einrichtung für die Auswahlspalte
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

        // Einrichtung für die anderen Spalten
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        proteinsColumn.setCellValueFactory(new PropertyValueFactory<>("proteins"));
        carbsColumn.setCellValueFactory(new PropertyValueFactory<>("carbs"));
        caloriesColumn.setCellValueFactory(new PropertyValueFactory<>("calories"));
        linkPlaceholderColumn.setCellValueFactory(new PropertyValueFactory<>("linkPlaceholder"));

        // Optionale Zellfabriken für bessere Formatierung
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

    /**
     * Lädt die Rezepte und markiert diejenigen, die dem aktuellen Client zugeordnet sind.
     */
    private void loadRecipes() {
        List<RecipeModel> allRecipes = DataManager.loadRecipes();
        List<Integer> clientRecipeIds = DataManager.getClientRecipeIds(clientId);

        for (RecipeModel recipe : allRecipes) {
            recipe.setSelected(clientRecipeIds.contains(recipe.getId()));
        }

        recipeTableView.setItems(FXCollections.observableArrayList(allRecipes));
    }

    /**
     * Behandelt die Aktion des Hinzufügens ausgewählter Rezepte zum Client.
     * Diese Methode sammelt die ausgewählten Rezepte und aktualisiert die Zuordnungen im DataManager.
     */
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