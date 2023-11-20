/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 2246745
 */
public class SettingsController implements UIController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @FXML
    private ChoiceBox chbScene;

    @FXML
    private ColorPicker clrPickScene;

    @FXML
    private ChoiceBox chbMusic;

    @FXML
    private Slider sldMusic;

    @FXML
    private Slider slbSound;
    
    @FXML
    private AnchorPane bkgnSettings;

    @Override
    public void init() {
        logger.info("Initializing MainAppController...");
         // create a image 
            Image image = new Image("/assets/textures/background.png"); 
            
  
            // create a background image 
            BackgroundImage backgroundimage = new BackgroundImage(image,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundPosition.DEFAULT,  
                                                BackgroundSize.DEFAULT); 
  
            // create Background 
            Background background = new Background(backgroundimage); 
  
            // set background 
        bkgnSettings.setBackground(background);
        
    }

}
