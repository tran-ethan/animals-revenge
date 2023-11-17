/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author macke
 */
public class CustomProjectileController implements UIController {

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    static Color borderColor = Color.RED;

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
    private Button btnApply;

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
    private HBox circleHbox;

    @FXML
    private HBox rectHbox;

    @FXML
    private HBox triangleHbox;

    @FXML
    void ApplyChanges(ActionEvent event) {

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
        //TODO
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
                //numbers stay small: (1 to 10) and (-1 to -10)
                enteredValue = enteredValue * 5;

                triangleCopy.getPoints().setAll(-85.0 - enteredValue / 2, 68.0, 85.0 + enteredValue / 2, 68.0, 0.0, -102.0 - enteredValue);

            } else {
                sizeTXT.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                LBLwarning.setVisible(true);
            }

        } else {
            sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        }

    }

    @FXML
    void circleClick(MouseEvent event) {

        circleCopy.setFill(Color.WHITE);
        sizeTXT.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
        sizeTXT.setText("" + circleCopy.getRadius());
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
        circle.setStroke(Color.TRANSPARENT);
    }

    @FXML
    void squareHoverEnter(MouseEvent event) {
        square.setStroke(borderColor);
    }

    @FXML
    void squareHoverExit(MouseEvent event) {
        square.setStroke(Color.TRANSPARENT);
    }

    @FXML
    void triangleHoverEnter(MouseEvent event) {
        triangle.setStroke(borderColor);
    }

    @FXML
    void triangleHoverExit(MouseEvent event) {
        triangle.setStroke(Color.TRANSPARENT);
    }

    //Not sure if we should we these methods or not:
    @FXML
    void circleHboxHoverEnter(MouseEvent event) {
        //circleHbox.setStyle("-fx-border-color: gray ; -fx-border-width: 1px ; -fx-border-radius: 10px;");
    }

    @FXML
    void circleHboxHoverExit(MouseEvent event) {
        //circleHbox.setStyle("-fx-border-color: Transparent ; -fx-border-width: 0px ;");
    }

    @FXML
    void rectHboxHoverEnter(MouseEvent event) {
        //rectHbox.setStyle("-fx-border-color: gray ; -fx-border-width: 1px ; -fx-border-radius: 10px;");
    }

    @FXML
    void rectHboxHoverExit(MouseEvent event) {
        //rectHbox.setStyle("-fx-border-color: Transparent ; -fx-border-width: 0px ;");
    }

    @FXML
    void triangleHboxHoverEnter(MouseEvent event) {
        //triangleHbox.setStyle("-fx-border-color: gray ; -fx-border-width: 1px ; -fx-border-radius: 10px;");
    }

    @FXML
    void triangleHboxHoverExit(MouseEvent event) {
        //triangleHbox.setStyle("-fx-border-color: Transparent ; -fx-border-width: 0px ;");
    }

    @Override
    public void init() {

        LBLwarning.setVisible(false);

        double centerX = MainApp.WIDTH / 2;
        double centerY = MainApp.HEIGHT / 2;

        double scaleFactor = 1.70;

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

        squareCopy.setWidth(square.getWidth() * scaleFactor);
        squareCopy.setHeight(square.getHeight() * scaleFactor);

        circleCopy.setRadius(circleCopy.getRadius() * scaleFactor);

        ObservableList<Double> points = triangleCopy.getPoints();

        for (int i = 0; i < points.size(); i++) {
            points.set(i, points.get(i) * scaleFactor);
        }
        squareCopy.setVisible(false);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(false);

    }
}
