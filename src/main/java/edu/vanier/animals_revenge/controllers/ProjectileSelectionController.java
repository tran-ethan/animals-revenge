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

        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                System.out.println("Please work");

            }

        };

        //adding the custom projectiles to the hbox
        for (int i = 0; i < customProjectiles.size(); i++) {

            final int finalIndex = i;
            System.out.println(customProjectiles.get(i));
            customProjectiles.get(i).setOnMouseEntered((event) -> {

                customProjectiles.get(finalIndex).setStroke(borderColor);
                customProjectiles.get(finalIndex).setStrokeWidth(5);
                customProjectiles.get(finalIndex).requestFocus();
                

            });

            customObjectRow.getChildren().add(conv.createShapeFromProjectile(customProjectiles.get(i)));
        }

    }

}
