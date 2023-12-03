/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.controllers.ParametersController;
import edu.vanier.animals_revenge.controllers.ProjectileSelectionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Parameters class represents a JavaFX Stage for displaying a UI form loaded from
 * the "Parameters.fxml" file. It extends the JavaFX Stage class and is associated with
 * a controller class, ParametersController, to handle the interaction and logic of the UI.
 *
 * The class is designed to load the UI form from the FXML file, set up the associated
 * controller, and display the form as a scene within the stage.
 *
 * @author Zachary Tremblay
 */
public class Parameters extends Stage {
    
    /**
     * Constructs a Parameters object by loading the UI form from the "Parameters.fxml"
     * file and associating it with a new instance of the ParametersController class.
     * The loaded form is set as the scene for the stage.
     */

    public Parameters() {
        Pane root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/ui/Parameters.fxml"));
        loader.setController(new ParametersController());
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Parameters.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);

        setScene(scene);

    }

}
