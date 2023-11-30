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
 *
 * @author macke
 */
public class Selection extends Stage {

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
