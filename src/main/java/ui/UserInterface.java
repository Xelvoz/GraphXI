package ui;

import graphs.structure.generators.UGGenerator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import physics.SpringsAndParticles;
import ui.animation.GraphAnimation;
import ui.animation.GraphCanvas;

public class UserInterface extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SpringsAndParticles forces = new SpringsAndParticles(UGGenerator.cyclic(5));
        GraphCanvas graphCanvas = new GraphCanvas();
        graphCanvas.setHeight(800);
        graphCanvas.setWidth(1200);

        GraphAnimation animation = new GraphAnimation(graphCanvas, forces);

        Group root = new Group();
        root.getChildren().add(graphCanvas);
        Scene scene = new Scene(root);

        primaryStage.setHeight(800);
        primaryStage.setWidth(1200);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        animation.start();
    }
}
