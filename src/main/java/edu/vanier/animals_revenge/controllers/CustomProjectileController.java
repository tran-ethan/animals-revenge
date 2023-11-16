/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

        if (squareCopy.isVisible()) {
            //TODO
        } else if (triangleCopy.isVisible()) {
            //TODO
        } else {

            
        }

    }

    @FXML
    void circleClick(MouseEvent event) {

        sizeTXT.setText("" + circleCopy.getRadius());
        ColourPicker.setValue((Color) circleCopy.getFill());
        squareCopy.setVisible(false);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(true);
    }

    @FXML
    void rectClick(MouseEvent event) {

        //height and width are always the same in a square so it doesnt matter which one is used
        sizeTXT.setText("" + squareCopy.getHeight());
        ColourPicker.setValue((Color) squareCopy.getFill());
        squareCopy.setVisible(true);
        triangleCopy.setVisible(false);
        circleCopy.setVisible(false);
    }

    @FXML
    void triangleClick(MouseEvent event) {

        ColourPicker.setValue((Color) triangleCopy.getFill());
        squareCopy.setVisible(false);
        triangleCopy.setVisible(true);
        circleCopy.setVisible(false);
    }

    @Override
    public void init() {

        double centerX = MainApp.WIDTH / 2;
        double centerY = MainApp.HEIGHT / 2;

        double scaleFactor = 1.75;

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
