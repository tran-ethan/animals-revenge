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
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    public void initialize() {
        logger.info("Initializing MainAppController...");
    }

    @FXML
    void handleBtn1(ActionEvent event) {
        System.out.println("Click me called.");
        logger.info("Click me button has been pressed...");
    }

    @FXML
    void handleBtn2(ActionEvent event) {
        loadFXML("Simulator");
    }

    protected void loadFXML(String fxml) {
        try {
            MainApp.setRoot(fxml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

