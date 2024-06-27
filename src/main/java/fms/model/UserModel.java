package fms.model;

import java.io.Serializable;

/**
 * Modellklasse für einen Benutzer.
 * Implementiert das Serializable-Interface zur Unterstützung der Serialisierung.
 */
public class UserModel implements Serializable {
  private static final long serialVersionUID = 1L;

  private int id;
  private String username;
  private String password;

  /**
   * Standardkonstruktor.
   */
  public UserModel() {
  }

  /**
   * Konstruktor zur Initialisierung mit Benutzername und Passwort.
   *
   * @param username Der Benutzername des Benutzers.
   * @param password Das Passwort des Benutzers.
   */
  public UserModel(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * Gibt die ID des Benutzers zurück.
   *
   * @return Die ID des Benutzers.
   */
  public int getId() {
    return id;
  }

  /**
   * Setzt die ID des Benutzers.
   *
   * @param id Die ID des Benutzers.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gibt den Benutzernamen zurück.
   *
   * @return Der Benutzername.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Setzt den Benutzernamen.
   *
   * @param username Der neue Benutzername.
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gibt das Passwort des Benutzers zurück.
   *
   * @return Das Passwort.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Setzt das Passwort des Benutzers.
   *
   * @param password Das neue Passwort.
   */
  public void setPassword(String password) {
    this.password = password;
  }
}