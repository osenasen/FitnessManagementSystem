package fms.model;

public class ClientModel {
  private int id;
  private String name;
  private String healthInfo;
  private String goal;
  private String workout;
  private String diet;
  
  public ClientModel(int id, String name) {
    this.id = id;
    this.name = name;
  }
  
  public int getId() { return id; }
  public String getName() { return name; }
  public String getHealthInfo() { return healthInfo; }
  public String getGoal() { return goal; }
  public String getWorkout() { return workout; }
  public String getDiet() { return diet; }
}

