/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

/**
 *
 * @author 2268182
 */
public class CustomProjectileSelectorController {
    
     @FXML
    private Button BTNCustom;

    @FXML
    void SelectProjectile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        
        File file = fileChooser.showOpenDialog(null);
        
        CustomProjectileSquare projectile = (CustomProjectileSquare) CustomProjectileController.deserialize(file.getAbsolutePath());
        
        System.out.println(projectile.getColour());
        
    }
    
}
