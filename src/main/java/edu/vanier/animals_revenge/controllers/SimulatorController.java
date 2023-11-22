package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.models.CustomProjectileTriangle;
import edu.vanier.animals_revenge.windows.graphs;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

    CustomProjectile projectile;

    private boolean BTNCustomHasBeenPressed;
    
    private Stage stage;
    
    private CustomProjectileCircle circle;
    
    private CustomProjectileSquare square;
    
    private CustomProjectileTriangle triangle;

    @FXML
    private Button BTNCustom;
    
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

    private static StackPane selected;

    private static int size = 1;

    private static int rotate = 1;

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    @Override
    public void init() {
        MainApp.initGameObjects();
        ImagePattern brick = new ImagePattern(new Image("/assets/textures/brick32x32.png"));
        ImagePattern dirt = new ImagePattern(new Image("/assets/textures/dirt32x32.png"));
        ImagePattern wood = new ImagePattern(new Image("/assets/textures/wood32x32.png"));
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
        square2.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(dirt));
        circle2.getChildren().
                stream()
                .map(node -> (Circle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(dirt));
        // TODO fix image pattern squeeze
        rectangle2.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(dirt));
        square3.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(wood));
        circle3.getChildren().
                stream()
                .map(node -> (Circle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(wood));
        // TODO fix image pattern squeeze
        rectangle3.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(wood));

        // Add event listener for sliders
        sizeSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            // Value should be only set after slider knob snaps to major unit tick
            if (newValue.intValue() == newValue.doubleValue()) {
                size = newValue.intValue();
            }
        }));

        rotateSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            // Value should be only set after slider knob snaps to major unit tick
            if (newValue.intValue() == newValue.doubleValue()) {
                rotate = newValue.intValue();
            }
        }));

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
        MainApp.launch(square, circle, triangle);
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

    @FXML
    void selectProjectile(ActionEvent event) throws Exception {

        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        CustomProjectile Projectile = (CustomProjectile) CustomProjectileController.deserialize(file.getAbsolutePath());

        System.out.println(Projectile.getClass().getSimpleName());

        if (Projectile instanceof CustomProjectileCircle) {

            circle = (CustomProjectileCircle)Projectile;
            
            System.out.println(circle.getImgPath());
            
            System.out.println("This is a circle");

        } else if (Projectile instanceof CustomProjectileSquare) {

            square = (CustomProjectileSquare)Projectile;
            
            
            System.out.println(square.getImgPath());
            
            System.out.println("This is a square, its color is " + square.getColour() + "Its width is " +
                    
                    square.getWidth()
                    + " Its height is: " + square.getHeight()
                    
            );

        } else {
            
            triangle = (CustomProjectileTriangle)Projectile;
            
            System.out.println(triangle.getImgPath());
            System.out.println("This is a triangle");
            
            
        }

    }
    
    public static StackPane getSelected() {
        return selected;
    }

    public static int getSize() {
        return size;
    }

    public static int getRotate() {
        return rotate;
    }

    public CustomProjectileCircle getCircle() {
        return circle;
    }

    public void setCircle(CustomProjectileCircle circle) {
        this.circle = circle;
    }

    public CustomProjectileSquare getSquare() {
        return square;
    }

    public void setSquare(CustomProjectileSquare square) {
        this.square = square;
    }

    public CustomProjectileTriangle getTriangle() {
        return triangle;
    }

    public void setTriangle(CustomProjectileTriangle triangle) {
        this.triangle = triangle;
    }
    
    
    
}
