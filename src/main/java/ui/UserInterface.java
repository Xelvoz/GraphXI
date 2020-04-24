package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class UserInterface extends Application {

    private ConfigurableApplicationContext context;
    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        SpringApplication app = new SpringApplication(UserInterface.class);
        app.setBannerMode(Banner.Mode.OFF);
        context = app.run();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui.fxml"));
        loader.setControllerFactory(context::getBean);
        root = loader.load();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("GraphXI");
        primaryStage.getIcons().add(new Image(UserInterface.class.getResourceAsStream("/icons/graphxi.png")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.stop();
    }
}
