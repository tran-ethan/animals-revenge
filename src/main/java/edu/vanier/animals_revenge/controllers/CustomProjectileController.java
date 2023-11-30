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
import edu.vanier.animals_revenge.windows.Loading;
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
import javafx.scene.layout.AnchorPane;
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

    private boolean savedChangesHasAlreadyPressed = false;

    File SelectedImgFile;

    File SaveFile;

    CustomProjectileSquare squareProjectile;
    CustomProjectileCircle circleProjectile;

    //The shape copies the shapes that appear in the middle of the screen
    @FXML
    private Circle circleCopy;

    @FXML
    private Rectangle squareCopy;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ColorPicker ColourPicker;

    @FXML
    private Circle circle;

    @FXML
    private Rectangle square;

    @FXML
    private Slider sizeSlider;

    @FXML
    private TextField sliderTextValue;

    //save button (not to be confused with Save As button) event handler
    @FXML
    void Save(ActionEvent event) throws MalformedURLException {

        //if the primary save button has not already been pressed than it should act the same as the
        //Save As button, if the button has already been pressed than it should simply update the file without 
        //changing the file path
        if (!savedChangesHasAlreadyPressed) {
            SaveAsChanges(event);
        } else {

            //Updating the already saved file
            Shape shape = null;
            Color color;

            //This determines which shape the user has selected
            if (squareCopy.isVisible()) {
                shape = squareCopy;
            } else if (circleCopy.isVisible()) {
                shape = circleCopy;
            }

            //sets the color that the user has selected
            color = ColourPicker.getValue();

            //sets the shape that the user has selected and creates an object based on the other parameters
            if (shape == squareCopy) {

                double width = squareCopy.getWidth();
                double height = squareCopy.getHeight();
                String StringColor = color.toString().replace("0x", "");

                //This determines if the projectile has an image on it or not and saves the file accordingly
                if (SelectedImgFile != null) {
                    String imgPath = SelectedImgFile.getAbsolutePath();
                    squareProjectile = new CustomProjectileSquare(width, height, StringColor, imgPath);
                } else {
                    squareProjectile = new CustomProjectileSquare(width, height, StringColor);
                }
                
                customProjectiles.add(squareProjectile);
                serialize(SaveFile.getAbsolutePath(), squareProjectile);

            } else if (shape == circleCopy) {

                double radius = circleCopy.getRadius();
                String StringColor = color.toString().replace("0x", "");

                if (SelectedImgFile != null) {
                    String imgPath = SelectedImgFile.getAbsolutePath();
                    circleProjectile = new CustomProjectileCircle(radius, StringColor, imgPath);
                } else {
                    circleProjectile = new CustomProjectileCircle(radius, StringColor);
                }

                System.out.println(circleProjectile.getColor());

                customProjectiles.add(circleProjectile);
                serialize(SaveFile.getAbsolutePath(), circleProjectile);

            }

        }

    }

    //Save as button event handler (located in the file menu button)
    @FXML
    void SaveAsChanges(ActionEvent event) throws MalformedURLException {

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
                        squareProjectile = new CustomProjectileSquare(width, height, StringColor, imgPath);
                    } else {
                        squareProjectile = new CustomProjectileSquare(width, height, StringColor);
                    }
                    
                    serialize(SaveFile.getAbsolutePath(), squareProjectile);
                    
                    customProjectiles.add(squareProjectile);
                    
                    savedChangesHasAlreadyPressed = true;

                } else if (shape == circleCopy) {

                    double radius = circleCopy.getRadius();
                    String StringColor = color.toString().replace("0x", "");

                    if (SelectedImgFile != null) {
                        String imgPath = SelectedImgFile.getAbsolutePath();
                        circleProjectile = new CustomProjectileCircle(radius, StringColor, imgPath);
                    } else {
                        circleProjectile = new CustomProjectileCircle(radius, StringColor);
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

        try {
            Loading load = new Loading();
        } catch (InterruptedException ex) {
            logger.info("Loader Failed During Serialization Process");
        }

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

    @FXML
    void setSize(MouseEvent event) {

        sliderTextValue.setText("" + sizeSlider.getValue());

        double newSize = sizeSlider.getValue();

        if (squareCopy.isVisible()) {
            squareCopy.setWidth(newSize);
            squareCopy.setHeight(newSize);
        } else if (circleCopy.isVisible()) {
            circleCopy.setRadius(newSize);
        }

    }

    @FXML
    void circleClick(MouseEvent event) {

        sizeSlider.setMax(90);
        sizeSlider.setMin(5);

        sizeSlider.setValue(circle.getRadius());
        circleCopy.setRadius(circle.getRadius());

        circleCopy.setFill(Color.WHITE);
        ColourPicker.setValue((Color) circleCopy.getFill());
        squareCopy.setVisible(false);
        circleCopy.setVisible(true);
    }

    @FXML
    void rectClick(MouseEvent event) {

        sizeSlider.setMax(150);
        sizeSlider.setMin(5);

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

        sliderTextValue.setDisable(true);

        sizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            sliderTextValue.setText(String.format("%.2f", newValue));

            if (squareCopy.isVisible()) {

                squareCopy.setWidth(sizeSlider.getValue());
                squareCopy.setHeight(sizeSlider.getValue());

            } else if (circleCopy.isVisible()) {
                circleCopy.setRadius(sizeSlider.getValue());
            }

        });
        double centerX = MainApp.WIDTH / 2;
        double centerY = MainApp.HEIGHT / 2;

        squareCopy.setFill(Color.WHITE);
        circleCopy.setFill(Color.WHITE);

        anchorPane.getChildren().addAll(squareCopy, circleCopy);

        squareCopy.setLayoutX(centerX - squareCopy.getWidth());
        squareCopy.setLayoutY(centerY - squareCopy.getHeight());

        circleCopy.setLayoutX(centerX);
        circleCopy.setLayoutY(centerY);

        squareCopy.setVisible(false);
        circleCopy.setVisible(false);

    }
    

    
    
    
    
}
