/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.controllers.CustomProjectileSelectorController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2268182
 */
public class CustomProjectileSelectionUI extends Stage{
    
    
    private CustomProjectileSelectorController controller1 = new CustomProjectileSelectorController();
    
    public CustomProjectileSelectionUI() throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/ui/CustomProjectileSelector.fxml"));
        
        loader.setController(controller1);
        
        Pane root = loader.load();
        
        CustomProjectileSelectorController controller = loader.getController();
        
        controller.setStage(this);
        
        Scene scene = new Scene(root, 800, 400);

        setScene(scene);
        
        initModality(Modality.APPLICATION_MODAL);
        
        show();
        
            
        
    }

    public CustomProjectileSelectorController getController() {
        return controller1;
    }

    public void setController(CustomProjectileSelectorController controller) {
        this.controller1 = controller;
    }
    
    
    
}
