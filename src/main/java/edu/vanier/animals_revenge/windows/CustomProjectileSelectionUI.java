/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.controllers.CustomProjectileController;
import edu.vanier.animals_revenge.controllers.CustomProjectileSelectorController;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2268182
 */
public class CustomProjectileSelectionUI extends Stage{
    
    
    
    public CustomProjectileSelectionUI() throws IOException {
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/ui/CustomProjectileSelector.fxml"));
        
        loader.setController(new CustomProjectileSelectorController());
        
        Pane root = loader.load();
        
        Scene scene = new Scene(root, 800, 400);

        setScene(scene);
        
        initModality(Modality.APPLICATION_MODAL);
        
    }
    
    
    
}
