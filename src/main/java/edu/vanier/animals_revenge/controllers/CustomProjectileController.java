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
import edu.vanier.animals_revenge.models.CustomProjectileTriangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
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
    
    File SelectedImgFile;
    
    CustomProjectileSquare squareProjectile;
    CustomProjectileCircle circleProjectile;

    @FXML
    private Circle circleCopy;

    @FXML
    private Rectangle squareCopy;

    @FXML
    private Polygon triangleCopy;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ColorPicker ColourPicker;

    @FXML
    private Label LBLsize;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnImg;

    @FXML
    private Circle circle;

    @FXML
    private TextField sizeTXT;

    @FXML
    private Rectangle square;

    @FXML
    private Polygon triangle;

    @FXML
    private Label LBLwarning;

    @FXML
    void SaveChanges(ActionEvent event) throws MalformedURLException {

        Shape shape = null;
        double size;
        Color color;
        Image img;

        FileChooser fileSelection = new FileChooser();

        fileSelection.setInitialFileName("myCustomProjectile");

        fileSelection.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Object file", "*obj"), new FileChooser.ExtensionFilter("All Files", "*"));

        File file = fileSelection.showSaveDialog(null);

        if (squareCopy.isVisible()) {
            shape = squareCopy;
        } else if (circleCopy.isVisible()) {
            shape = circleCopy;
        } else if (triangleCopy.isVisible()) {

            shape = triangleCopy;
        }

        size = Double.valueOf(sizeTXT.getText());
        color = ColourPicker.getValue();

        if (shape == squareCopy) {
            
            double width = squareCopy.getWidth();
            double height = squareCopy.getHeight();
            String StringColor = color.toString().replace("0x", "");
            
            if(SelectedImgFile != null) {
                String imgPath = SelectedImgFile.getAbsolutePath();
                squareProjectile = new CustomProjectileSquare(width, height, StringColor, imgPath);
            } else {
                squareProjectile = new CustomProjectileSquare(width, height, StringColor);
            }

            serialize(file.getAbsolutePath(), squareProjectile);

        } else if (shape == circleCopy) {

            double radius = circleCopy.getRadius();
            String StringColor = color.toString().replace("0x", "");
            
            if(SelectedImgFile != null) {
                String imgPath = SelectedImgFile.getAbsolutePath();
                circleProjectile = new CustomProjectileCircle(radius, StringColor, imgPath);
            } else {
                circleProjectile = new CustomProjectileCircle(radius, StringColor);
            }
            
            System.out.println(circleProjectile.getColor());

            serialize(file.getAbsolutePath(), circleProjectile);
            
        } else if (shape == triangleCopy) {
            CustomProjectile TriangleProjectile = new CustomProjectileTriangle();
            serialize(file.getAbsolutePath(), TriangleProjectile);
            
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
                return (CustomProjectileTriangle) obj;
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
        } else if (triangleCopy.isVisible()) {
            triangleCopy.setFill(ColourPicker.getValue());
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
            } else if (triangleCopy.isVisible()) {
                triangleCopy.setFill(new ImagePattern(selectedImage));
            }
        } else {
            System.out.println("Did not select a file");
        }
    }

    @FXML
    void setSize(ActionEvent event) {

        double enteredValue = 0;

        try {
            enteredValue = Double.valueOf(sizeTXT.getText());
        } catch (NumberFormatException e) {
            sizeTXT.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
            return;
        }

        if (squareCopy.isVisible()) {

            sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");

            if (enteredValue >= -15 && enteredValue <= 15) {
                double scaleFactor = 1 + enteredValue / 100.00;

                LBLwarning.setVisible(false);
                squareCopy.setWidth((square.getWidth() + 50) * scaleFactor);
                squareCopy.setHeight((square.getHeight() + 50) * scaleFactor);
            } else {
                sizeTXT.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                LBLwarning.setVisible(true);
            }

        } else if (triangleCopy.isVisible()) {

            //points of original triangle
            //-85.0, 68.0, 85.0, 68.0, 0.0, -102.0
            if (enteredValue >= -15 && enteredValue <= 15) {

                LBLwarning.setVisible(false);
                sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
                //numbers stay small: (1 to 15) and (-1 to -15)
                enteredValue = enteredValue * 5;

                triangleCopy.getPoints().setAll(-85.0 - enteredValue / 2, 68.0, 85.0 + enteredValue / 2, 68.0, 0.0, -102.0 - enteredValue);

            } else {
                sizeTXT.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                LBLwarning.setVisible(true);
            }

        } else if (circleCopy.isVisible()) {

            if (enteredValue >= -15 && enteredValue <= 15) {
                LBLwarning.setVisible(false);
                sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
                sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");

                double scaleFactor = 1 + enteredValue / 20.00;

                circleCopy.setRadius(circle.getRadius() * 1.7 * scaleFactor);
            } else {
                sizeTXT.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                LBLwarning.setVisible(true);
            }

        }

    }

    @FXML
    void circleClick(MouseEvent event) {

        circleCopy.setFill(Color.WHITE);
        sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        sizeTXT.setText("0");
        ColourPicker.setValue((Color) circleCopy.getFill());
        squareCopy.setVisible(false);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(true);
    }

    @FXML
    void rectClick(MouseEvent event) {

        sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        sizeTXT.setText("0");
        //height and width are always the same in a square so it doesnt matter which one is used
        squareCopy.setWidth(square.getWidth() + 50);
        squareCopy.setHeight(square.getHeight() + 50);

        squareCopy.setFill(Color.WHITE);
        ColourPicker.setValue((Color) squareCopy.getFill());
        squareCopy.setVisible(true);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(false);
    }

    @FXML
    void triangleClick(MouseEvent event) {
        triangleCopy.setFill(Color.WHITE);
        ColourPicker.setValue((Color) triangleCopy.getFill());
        triangleCopy.getPoints().setAll(-85.0, 68.0, 85.0, 68.0, 0.0, -102.0);
        sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        sizeTXT.setText("0");
        squareCopy.setVisible(false);
        triangleCopy.setVisible(true);
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
    void triangleHoverEnter(MouseEvent event) {
        triangle.setStroke(borderColor);
    }

    @FXML
    void triangleHoverExit(MouseEvent event) {
        triangle.setStroke(Color.BLACK);
    }

    @Override
    public void init() {
        
        
        LBLwarning.setVisible(false);

        double centerX = MainApp.WIDTH / 2;
        double centerY = MainApp.HEIGHT / 2;

        double scale = 1.70;

        squareCopy.setFill(Color.WHITE);
        circleCopy.setFill(Color.WHITE);
        triangleCopy.setFill(Color.WHITE);

        anchorPane.getChildren().addAll(squareCopy, triangleCopy, circleCopy);

        squareCopy.setLayoutX(centerX - squareCopy.getWidth());
        squareCopy.setLayoutY(centerY - squareCopy.getHeight());

        circleCopy.setLayoutX(centerX);
        circleCopy.setLayoutY(centerY);

        triangleCopy.setLayoutX(centerX);
        triangleCopy.setLayoutY(centerY);

        squareCopy.setWidth(square.getWidth() * scale);
        squareCopy.setHeight(square.getHeight() * scale);

        circleCopy.setRadius(circleCopy.getRadius() * scale);

        ObservableList<Double> points = triangleCopy.getPoints();

        for (int i = 0; i < points.size(); i++) {
            points.set(i, points.get(i) * scale);
        }
        squareCopy.setVisible(false);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(false);

    }
}
