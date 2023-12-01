/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.util.ProjectileConverterController;
import edu.vanier.animals_revenge.models.CustomProjectile;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 *
 * @author macke
 */
public class ProjectileSelectionController {

    @FXML
    public HBox customObjectRow;

    public static CustomProjectile finalProjectile;

    public static final Color borderColor = Color.BLACK;

    @FXML
    public void initialize() {

        ProjectileConverterController conv = new ProjectileConverterController();
        
        ArrayList<CustomProjectile> customProjectiles = CustomProjectileController.customProjectiles;

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
