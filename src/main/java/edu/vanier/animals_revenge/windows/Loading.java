/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 *
 * @author macke
 */
public class Loading extends Stage{

    public Loading() throws InterruptedException {
        
        ProgressBar loadingBar = new ProgressBar();
        
        Scene scene = new Scene(loadingBar, 500, 25);
        
        setScene(scene);
        
        setTitle("Saving Changes...");
        
        show();
        
        Thread.sleep(200);

        hide();
        
        
    }
    
    
}
