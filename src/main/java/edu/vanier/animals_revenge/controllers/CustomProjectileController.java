/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author macke
 */
public class CustomProjectileController implements UIController, Serializable {

    public static ArrayList<CustomProjectile> customProjectiles = new ArrayList<>();

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    private final static String FILE_EXTENSION = ".proj";

    public static final Color borderColor = Color.RED;

    private static float density;
    private static float restitution;

    private boolean savedChangesHasAlreadyPressed = false;

    File SelectedImgFile;

    File SaveFile;

    CustomProjectileSquare squareProjectile;

    CustomProjectileCircle circleProjectile;

    @FXML
    private Slider densitySlider;

    @FXML
    private Slider restitutionSlider;

    //The shape copies the shapes that appear in the middle of the screen
    @FXML
    private Circle circleCopy;

    @FXML
    private Rectangle squareCopy;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ColorPicker ColourPicker;

    @FXML
    private Circle circle;

    @FXML
    private Rectangle square;

    @FXML
    private TextField txtFieldDensity;

    @FXML
    private TextField txtFieldRestitution;

    @FXML
    private Slider sizeSlider;

    @FXML
    private TextField txtFieldSize;

    //Save as button event handler (located in the file menu button)
    @FXML
    void SaveAsChanges(ActionEvent event) throws MalformedURLException {

        if (txtFieldRestitution.getText().trim().equals("")) {
            restitution = 0.5f;
        }

        if (txtFieldDensity.getText().trim().equals("")) {
            // if the text field is empty
            density = 0.3f;
        }

        Shape shape = null;
        Color color;

        //if there is not shape visible than there is nothing to save and thus should not open the save dialogue
        if (squareCopy.isVisible() || circleCopy.isVisible()) {
            //Opens file chooser 
            FileChooser saveLocation = new FileChooser();

            //sets initial file name
            saveLocation.setInitialFileName("myCustomProjectile" + FILE_EXTENSION);

            //Provides the extension(s) the file will have
            saveLocation.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Custom Projectile", "*proj"), new FileChooser.ExtensionFilter("All Files", "*"));

            SaveFile = saveLocation.showSaveDialog(null);

            //if no file is selected
            if (SaveFile != null) {

                if (squareCopy.isVisible()) {
                    shape = squareCopy;
                } else if (circleCopy.isVisible()) {
                    shape = circleCopy;
                }

                color = ColourPicker.getValue();

                //creates a projecitle based on the parameters the user has selected
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

                    savedChangesHasAlreadyPressed = true;

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
                    savedChangesHasAlreadyPressed = true;

                }
            }
        } else {
            SimulatorController.throwWarning("Please Provide Properties To Save!", "Saving Error");
        }

    }

    public static void serialize(String filePath, CustomProjectile p) {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filePath))) {
            o.writeObject(p);
        } catch (IOException e) {
            System.out.println("Error during serilization process");
        }

    }

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

    @FXML
    void chooseColor(ActionEvent event) {

        if (squareCopy.isVisible()) {
            squareCopy.setFill(ColourPicker.getValue());
        } else {

            circleCopy.setFill(ColourPicker.getValue());

        }
    }

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

    void setSize() {

        double newSize = Double.valueOf(txtFieldSize.getText());

        if (squareCopy.isVisible()) {
            squareCopy.setWidth(newSize);
            squareCopy.setHeight(newSize);
        } else if (circleCopy.isVisible()) {
            circleCopy.setRadius(newSize);
        }

        sizeSlider.setValue(newSize);

    }

    @FXML
    void setRestitution(ActionEvent event) {

        //only valid values are between 0 and 1
        if (Float.valueOf(txtFieldRestitution.getText()) > 1.00 || Float.valueOf(txtFieldRestitution.getText()) < 0) {
            txtFieldRestitution.setText("" + 1.00);

            restitution = Float.valueOf(txtFieldRestitution.getText());

            restitutionSlider.setValue(restitution);

        } else {
            restitution = Float.valueOf(txtFieldRestitution.getText());
            restitutionSlider.setValue(restitution);
        }

    }

    @FXML
    void setDensity(ActionEvent event) {

        //only valid values are between 0 and 1
        if (Float.valueOf(txtFieldDensity.getText()) > 1.00 || Float.valueOf(txtFieldDensity.getText()) < 0) {
            txtFieldDensity.setText("" + 1.00);

            density = Float.valueOf(txtFieldDensity.getText());

            densitySlider.setValue(density);

        } else if (txtFieldDensity.getText().trim().equals("")) {
            // if the text field is empty

            //default value
            density = 0.3f;
            densitySlider.setValue(density);

        } else {
            density = Float.valueOf(txtFieldDensity.getText());
            densitySlider.setValue(density);
        }

    }

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

    @FXML
    void circleHoverEnter(MouseEvent event) {
        circle.setStroke(borderColor);
    }

    @FXML
    void circleHoverExit(MouseEvent event) {
        circle.setStroke(Color.BLACK);
    }

    @FXML
    void squareHoverEnter(MouseEvent event) {
        square.setStroke(borderColor);
    }

    @FXML
    void squareHoverExit(MouseEvent event) {
        square.setStroke(Color.BLACK);
    }

    @FXML
    void returnHome(ActionEvent event) {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }

    @FXML
    void OpenAboutPage(ActionEvent event) {

        HelpButtonController help = new HelpButtonController();

    }

    @Override
    public void init() {

        //make scaling up the square look a bit nicer
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
