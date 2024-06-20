package fms.model;

import java.io.Serializable;

public class Exercise implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private String description;
  private int duration;

  // Default constructor
  public Exercise() {}

  // Constructor with parameters
  public Exercise(int id, String name, String description, int duration) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.duration = duration;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }
}
