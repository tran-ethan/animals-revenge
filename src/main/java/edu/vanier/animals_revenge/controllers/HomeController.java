package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.models.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


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

    /**
     * Initializes the MainAppController.
     */
    @Override
    public void init() {
        logger.info("Initializing MainAppController...");
    }
    
    /**
     * Switches scenes based on the button clicked.
     *
     * @param event The ActionEvent triggered when a button is clicked.
     */
    @FXML 
    void switchScenes(ActionEvent event) {
        Object clickButton = event.getSource();
        
        if (clickButton == btnNew) {
            MainApp.loadFXML("Simulator.fxml", new SimulatorController());
        } else if (clickButton == btnLoad) {
            // Choose level
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            if (selectedFile != null) {
                try (ObjectInputStream o = new ObjectInputStream(new FileInputStream(selectedFile.getAbsolutePath()))) {

                    // Type-cast to level
                    Level loadedLevel = (Level) o.readObject();
                    // Pass level to the Simulator Controller scene
                    MainApp.loadFXML("Simulator.fxml", new SimulatorController(loadedLevel));

                } catch (IOException | ClassNotFoundException e) {
                    SimulatorController.throwWarning("File Not A Valid Level", "Deserialization Error");
                }
            }
        } else if (clickButton == btnCustomProjectile) {
            MainApp.loadFXML("CustomProjectile.fxml", new CustomProjectileController());
        } else if (clickButton == btnSettings) {
            MainApp.loadFXML("Setting.fxml", new SettingsController());
        }
    }
}
