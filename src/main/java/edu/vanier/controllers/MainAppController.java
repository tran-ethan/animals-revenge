package edu.vanier.controllers;

import edu.vanier.ui.MainApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller class of the MainApp's UI.
 *
 */
public class MainAppController {

    private final static Logger logger = LoggerFactory.getLogger(MainAppController.class);

    @FXML
    private Button btnNew;
    
    @FXML
    private Button btnLoad;
    
    @FXML
    private Button btnCustomProjectile;
    
    @FXML
    private Button btnVisualSettings;
    
    @FXML
    private Button btnSoundSettings;

    
    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
    }
    
    @FXML 
    void switchScenes(ActionEvent event) {
        
        Button clickButton = (Button)event.getSource();
        
        if(clickButton == btnNew) {
            loadFXML("Simulator");
        } else if (clickButton == btnLoad) {
            loadFXML("");
        } else if (clickButton == btnCustomProjectile) {
            loadFXML("");
        } else if (clickButton == btnVisualSettings) {
            loadFXML("");
        } else if (clickButton == btnSoundSettings) {
            loadFXML("");
        }
    }

    protected void loadFXML(String fxml) {
        try {
            MainApp.setRoot(fxml);
        } catch (Exception e) {
            System.out.printf("Could not find %s.fxml\n", fxml);
        }
    }
}

