package fms.model;

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
  
  public int getId() { return id; }
  public String getName() { return name; }
  public String getHealthInfo() { return healthInfo; }
  public String getGoal() { return goal; }
  public String getWorkout() { return workout; }
  public String getDiet() { return diet; }
}

