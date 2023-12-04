package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller class for managing custom projectiles in the application. This
 * controller handles UI events and interactions related to custom projectiles,
 * allowing users to create, modify, and save custom projectile configurations.
 *
 * @author Mackenzie Rouchdy
 */
public class CustomProjectileController implements UIController, Serializable {

    /**
     * Provides a list of custom projectiles.
     */
    public static ArrayList<CustomProjectile> customProjectiles = new ArrayList<>();

    /**
     * Provides the file extension that is used when serializing a custom
     * projectile.
     */
    private final static String FILE_EXTENSION = ".proj";

    /**
     * Provides a border colour.
     */
    public static final Color borderColour = Color.RED;

    /**
     * Provides access to the density property.
     */
    private static float density;

    /**
     * Provides access to the restitution property.
     */
    private static float restitution;

    /**
     * Declaration of the user's selected image file.
     */
    File SelectedImgFile;

    /**
     * Declaration of the user's saved file.
     */
    File SaveFile;

    /**
     * Declaration of the custom square projectile.
     */
    CustomProjectileSquare squareProjectile;

    /**
     * Declaration of the custom circle projectile.
     */
    CustomProjectileCircle circleProjectile;

    /**
     * JavaFX density slider component used to change the density property of a
     * custom projectile.
     */
    @FXML
    private Slider densitySlider;

    /**
     * JavaFX restitution slider component used to change the restitution
     * property of a custom projectile.
     */
    @FXML
    private Slider restitutionSlider;

    /**
     * JavaFX Circle component that serves as a copy of a circle shape appearing
     * in the middle of the screen.
     */
    @FXML
    private Circle circleCopy;

    /**
     * JavaFX Rectangle component that serves as a copy of a square shape
     * appearing in the middle of the screen.
     */
    @FXML
    private Rectangle squareCopy;

    /**
     * JavaFX BorderPane component that serves as a container for other UI
     * elements.
     */
    @FXML
    private BorderPane borderPane;

    /**
     * JavaFX ColorPicker component used for selecting colors.
     */
    @FXML
    private ColorPicker ColourPicker;

    /**
     * JavaFX Circle component that represents a circle shape in the middle of
     * the screen.
     */
    @FXML
    private Circle circle;

    /**
     * JavaFX Rectangle component that represents a square shape in the middle
     * of the screen.
     */
    @FXML
    private Rectangle square;

    /**
     * JavaFX TextField component used for displaying and editing the density
     * property of a projectile.
     */
    @FXML
    private TextField txtFieldDensity;

    /**
     * JavaFX TextField component used for displaying and editing the
     * restitution property of a projectile.
     */
    @FXML
    private TextField txtFieldRestitution;

    /**
     * JavaFX Slider component used for adjusting the size property of a
     * projectile.
     */
    @FXML
    private Slider sizeSlider;

    /**
     * JavaFX TextField component used for displaying and editing the size
     * property of a projectile.
     */
    @FXML
    private TextField txtFieldSize;

    /**
     * Handles the action event for saving changes.
     *
     * @param event The ActionEvent triggered by the save button.
     * @throws MalformedURLException If there is an issue with the provided URL.
     */
    @FXML
    void saveAsChanges(ActionEvent event) throws MalformedURLException {
        if (txtFieldRestitution.getText().trim().equals("")) {
            restitution = 0.5f;
        }

        if (txtFieldDensity.getText().trim().equals("")) {
            // If the text field is empty
            density = 0.3f;
        }

        Shape shape = null;
        Color color;

        // If there is no shape visible than there is nothing to save and thus should not open the save dialogue
        if (squareCopy.isVisible() || circleCopy.isVisible()) {
            // Opens file chooser
            FileChooser saveLocation = new FileChooser();

            // Sets initial file name
            saveLocation.setInitialFileName("myCustomProjectile" + FILE_EXTENSION);

            // Provides the extension(s) the file will have
            saveLocation.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Custom Projectile", "*proj"), new FileChooser.ExtensionFilter("All Files", "*"));

            SaveFile = saveLocation.showSaveDialog(null);

            // If no file is selected
            if (SaveFile != null) {

                if (squareCopy.isVisible()) {
                    shape = squareCopy;
                } else if (circleCopy.isVisible()) {
                    shape = circleCopy;
                }

                color = ColourPicker.getValue();

                // Creates a projectile based on the parameters the user has selected
                if (shape == squareCopy) {

                    double width = squareCopy.getWidth();
                    double height = squareCopy.getHeight();
                    String StringColor = color.toString().replace("0x", "");

                    if (SelectedImgFile != null) {
                        String imgPath = SelectedImgFile.getAbsolutePath();
                        squareProjectile = new CustomProjectileSquare(width, height, StringColor, imgPath, restitution, density);
                    } else {
                        squareProjectile = new CustomProjectileSquare(width, height, StringColor, restitution, density);
                    }

                    serialize(SaveFile.getAbsolutePath(), squareProjectile);

                    customProjectiles.add(squareProjectile);

                } else if (shape == circleCopy) {

                    double radius = circleCopy.getRadius();
                    String StringColor = color.toString().replace("0x", "");

                    if (SelectedImgFile != null) {
                        String imgPath = SelectedImgFile.getAbsolutePath();
                        circleProjectile = new CustomProjectileCircle(radius, StringColor, imgPath, restitution, density);
                    } else {
                        circleProjectile = new CustomProjectileCircle(radius, StringColor, restitution, density);
                    }

                    serialize(SaveFile.getAbsolutePath(), circleProjectile);
                    customProjectiles.add(circleProjectile);

                }
            }
        } else {
            SimulatorController.throwWarning("Please Provide Properties To Save!", "Saving Error");
        }

    }

