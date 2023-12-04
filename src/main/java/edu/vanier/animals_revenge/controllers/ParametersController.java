/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.dsl.FXGL;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The ParametersController class is a controller for the UI form loaded from the
 * "Parameters.fxml" file. It handles the interaction and logic of the UI components,
 * such as the gravity setting TextField, graph CheckBox, and Apply Button.
 * <p>
 * The class is associated with the UI elements through JavaFX's FXML annotation,
 * and it initializes the UI components in the initialize() method. The controller
 * provides functionality to set the gravity in the FXGL game engine based on user input.
 * <p>
 * Additionally, the controller manages the state of a static boolean variable, isGraphOff,
 * which represents whether the graph is turned off.
 *
 * @author Zachary Tremblay
 */
public class ParametersController {
    
    /** TextField for entering the gravity setting. */
    @FXML
    private TextField gravityTextField;
    
    /** CheckBox for toggling the graphs. */
    @FXML
    private CheckBox graphCheckBox;

    /** Button for applying changes. */
    @FXML
    private Button btnApply;

    /** Static boolean indicating whether the graphs are turned off. */
    private static boolean isGraphOff = false;

    /**
     * Initializes the UI components and sets their initial states. This method
     * is called automatically by JavaFX after the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        
        graphCheckBox.setSelected(isGraphOff);

        btnApply.setOnAction((event) -> {
            boolean isDouble;
            boolean isEmpty = gravityTextField.getText().isEmpty();

            try {
                Double.parseDouble(gravityTextField.getText());
                isDouble = true;
            } catch (NumberFormatException numberFormatException) {
                isDouble = false;
            }

            if (isDouble) {
                double gravity = FXGL.getPhysicsWorld().toPixels(Double.parseDouble(gravityTextField.getText()));
                FXGL.getPhysicsWorld().setGravity(0, gravity);
                setIsGraphOff(graphCheckBox.isSelected());
                Stage stage = (Stage) btnApply.getScene().getWindow();
                stage.close();

            } else if (isEmpty) {
                double gravity = FXGL.getPhysicsWorld().toPixels(9.807);
                FXGL.getPhysicsWorld().setGravity(0, gravity);
                setIsGraphOff(graphCheckBox.isSelected());
                Stage stage = (Stage) btnApply.getScene().getWindow();
                stage.close();
            } else {
                System.out.println("Showing Alert ...");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Please enter a valid number for the gravity setting or leave blank for default value");
                alert.setHeaderText("Entered number is not valid");
                alert.show();
                gravityTextField.clear();
            }
        });
    }

    public static boolean isIsGraphOff() {
        return isGraphOff;
    }

    public static void setIsGraphOff(boolean isGraphOff) {
        ParametersController.isGraphOff = isGraphOff;
    }

}
