package fms.model;

import java.io.Serializable;
import java.util.List;

public class ClientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private List<Recipe> recipes;
  private List<Exercise> exercises;

  // Default constructor
  public ClientModel() {}

  // Constructor with parameters
  public ClientModel(int id, String name, List<Recipe> recipes, List<Exercise> exercises) {
    this.id = id;
    this.name = name;
    this.recipes = recipes;
    this.exercises = exercises;
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

  public List<Recipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public List<Exercise> getExercises() {
    return exercises;
  }

  public void setExercises(List<Exercise> exercises) {
    this.exercises = exercises;
  }
}
