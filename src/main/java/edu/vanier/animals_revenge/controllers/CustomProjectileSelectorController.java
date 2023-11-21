/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.models.CustomProjectileTriangle;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author 2268182
 */

//Currently not in use, will continue if enough time

public class CustomProjectileSelectorController {

    @FXML
    private Button BTNCustom;

    private boolean BTNCustomHasBeenPressed;
    
    private Stage stage;
    
    private CustomProjectileCircle circle;
    
    private CustomProjectileSquare square;
    
    private CustomProjectileTriangle triangle;

    @FXML
    void SelectProjectile(ActionEvent event) {

        BTNCustomHasBeenPressed = true;
        
        FileChooser fileChooser = new FileChooser();

        File file = fileChooser.showOpenDialog(null);

        CustomProjectile Projectile = (CustomProjectile) CustomProjectileController.deserialize(file.getAbsolutePath());

        System.out.println(Projectile.getClass().getSimpleName());

        if (Projectile instanceof CustomProjectileCircle) {

            circle = (CustomProjectileCircle)Projectile;
            
            System.out.println(circle.getImgPath());
            System.out.println("This is a circle");

        } else if (Projectile instanceof CustomProjectileSquare) {

            square = (CustomProjectileSquare)Projectile;
            
            
            System.out.println(square.getImgPath());
            
            System.out.println("This is a square");

        } else {
            
            triangle = (CustomProjectileTriangle)Projectile;
            
            System.out.println(triangle.getImgPath());
            System.out.println("This is a triangle");
            
            
        }

        stage.close();
    }
    
    public CustomProjectile determineProjectile(CustomProjectileCircle c, CustomProjectileSquare s, CustomProjectileTriangle t) {
        
        if (c != null) {
            System.out.println("cirlce is not null");
            return c;
        } else if (s != null) {
            return s;
        } else if (t != null) {
            return t;
        } else {
            System.out.println("An error has Occured....");
        }
        
        return null;
        
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public CustomProjectileCircle getCircle() {
        return circle;
    }

    public void setCircle(CustomProjectileCircle circle) {
        this.circle = circle;
    }

    public CustomProjectileSquare getSquare() {
        return square;
    }

    public void setSquare(CustomProjectileSquare square) {
        this.square = square;
    }

    public CustomProjectileTriangle getTriangle() {
        return triangle;
    }

    public void setTriangle(CustomProjectileTriangle triangle) {
        this.triangle = triangle;
    }

    public Button getBTNCustom() {
        return BTNCustom;
    }

    public void setBTNCustom(Button BTNCustom) {
        this.BTNCustom = BTNCustom;
    }

    public boolean isBTNCustomHasBeenPressed() {
        return BTNCustomHasBeenPressed;
    }

    public void setBTNCustomHasBeenPressed(boolean BTNCustomHasBeenPressed) {
        this.BTNCustomHasBeenPressed = BTNCustomHasBeenPressed;
    }

    
    
    
}