    /**
     * Method to serialize a custom projectile and save it to a file.
     *
     * @param filePath The path to the file where the custom projectile will be
     * saved.
     * @param p The custom projectile object to be serialized.
     */
    public static void serialize(String filePath, CustomProjectile p) {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filePath))) {
            o.writeObject(p);
        } catch (IOException e) {
            System.out.println("Error during serilization process");
        }

    }

    /**
     * Method to deserialize a custom projectile from a file.
     *
     * @param filePath The path to the file from which the custom projectile
     * will be deserialized.
     * @return The deserialized custom projectile object.
     */
    public static CustomProjectile deserialize(String filePath) {

        try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(filePath))) {

            Object obj = o.readObject();

            System.out.println(obj.getClass().getSimpleName());

            if (obj instanceof CustomProjectileSquare) {
                return (CustomProjectileSquare) obj;
            } else if (obj instanceof CustomProjectileCircle) {
                return (CustomProjectileCircle) obj;
            } else {
                SimulatorController.throwWarning("File Not A Valid Custom Projectile", "Serialization Error");
            }

        } catch (IOException | ClassNotFoundException e) {
            SimulatorController.throwWarning("File Not A Valid Custom Projectile", "Deserialization Error");
        }
        return null;
    }

    /**
     * Handles the action event for choosing a colour. Sets the fill colour of
     * the visible shape (either square or circle) to the selected color from
     * the ColourPicker.
     *
     * @param event The ActionEvent triggered by the color selection.
     */
    @FXML
    void chooseColor(ActionEvent event) {

        if (squareCopy.isVisible()) {
            squareCopy.setFill(ColourPicker.getValue());
        } else {

            circleCopy.setFill(ColourPicker.getValue());

        }
    }

    /**
     * Handles the event when the user chooses an image file. It opens a
     * FileChooser dialog with filters for selecting image files. After
     * selecting a file, it loads the chosen image and sets the ImagePattern for
     * the visible shape (either square or circle).
     *
     * @param event The ActionEvent triggered
     */
    @FXML
    void chooseImg(ActionEvent event) {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Show the file chooser dialog
        SelectedImgFile = fileChooser.showOpenDialog(new Stage());

        if (SelectedImgFile != null) {
            // Load the selected image
            Image selectedImage = new Image(SelectedImgFile.getAbsolutePath());

            // Set the ImagePattern based on the selected image for the visible shape
            if (squareCopy.isVisible()) {
                squareCopy.setFill(new ImagePattern(selectedImage));
            } else if (circleCopy.isVisible()) {
                circleCopy.setFill(new ImagePattern(selectedImage));
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("Did not select a file");
        }
    }

    /**
     * Handles the action event for choosing an image. Opens a file chooser
     * dialog to select an image file, and sets the fill of the visible shape
     * (either square or circle).
     */
    void setSize() {

        try {

            double newSize = Double.parseDouble(txtFieldSize.getText());

            if (squareCopy.isVisible()) {
                squareCopy.setWidth(newSize);
                squareCopy.setHeight(newSize);
            } else if (circleCopy.isVisible()) {
                circleCopy.setRadius(newSize);
            }

            sizeSlider.setValue(newSize);

        } catch (NumberFormatException e) {

            SimulatorController.throwWarning("Please Enter A Valid Value!", "Format Error");

        }

    }

    /**
     * Handles the action event for setting the restitution value. Validates the
     * input from the text field to ensure it is within the valid range [0, 1].
     *
     * @param event The ActionEvent triggered by setting the restitution value.
     */
    @FXML
    void setRestitution(ActionEvent event) {

        try {

            //only valid values are between 0 and 1
            if (Float.parseFloat(txtFieldRestitution.getText()) > 1.00 || Float.parseFloat(txtFieldRestitution.getText()) < 0) {
                txtFieldRestitution.setText("" + 1.00);

                restitution = Float.parseFloat(txtFieldRestitution.getText());

                restitutionSlider.setValue(restitution);

            } else {
                restitution = Float.parseFloat(txtFieldRestitution.getText());
                restitutionSlider.setValue(restitution);
            }

        } catch (NumberFormatException e) {
            SimulatorController.throwWarning("Please Enter A Valid Value!", "Format Error");

        }

    }

    /**
     * Handles the action event for setting the density value. Validates the
     * input from the text field to ensure it is within the valid range [0, 1].
     *
     * @param event The ActionEvent triggered by setting the density value.
     */
    @FXML
    void setDensity(ActionEvent event) {

        try {

            //only valid values are between 0 and 1
            if (Float.parseFloat(txtFieldDensity.getText()) > 1.00 || Float.parseFloat(txtFieldDensity.getText()) < 0) {
                txtFieldDensity.setText("" + 1.00);

                density = Float.parseFloat(txtFieldDensity.getText());

                densitySlider.setValue(density);

            } else if (txtFieldDensity.getText().trim().equals("")) {
                // if the text field is empty

                //default value
                density = 0.3f;
                densitySlider.setValue(density);

            } else {
                density = Float.parseFloat(txtFieldDensity.getText());
                densitySlider.setValue(density);
            }

        } catch (NumberFormatException e) {
            
            SimulatorController.throwWarning("Please Enter A Valid Value!", "Format Error");
            
        }

    }

    /**
     * Handles the change event for sliders. Updates the corresponding
     * attributes and text fields based on the slider that triggered the event.
     * Handles densitySlider, restitutionSlider, and sizeSlider separately.
     *
     * @param event The MouseEvent triggered by changing the sliders.
     */
    @FXML
    void onSliderChange(MouseEvent event) {
        Object source = event.getSource();

        if (source == densitySlider) {

            try {

                density = (float) densitySlider.getValue();

                txtFieldDensity.setText(String.format("%.0f", densitySlider.getValue()));

            } catch (Exception e) {

                System.out.println("error");

            }

        } else if (source == restitutionSlider) {

            restitution = (float) restitutionSlider.getValue();

            txtFieldRestitution.setText(String.format("%.0f", restitutionSlider.getValue()));

        } else if (source == sizeSlider) {

            txtFieldSize.setText(String.format("%.0f", sizeSlider.getValue()));

            double newSize = sizeSlider.getValue();

            if (squareCopy.isVisible()) {
                squareCopy.setWidth(newSize);
                squareCopy.setHeight(newSize);
            } else if (circleCopy.isVisible()) {
                circleCopy.setRadius(newSize);
            }

        }

    }

    /**
     * Handles the change event for text fields. Calls corresponding methods to
     * handle the change based on the text field that triggered the event.
     * Handles txtFieldDensity, txtFieldRestitution, and txtFieldSize
     * separately.
     *
     * @param event The ActionEvent triggered by changing the text fields.
     */
    @FXML
    void onTextChange(ActionEvent event) {
        Object source = event.getSource();

        if (source == txtFieldDensity) {

            setDensity(event);

        } else if (source == txtFieldRestitution) {

            setRestitution(event);

        } else if (source == txtFieldSize) {

            setSize();
        }

    }

    /**
     * Handles the mouse click event for the circle. Sets focus on the circle,
     * updates UI elements, and sets the initial values for size, density, and
     * restitution.
     *
     * @param event The MouseEvent triggered by clicking on the circle shape.
     */
    @FXML
    void circleClick(MouseEvent event) {

        circleCopy.requestFocus();

        txtFieldDensity.setText("");
        txtFieldRestitution.setText("");

        sizeSlider.setMax(90);
        sizeSlider.setMin(5);
        txtFieldSize.setPromptText("");
        txtFieldSize.setPromptText("5-90");

        sizeSlider.setValue(circle.getRadius());
        circleCopy.setRadius(circle.getRadius());

        circleCopy.setFill(Color.WHITE);
        ColourPicker.setValue((Color) circleCopy.getFill());
        squareCopy.setVisible(false);
        circleCopy.setVisible(true);
    }

    /**
     * Handles the mouse click event for the rectangle. Sets focus on the
     * square, updates UI elements, and sets the initial values for size,
     * density, and restitution.
     *
     * @param event The MouseEvent triggered by clicking on the square shape.
     */
    @FXML
    void rectClick(MouseEvent event) {

        squareCopy.requestFocus();

        txtFieldDensity.setText("");
        txtFieldRestitution.setText("");

        sizeSlider.setMax(150);
        sizeSlider.setMin(5);
        txtFieldSize.setPromptText("");
        txtFieldSize.setPromptText("5-150");

        sizeSlider.setValue(square.getHeight());
        System.out.println(square.getHeight());
        squareCopy.setWidth(square.getWidth());
        squareCopy.setHeight(square.getHeight());

        squareCopy.setFill(Color.WHITE);
        ColourPicker.setValue((Color) squareCopy.getFill());
        squareCopy.setVisible(true);
        circleCopy.setVisible(false);
    }

    /**
     * Handles the mouse hover enter event for the circle shape. Sets the
     * circle's stroke color to the specified border color.
     *
     * @param event The MouseEvent triggered by entering the hover area of the
     * circle.
     */
    @FXML
    void circleHoverEnter(MouseEvent event) {
        circle.setStroke(borderColour);
    }

    /**
     * Handles the mouse hover exit event for the circle shape. Resets the
     * circle's stroke color to black.
     *
     * @param event The MouseEvent triggered by exiting the hover area of the
     * circle.
     */
    @FXML
    void circleHoverExit(MouseEvent event) {
        circle.setStroke(Color.BLACK);
    }

    /**
     * Handles the mouse hover enter event for the square shape. Sets the
     * square's stroke color to the specified border color.
     *
     * @param event The MouseEvent triggered by entering the hover area of the
     * square.
     */
    @FXML
    void squareHoverEnter(MouseEvent event) {
        square.setStroke(borderColour);
    }

    /**
     * Handles the mouse hover Exit event for the square shape. Resets the
     * square's stroke color to black.
     *
     * @param event The MouseEvent triggered by exiting the hover area of the
     * square.
     */
    @FXML
    void squareHoverExit(MouseEvent event) {
        square.setStroke(Color.BLACK);
    }

    /**
     * Handles the action event for returning to the home page. Loads the
     * "Home.fxml" file and initializes the HomeController to display the home
     * page.
     *
     * @param event The ActionEvent triggered by clicking the return home
     * button.
     */
    @FXML
    void returnHome(ActionEvent event) {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }

    /**
     * Initializes the controller. Adjusts initial positions and appearance
     * settings for square and circle shapes, sets up listeners for sliders, and
     * configures default values for restitution, density, and size. This method
     * is typically called once when the controller is first initialized.
     */
    @Override
    public void init() {

        // Make scaling up the square look a bit nicer
        squareCopy.setTranslateX(squareCopy.getX() + squareCopy.getWidth() / 2);
        squareCopy.setTranslateY(squareCopy.getY() + squareCopy.getHeight() / 2);

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            txtFieldSize.setText(String.format("%.2f", newValue));

            if (squareCopy.isVisible()) {

                squareCopy.setWidth(sizeSlider.getValue());
                squareCopy.setHeight(sizeSlider.getValue());

            } else if (circleCopy.isVisible()) {
                circleCopy.setRadius(sizeSlider.getValue());
            }

        });

        restitutionSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            txtFieldRestitution.setText(String.format("%.2f", newValue));

            restitution = (float) restitutionSlider.getValue();

        });

        densitySlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            txtFieldDensity.setText(String.format("%.2f", newValue));

            density = (float) densitySlider.getValue();

        });
        double centerX = MainApp.WIDTH / 2;
        double centerY = MainApp.HEIGHT / 2;

        squareCopy.setFill(Color.WHITE);
        circleCopy.setFill(Color.WHITE);

        borderPane.getChildren().addAll(squareCopy, circleCopy);

        squareCopy.setLayoutX(centerX - squareCopy.getWidth());
        squareCopy.setLayoutY(centerY - squareCopy.getHeight());

        circleCopy.setLayoutX(centerX);
        circleCopy.setLayoutY(centerY);

        squareCopy.setVisible(false);
        circleCopy.setVisible(false);

    }

}
