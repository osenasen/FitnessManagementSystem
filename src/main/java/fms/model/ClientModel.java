package fms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private List<Integer> recipeIds;
  private List<Integer> exerciseIds;  // Changed from exercises

  public ClientModel() {
    this.recipeIds = new ArrayList<>();
    this.exerciseIds = new ArrayList<>();
  }

  public ClientModel(int id, String name) {
    this();
    this.id = id;
    this.name = name;
  }

  // Getters and setters

  public int getId() { return id; }
  public void setId(int id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public List<Integer> getRecipeIds() { return recipeIds; }
  public void setRecipeIds(List<Integer> recipeIds) { this.recipeIds = recipeIds; }

  public List<Integer> getExerciseIds() { return exerciseIds; }
  public void setExerciseIds(List<Integer> exerciseIds) { this.exerciseIds = exerciseIds; }

  // Utility methods

  public void addRecipeId(int recipeId) {
    if (!this.recipeIds.contains(recipeId)) {
      this.recipeIds.add(recipeId);
    }
  }

  public void removeRecipeId(int recipeId) {
    this.recipeIds.remove(Integer.valueOf(recipeId));
  }
}