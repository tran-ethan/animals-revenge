package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.vanier.animals_revenge.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

    @FXML
    private StackPane circle1;

    @FXML
    private StackPane circle2;

    @FXML
    private StackPane circle3;

    @FXML
    private StackPane square1;

    @FXML
    private StackPane square2;

    @FXML
    private StackPane square3;

    @FXML
    private StackPane rectangle1;

    @FXML
    private StackPane rectangle2;

    @FXML
    private StackPane rectangle3;

    @FXML
    private FontAwesomeIconView playPauseIcon;

    @FXML
    private Slider rotateSlider;

    @FXML
    private Slider sizeSlider;

    private StackPane selected;

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    @Override
    public void init() {
        MainApp.initGameObjects();
        ImagePattern brick = new ImagePattern(new Image("/assets/textures/brick.png"));
        selected = square1;

        square1.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(brick));
        circle1.getChildren().
                stream()
                .map(node -> (Circle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(brick));
        // TODO fix image pattern squeeze
        rectangle1.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(brick));
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
    void select(MouseEvent event) {
        StackPane source = (StackPane) event.getSource();
        if (selected != source) {
            source.setStyle("-fx-background-color: lightgray");
            selected.setStyle("-fx-background-color: white");
            selected = source;
        }
    }
}
