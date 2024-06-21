package fms.model;

import java.io.Serializable;
import java.util.List;

public class ClientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private List<RecipeModel> recipeModels;
  private List<ExerciseModel> exerciseModels;
  
  public ClientModel() {}
  
  public ClientModel(int id, String name, List<RecipeModel> recipeModels, List<ExerciseModel> exerciseModels) {
    this.id = id;
    this.name = name;
    this.recipeModels = recipeModels;
    this.exerciseModels = exerciseModels;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RecipeModel> getRecipes() {
    return recipeModels;
  }

  public void setRecipes(List<RecipeModel> recipeModels) {
    this.recipeModels = recipeModels;
  }

  public List<ExerciseModel> getExercises() {
    return exerciseModels;
  }

  public void setExercises(List<ExerciseModel> exerciseModels) {
    this.exerciseModels = exerciseModels;
  }
}
