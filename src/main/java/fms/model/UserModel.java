package fms.model;

public class UserModel {
  private static UserModel instance;
  private String username;
  private String password;

  private UserModel() {
    this.username = "defaultUser";
    this.password = "password";
  }

  public static UserModel getInstance() {
    if (instance == null) {
      instance = new UserModel();
    }
    return instance;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String newPassword) {
    this.password = newPassword;
  }
}
