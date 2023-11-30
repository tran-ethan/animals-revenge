/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.models.CustomProjectile;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 *
 * @author macke
 */
public class ProjectileSelectionController {

    @FXML
    public HBox customObjectRow;

    public static final Color borderColor = Color.BLACK;

    @FXML
    public void initialize() {

        ProjectileConverterController conv = new ProjectileConverterController();

        ArrayList<CustomProjectile> customProjectiles = CustomProjectileController.customProjectiles;

        for (int i = 0; i < customProjectiles.size(); i++) {

            customObjectRow.getChildren().add(conv.createShapeFromProjectile(customProjectiles.get(i)));

            //variables in the lambda expression must be final
            final int finalIndex = i;

            customProjectiles.get(i).setOnMouseEntered(
                    (event) -> {

                        customProjectiles.get(finalIndex).setStroke(borderColor);

                    });
            
            customProjectiles.get(i).setOnMouseExited((event) -> {
            
                customProjectiles.get(finalIndex).setStroke(Color.TRANSPARENT);
            
            });
        }
    }

}
