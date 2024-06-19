package fms.model;

import fms.utils.RecipeDAO;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public class RecipeModel {
  private int id;
  private String name;
  private String imagePath; // placeholder for image link
  private int proteins;
  private int carbs;
  private int calories;
  private String linkPlaceholder;
  private BooleanProperty selected = new SimpleBooleanProperty(false);

  private static RecipeDAO recipeDAO = new RecipeDAO();

  // Constructors
  public RecipeModel() {}

  public RecipeModel(int id, String name, String imagePath, int proteins, int carbs, int calories, String linkPlaceholder) {
    this.id = id;
    this.name = name;
    this.imagePath = imagePath;
    this.proteins = proteins;
    this.carbs = carbs;
    this.calories = calories;
    this.linkPlaceholder = linkPlaceholder;
  }

  // Getters and setters
  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getImagePath() { return imagePath; }
  public void setImagePath(String imagePath) { this.imagePath = imagePath; }

  public int getProteins() { return proteins; }
  public void setProteins(int proteins) { this.proteins = proteins; }

  public int getCarbs() { return carbs; }
  public void setCarbs(int carbs) { this.carbs = carbs; }

  public int getCalories() { return calories; }
  public void setCalories(int calories) { this.calories = calories; }

  public String getLinkPlaceholder() { return linkPlaceholder; }
  public void setLinkPlaceholder(String linkPlaceholder) { this.linkPlaceholder = linkPlaceholder; }

  public BooleanProperty selectedProperty() {
    return selected;
  }

  public boolean isSelected() {
    return selected.get();
  }

  public void setSelected(boolean selected) {
    this.selected.set(selected);
  }

  // Database methods
  public static void addRecipe(RecipeModel recipe) {
    recipeDAO.addRecipe(recipe);
  }

  public static List<RecipeModel> getAllRecipes() {
    return recipeDAO.getAllRecipes();
  }
}