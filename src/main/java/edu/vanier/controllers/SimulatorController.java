package edu.vanier.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class SimulatorController {

    @FXML
    private Slider rotateSlider;

    @FXML
    private Slider scaleSlider;

    @FXML
    public void initialize() {
        System.out.println("simulator screen");
    }

}
