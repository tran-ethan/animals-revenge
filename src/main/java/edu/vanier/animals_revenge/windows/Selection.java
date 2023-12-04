/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.controllers.ProjectileSelectionController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The {@code Selection} class represents a custom JavaFX stage for displaying a
 * projectile selection window. It extends the {@link javafx.stage.Stage} class
 * and is responsible for loading the FXML file associated with the projectile
 * selection window. The window contains a {@link ProjectileSelectionController}
 * as its controller.
 * <p>
 * This class initializes the stage by loading the FXML file, setting the
 * controller, and creating the associated scene. Instances of this class are
 * expected to be used to show the projectile selection window in the
 * application's UI.
 * </p>
 *
 * @author macke
 * @version 1.0
 */
public class Selection extends Stage {

    /**
     * Constructs a new {@code Selection} object. It initializes the stage by
     * loading the FXML file associated with the projectile selection window,
     * setting the {@link ProjectileSelectionController} as the controller, and
     * creating the scene.
     */
    public Selection() {

        Pane root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/ui/ProjectileSelection.fxml"));

        loader.setController(new ProjectileSelectionController());

        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Selection.class.getName()).log(Level.SEVERE, null, ex);
        }

        Scene scene = new Scene(root);

        setScene(scene);

    }

}
