package fms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Modellklasse für einen Client, der Rezepte und Übungen enthält.
 * Implementiert das Serializable-Interface zur Unterstützung der Serialisierung.
 */
public class ClientModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private List<Integer> recipeIds;
  private List<Integer> exerciseIds;

  /**
   * Standardkonstruktor. Initialisiert leere Listen für Rezept- und Übungs-IDs.
   */
  public ClientModel() {
    this.recipeIds = new ArrayList<>();
    this.exerciseIds = new ArrayList<>();
  }

  /**
   * Konstruktor zur Initialisierung mit ID und Namen.
   *
   * @param id   Die ID des Clients.
   * @param name Der Name des Clients.
   */
  public ClientModel(int id, String name) {
    this();
    this.id = id;
    this.name = name;
  }

  // Getters and setters

  /**
   * Gibt die ID des Clients zurück.
   *
   * @return Die ID des Clients.
   */
  public int getId() {
    return id;
  }

  /**
   * Setzt die ID des Clients.
   *
   * @param id Die ID des Clients.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gibt den Namen des Clients zurück.
   *
   * @return Der Name des Clients.
   */
  public String getName() {
    return name;
  }

  /**
   * Setzt den Namen des Clients.
   *
   * @param name Der Name des Clients.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gibt die Liste der Rezept-IDs des Clients zurück.
   *
   * @return Die Liste der Rezept-IDs.
   */
  public List<Integer> getRecipeIds() {
    return recipeIds;
  }

  /**
   * Setzt die Liste der Rezept-IDs des Clients.
   *
   * @param recipeIds Die Liste der Rezept-IDs.
   */
  public void setRecipeIds(List<Integer> recipeIds) {
    this.recipeIds = recipeIds;
  }

  /**
   * Gibt die Liste der Übungs-IDs des Clients zurück.
   *
   * @return Die Liste der Übungs-IDs.
   */
  public List<Integer> getExerciseIds() {
    return exerciseIds;
  }

  /**
   * Setzt die Liste der Übungs-IDs des Clients.
   *
   * @param exerciseIds Die Liste der Übungs-IDs.
   */
  public void setExerciseIds(List<Integer> exerciseIds) {
    this.exerciseIds = exerciseIds;
  }

  // Utility methods

  /**
   * Fügt eine Rezept-ID zur Liste der Rezept-IDs hinzu, wenn sie noch nicht enthalten ist.
   *
   * @param recipeId Die hinzuzufügende Rezept-ID.
   */
  public void addRecipeId(int recipeId) {
    if (!this.recipeIds.contains(recipeId)) {
      this.recipeIds.add(recipeId);
    }
  }

  /**
   * Entfernt eine Rezept-ID aus der Liste der Rezept-IDs.
   *
   * @param recipeId Die zu entfernende Rezept-ID.
   */
  public void removeRecipeId(int recipeId) {
    this.recipeIds.remove(Integer.valueOf(recipeId));
  }
}