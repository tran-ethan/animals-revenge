package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.windows.Selection;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

    private CustomProjectileCircle circle;

    private CustomProjectileSquare square;

    @FXML
    private Button BTNCustom;

    @FXML
    private Button SelectProjectileBTN;

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

    @FXML
    private Slider frictionSlider;

    @FXML
    private TextField sizeTextField;

    @FXML
    private TextField rotateTextField;

    @FXML
    private TextField frictionTextField;

    private static StackPane selected;

    private static int size = 1;

    private static int rotate = 1;

    private static int friction = 1;

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

        frictionSlider.valueProperty().addListener(((observable, oldValue, newValue) -> {
            // Value should be only set after slider knob snaps to major unit tick
            if (newValue.intValue() == newValue.doubleValue()) {
                friction = newValue.intValue();
            }
        }));

        sizeTextField.textProperty().bind(sizeSlider.valueProperty().asString("%.0f"));
        rotateTextField.textProperty().bind(rotateSlider.valueProperty().asString("%.0f"));
        frictionTextField.textProperty().bind(frictionSlider.valueProperty().asString("%.0f"));

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
        MainApp.launch(square, circle);
    }

    @FXML
    void OpenAboutPage(ActionEvent event) {
        HelpButtonController help = new HelpButtonController();
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
        System.out.println(CustomProjectileController.customProjectiles);

        
        Selection sel = new Selection();
        sel.show();
        
        /*FileChooser fileChooser = new FileChooser();
        
        File file = fileChooser.showOpenDialog(null);
        
        if (file != null) {
        
        //will throw an error in the deserilization method if not a valid file
        CustomProjectile Projectile = (CustomProjectile) CustomProjectileController.deserialize(file.getAbsolutePath());
        
        if (Projectile instanceof CustomProjectileCircle) {
        
        circle = (CustomProjectileCircle) Projectile;
        
        } else if (Projectile instanceof CustomProjectileSquare) {
        
        square = (CustomProjectileSquare) Projectile;
        
        }
        
        } else if (file == null) {
        throwWarning("No File Selected", "File Selection");
        }*/

    }

    public static void throwWarning(String warningMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(warningMessage);
        alert.setTitle(title);
        alert.showAndWait();
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

    public static int getFriction() {
        return friction;
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

}
