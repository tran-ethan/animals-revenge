package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.windows.Parameters;
import edu.vanier.animals_revenge.windows.Selection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

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

    @FXML
    private Button btnParameters;

    private static StackPane selected;

    private static int size = 1;

    private static int rotate = 1;

    private static float friction = 0.5f;

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    @Override
    public void init() {
        // Spawn in objects
        MainApp.initGameObjects();

        // Get image patterns to fill obstacle shapes on right hand panel
        ImagePattern brick = new ImagePattern(new Image("/assets/textures/brick.png"));
        ImagePattern long_brick = new ImagePattern(new Image("/assets/textures/long_brick.png"));
        ImagePattern dirt = new ImagePattern(new Image("/assets/textures/dirt.png"));
        ImagePattern long_dirt = new ImagePattern(new Image("/assets/textures/long_dirt.png"));
        ImagePattern wood = new ImagePattern(new Image("/assets/textures/wood.png"));
        ImagePattern long_wood = new ImagePattern(new Image("/assets/textures/long_wood.png"));

        // Set selected obstacle to (top-left) square brick
        selected = square1;

        // Set fill for obstacle shapes
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
        rectangle1.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(long_brick));
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
        rectangle2.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(long_dirt));
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
        rectangle3.getChildren().
                stream()
                .map(node -> (Rectangle) node)
                .findFirst()
                .ifPresent(rectangle -> rectangle.setFill(long_wood));

        logger.info("Initializing SimulatorController...");

        btnParameters.setOnAction((event) -> {
            Parameters parameters = new Parameters();
            parameters.show();
        });

    }

    /**
     * Switches the scene to the home screen.
     */
    @FXML
    public void goHome() {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }

    /**
     * Toggles the icon between play and pause states by changing the glyph name
     * of the playPauseIcon based on its current state.
     * If the current glyph name is "PLAY", it switches it to "PAUSE"; otherwise, it sets it to "PLAY".
     * This method visually changes the icon representing play/pause functionality.
     */
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
        // TODO
        System.out.println("resetting");
    }

    /**
     * Launches the projectile.
     */
    @FXML
    public void launch() {
        MainApp.launch();
    }

    @FXML
    void openAboutPage(ActionEvent event) {
        HelpButtonController help = new HelpButtonController();
    }

    @FXML
    void save(ActionEvent event) {
        // TODO Save scene
        System.out.println("saving");
    }

    /**
     * Handles the selection of the obstacle in the right-hand side.
     * Changes the background color to indicate the selected StackPane.
     * Updates the 'selected' StackPane (obstacle) reference to the newly selected StackPane.
     *
     * @param event The MouseEvent generated by selecting a StackPane
     */
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

    }

    public static void throwWarning(String warningMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(warningMessage);
        alert.setTitle(title);
        alert.showAndWait();
    }

    /**
     * Event handler method for updating values based on slider movements.
     * Handles the MouseEvent triggered by slider adjustments to update corresponding text fields
     * and associated values for size, rotation, and friction.
     *
     * @param event The MouseEvent generated by slider adjustment
     */
    @FXML
    void onSliderChange(MouseEvent event) {
        Object source = event.getSource();
        if (source == sizeSlider) {
            sizeTextField.setText(String.format("%.0f", sizeSlider.getValue()));
            size = (int) sizeSlider.getValue();
        } else if (source == rotateSlider) {
            rotateTextField.setText(String.format("%.0f", rotateSlider.getValue()));
            rotate = (int) rotateSlider.getValue();
        } else if (source == frictionSlider) {
            frictionTextField.setText(String.format("%.1f", frictionSlider.getValue()));
            friction = (float) frictionSlider.getValue();
        }
    }


    /**
     * Event handler method triggered by text field changes to update slider values
     * and associated variables for size, rotation, and friction.
     * Throws a warning message if the input is outside the slider's range of values.
     *
     * @param event The ActionEvent generated by text field modification
     */
    @FXML
    void onTextChange(ActionEvent event) {
        Object source = event.getSource();

        if (source == sizeTextField) {
            double value = Double.parseDouble(sizeTextField.getText());
            if (value >= sizeSlider.getMin() && value <= sizeSlider.getMax()) {
                sizeSlider.setValue(value);
                size = (int) value;
            } else {
                throwWarning("Size has to be between 1 and 5.", "Invalid size");
            }
        } else if (source == rotateTextField) {
            double value = Double.parseDouble(rotateTextField.getText());
            rotateSlider.setValue(value);
            if (value >= rotateSlider.getMin() && value <= rotateSlider.getMax()) {
                rotateSlider.setValue(value);
                rotate = (int) value;
            } else {
                throwWarning("Angle of rotation has to be between 0 and 360.", "Invalid rotation angle");
            }
        } else if (source == frictionTextField) {
            // Round value entered in the text field to the nearest 0.1 unit tick
            double value = Math.round(Double.parseDouble(frictionTextField.getText()) * 10.0) / 10.0;

            if (value >= frictionSlider.getMin() && value <= frictionSlider.getMax()) {
                frictionTextField.setText(String.format("%.1f", value));
                frictionSlider.setValue(value);
                friction = (float) value;
            } else {
                throwWarning("Friction coefficient must be between 0 and 1.", "Invalid friction coefficient");
            }
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

    public static float getFriction() {
        return friction;
    }

}
