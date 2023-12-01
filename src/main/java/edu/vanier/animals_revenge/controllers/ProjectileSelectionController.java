/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.util.ProjectileConverterController;
import edu.vanier.animals_revenge.models.CustomProjectile;
import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author macke
 */
public class ProjectileSelectionController {

    @FXML
    public HBox customObjectRow;

    @FXML
    private Button BTNImport;

    public static CustomProjectile finalProjectile;

    public static final Color borderColor = Color.BLACK;

    ArrayList<CustomProjectile> customProjectiles;

    FileChooser fileChooser = new FileChooser();

    @FXML
    void importProjectile(ActionEvent event) {

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            CustomProjectile p = CustomProjectileController.deserialize(selectedFile.getAbsolutePath());

            customProjectiles.add(p);

            // Add the new projectile to the HBox
            Shape projectile = new ProjectileConverterController().createShapeFromProjectile(p);

            projectile.setOnMouseEntered(
                    
                    (mouseEvent) -> projectile.setStroke(borderColor)
            
            );
            
            
            projectile.setOnMouseExited(
                    
                    (mouseEvent) -> projectile.setStroke(Color.TRANSPARENT)
            
            );
            projectile.setOnMouseClicked((mouseEvent) -> {
                
                
                ProjectileSelectionController.finalProjectile = p;
                ((Stage) projectile.getScene().getWindow()).close();
                
                
            });

            customObjectRow.getChildren().add(projectile);

        } else {
            SimulatorController.throwWarning("No File Chosen!", "File Error");
        }

    }

    @FXML
    public void initialize() {

        customObjectRow.getChildren().clear();
        
        ProjectileConverterController conv = new ProjectileConverterController();

        customProjectiles = CustomProjectileController.customProjectiles;

        //adding the custom projectiles to the hbox
        //normal rectangle works but custom projectile doesn't
        for (int i = 0; i < customProjectiles.size(); i++) {

            Shape projectile = conv.createShapeFromProjectile(customProjectiles.get(i));

            final int finalIndex = i;

            projectile.setOnMouseEntered(
                    (event) -> {

                        projectile.setStroke(borderColor);

                    });

            projectile.setOnMouseExited(
                    (event) -> {

                        projectile.setStroke(Color.TRANSPARENT);

                    });

            projectile.setOnMouseClicked((event) -> {

                finalProjectile = customProjectiles.get(finalIndex);
                
                ((Stage) projectile.getScene().getWindow()).close();

            });

            customObjectRow.getChildren().add(projectile);
        }

    }

    public static CustomProjectile getFinalProjectile() {
        return finalProjectile;
    }

    public static void setFinalProjectile(CustomProjectile finalProjectile) {
        ProjectileSelectionController.finalProjectile = finalProjectile;
    }

}
