package fms.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.io.Serializable;

/**
 * Modellklasse für ein Rezept.
 * Implementiert das Serializable-Interface zur Unterstützung der Serialisierung.
 */
public class RecipeModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String name;
  private String imagePath;
  private int proteins;
  private int carbs;
  private int calories;
  private String linkPlaceholder;
  private transient BooleanProperty selected = new SimpleBooleanProperty(false);

  /**
   * Standardkonstruktor.
   */
  public RecipeModel() {
  }

  /**
   * Konstruktor zur Initialisierung mit ID, Name, Bildpfad, Proteingehalt, Kohlenhydratgehalt,
   * Kaloriengehalt und Platzhalterlink.
   *
   * @param id              Die ID des Rezepts.
   * @param name            Der Name des Rezepts.
   * @param imagePath       Der Pfad zum Bild des Rezepts.
   * @param proteins        Der Proteingehalt des Rezepts.
   * @param carbs           Der Kohlenhydratgehalt des Rezepts.
   * @param calories        Der Kaloriengehalt des Rezepts.
   * @param linkPlaceholder Der Platzhalterlink für das Rezept.
   */
  public RecipeModel(int id, String name, String imagePath, int proteins, int carbs, int calories, String linkPlaceholder) {
    this.id = id;
    this.name = name;
    this.imagePath = imagePath;
    this.proteins = proteins;
    this.carbs = carbs;
    this.calories = calories;
    this.linkPlaceholder = linkPlaceholder;
  }

  /**
   * Gibt die ID des Rezepts zurück.
   *
   * @return Die ID des Rezepts.
   */
  public int getId() {
    return id;
  }

  /**
   * Setzt die ID des Rezepts.
   *
   * @param id Die ID des Rezepts.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gibt den Namen des Rezepts zurück.
   *
   * @return Der Name des Rezepts.
   */
  public String getName() {
    return name;
  }

  /**
   * Setzt den Namen des Rezepts.
   *
   * @param name Der Name des Rezepts.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gibt den Bildpfad des Rezepts zurück.
   *
   * @return Der Bildpfad des Rezepts.
   */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Setzt den Bildpfad des Rezepts.
   *
   * @param imagePath Der Bildpfad des Rezepts.
   */
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  /**
   * Gibt den Proteingehalt des Rezepts zurück.
   *
   * @return Der Proteingehalt des Rezepts.
   */
  public int getProteins() {
    return proteins;
  }

  /**
   * Setzt den Proteingehalt des Rezepts.
   *
   * @param proteins Der Proteingehalt des Rezepts.
   */
  public void setProteins(int proteins) {
    this.proteins = proteins;
  }

  /**
   * Gibt den Kohlenhydratgehalt des Rezepts zurück.
   *
   * @return Der Kohlenhydratgehalt des Rezepts.
   */
  public int getCarbs() {
    return carbs;
  }

  /**
   * Setzt den Kohlenhydratgehalt des Rezepts.
   *
   * @param carbs Der Kohlenhydratgehalt des Rezepts.
   */
  public void setCarbs(int carbs) {
    this.carbs = carbs;
  }

  /**
   * Gibt den Kaloriengehalt des Rezepts zurück.
   *
   * @return Der Kaloriengehalt des Rezepts.
   */
  public int getCalories() {
    return calories;
  }

  /**
   * Setzt den Kaloriengehalt des Rezepts.
   *
   * @param calories Der Kaloriengehalt des Rezepts.
   */
  public void setCalories(int calories) {
    this.calories = calories;
  }

  /**
   * Gibt den Platzhalterlink des Rezepts zurück.
   *
   * @return Der Platzhalterlink des Rezepts.
   */
  public String getLinkPlaceholder() {
    return linkPlaceholder;
  }

  /**
   * Setzt den Platzhalterlink des Rezepts.
   *
   * @param linkPlaceholder Der Platzhalterlink des Rezepts.
   */
  public void setLinkPlaceholder(String linkPlaceholder) {
    this.linkPlaceholder = linkPlaceholder;
  }

  /**
   * Gibt die BooleanProperty für die Auswahlstatus des Rezepts zurück.
   *
   * @return Die BooleanProperty für die Auswahlstatus des Rezepts.
   */
  public BooleanProperty selectedProperty() {
    return selected;
  }

  /**
   * Gibt den Auswahlstatus des Rezepts zurück.
   *
   * @return true, wenn das Rezept ausgewählt ist, ansonsten false.
   */
  public boolean isSelected() {
    return selected.get();
  }

  /**
   * Setzt den Auswahlstatus des Rezepts.
   *
   * @param selected true, um das Rezept auszuwählen, ansonsten false.
   */
  public void setSelected(boolean selected) {
    this.selected.set(selected);
  }
}