package edu.vanier.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point of the JavaFX application.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 * @author Zachary Tremblay
 * @author Anton Lisunov
 */
public class MainApp extends Application {

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    private static Scene scene;

    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setResizable(false);
        
        try {
            logger.info("Bootstrapping the application...");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainApp.fxml"));
            scene = new Scene(loader.load(), 1280, 720);
            primaryStage.setScene(scene);
            // primaryStage.sizeToScene();
            primaryStage.show();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void setRoot(String fxml) throws IOException {
        String filePath = String.format("/fxml/%s.fxml", fxml);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(filePath));
        scene.setRoot(loader.load());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
