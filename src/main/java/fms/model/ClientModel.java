package main.java.fms.model;

public class ClientModel {
  private int id;
  private String name;
  private String healthInfo;
  private String goal;
  private String workout;
  private String diet;
  
  public ClientModel(int id, String name, String healthInfo, String goal, String workout, String diet) {
    this.id = id;
    this.name = name;
    this.healthInfo = healthInfo;
    this.goal = goal;
    this.workout = workout;
    this.diet = diet;
  }
  
  // Getters and setters
  
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
  
  public String getHealthInfo() {
    return healthInfo;
  }
  
  public void setHealthInfo(String healthInfo) {
    this.healthInfo = healthInfo;
  }
  
  public String getGoal() {
    return goal;
  }
  
  public void setGoal(String goal) {
    this.goal = goal;
  }
  
  public String getWorkout() {
    return workout;
  }
  
  public void setWorkout(String workout) {
    this.workout = workout;
  }
  
  public String getDiet() {
    return diet;
  }
  
  public void setDiet(String diet) {
    this.diet = diet;
  }
}
