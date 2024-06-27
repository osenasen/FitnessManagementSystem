package fms.model;

import java.io.Serializable;

/**
 * Modellklasse für eine Übung.
 * Implementiert das Serializable-Interface zur Unterstützung der Serialisierung.
 */
public class ExerciseModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private String description;
  private int duration;

  /**
   * Standardkonstruktor.
   */
  public ExerciseModel() {
  }

  /**
   * Konstruktor zur Initialisierung mit ID, Name, Beschreibung und Dauer.
   *
   * @param id          Die ID der Übung.
   * @param name        Der Name der Übung.
   * @param description Die Beschreibung der Übung.
   * @param duration    Die Dauer der Übung in Minuten.
   */
  public ExerciseModel(int id, String name, String description, int duration) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.duration = duration;
  }

  /**
   * Gibt die ID der Übung zurück.
   *
   * @return Die ID der Übung.
   */
  public int getId() {
    return id;
  }

  /**
   * Setzt die ID der Übung.
   *
   * @param id Die ID der Übung.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gibt den Namen der Übung zurück.
   *
   * @return Der Name der Übung.
   */
  public String getName() {
    return name;
  }

  /**
   * Setzt den Namen der Übung.
   *
   * @param name Der Name der Übung.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gibt die Beschreibung der Übung zurück.
   *
   * @return Die Beschreibung der Übung.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Setzt die Beschreibung der Übung.
   *
   * @param description Die Beschreibung der Übung.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gibt die Dauer der Übung zurück.
   *
   * @return Die Dauer der Übung in Minuten.
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Setzt die Dauer der Übung.
   *
   * @param duration Die Dauer der Übung in Minuten.
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}