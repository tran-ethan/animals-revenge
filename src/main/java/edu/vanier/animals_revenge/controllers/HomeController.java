package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Controller class of the MainApp's UI.
 *
 */
public class HomeController implements UIController {

    private final static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @FXML
    private Button btnNew;
    
    @FXML
    private Button btnLoad;
    
    @FXML
    private Button btnCustomProjectile;
    
    @FXML
    private Button btnSettings;

    @Override
    public void init() {
        logger.info("Initializing MainAppController...");
    }
    
    @FXML 
    void switchScenes(ActionEvent event) {
        Object clickButton = event.getSource();
        
        if (clickButton == btnNew) {
            MainApp.loadFXML("Simulator.fxml", new SimulatorController());
        } else if (clickButton == btnLoad) {
            System.out.println("TODO");
        } else if (clickButton == btnCustomProjectile) {
            MainApp.loadFXML("1CustomProjectile.fxml", new CustomProjectileController());
        } else if (clickButton == btnSettings) {
            MainApp.loadFXML("Setting.fxml", new SettingsController());
        }
    }
}
