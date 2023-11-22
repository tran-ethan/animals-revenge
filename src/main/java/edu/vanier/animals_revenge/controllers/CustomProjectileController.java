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
import java.util.logging.Level;
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

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    static Color borderColor = Color.RED;

    private boolean savedChangesHasAlreadyPressed = false;

    File SelectedImgFile;

    File SaveFile;

    CustomProjectileSquare squareProjectile;
    CustomProjectileCircle circleProjectile;

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

    //this is the save button
    @FXML
    void Save(ActionEvent event) throws MalformedURLException {

        if (!savedChangesHasAlreadyPressed) {
            SaveChanges(event);
        } else {

            Shape shape = null;
            double size;
            Color color;
            Image img;

            if (squareCopy.isVisible()) {
                shape = squareCopy;
            } else if (circleCopy.isVisible()) {
                shape = circleCopy;
            }

            size = sizeSlider.getValue();
            color = ColourPicker.getValue();

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

            serialize(SaveFile.getAbsolutePath(), circleProjectile);

        }

        }

    }

    //this is the file -> save as button
    @FXML
    void SaveChanges(ActionEvent event) throws MalformedURLException {

        savedChangesHasAlreadyPressed = true;

        Shape shape = null;
        double size;
        Color color;
        Image img;

        FileChooser saveLocation = new FileChooser();

        saveLocation.setInitialFileName("myCustomProjectile");

        saveLocation.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Object file", "*obj"), new FileChooser.ExtensionFilter("All Files", "*"));

        SaveFile = saveLocation.showSaveDialog(null);

        if (squareCopy.isVisible()) {
            shape = squareCopy;
        } else if (circleCopy.isVisible()) {
            shape = circleCopy;
        }

        size = sizeSlider.getValue();
        color = ColourPicker.getValue();

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

            serialize(SaveFile.getAbsolutePath(), circleProjectile);

        }

    }

    public static void serialize(String filePath, CustomProjectile p) {
        
        try {
            Loading load = new Loading();
        } catch (InterruptedException ex) {
            logger.info("Loader failed");
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
                System.out.println("Something went wrong");
            }

        } catch (IOException | ClassNotFoundException e) {

            System.out.println("Error during deserilization process");
            e.printStackTrace();
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
        sizeSlider.setMin(1);

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
        sizeSlider.setMin(1);

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
