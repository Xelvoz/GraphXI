package ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
