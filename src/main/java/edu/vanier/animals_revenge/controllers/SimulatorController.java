package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.vanier.animals_revenge.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;

    @FXML
    private Rectangle square1;

    @FXML
    private Rectangle square2;

    @FXML
    private Rectangle square3;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle2;

    @FXML
    private Rectangle rectangle3;

    @FXML
    private FontAwesomeIconView playPauseIcon;

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    @Override
    public void init() {
        MainApp.initGameObjects();
        square1.setFill(new ImagePattern(new Image("/assets/textures/brick.png")));
        circle1.setFill(new ImagePattern(new Image("/assets/textures/brick.png")));
        // TODO fix image pattern squeeze
        rectangle1.setFill(new ImagePattern(new Image("/assets/textures/brick.png")));
        logger.info("Initializing SimulatorController...");
    }

    @FXML
    public void goHome() {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }

    @FXML
    public void playPause() {
        if (playPauseIcon.getGlyphName().equals("PLAY")) {
            playPauseIcon.setGlyphName("PAUSE");
        } else {
            playPauseIcon.setGlyphName("PLAY");
        }
    }

    @FXML
    public void reset() {
        System.out.println("resetting");
    }

    @FXML
    public void launch() {
        MainApp.launch();
    }

    @FXML
    void spawnObstacle(MouseEvent event) {
        System.out.println("spawning obstacle");
    }
}
