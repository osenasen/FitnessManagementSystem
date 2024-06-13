package fms.model;

public class NutritionModel {
  private String dietDetails;
  
  public NutritionModel(String dietDetails) {
    this.dietDetails = dietDetails;
  }
  
  public String getDietDetails() {
    return dietDetails;
  }
}
