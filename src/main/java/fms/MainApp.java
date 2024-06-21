package fms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/LoginView.fxml"));
      Scene scene = new Scene(loader.load());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Fitness Management System");
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
    System.out.println(System.getProperty("javafx.runtime.version"));
  }
}
