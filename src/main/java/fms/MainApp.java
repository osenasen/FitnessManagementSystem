package fms;

import fms.utils.RecipeDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import fms.utils.DatabaseManager;
import fms.utils.UserDAO;

public class MainApp extends Application {
  @Override
  public void start(Stage primaryStage) {
    DatabaseManager.initializeDatabase();

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
      Scene scene = new Scene(loader.load());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Fitness Management System");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace(); // Simple error logging
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
