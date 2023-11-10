package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import javafx.fxml.FXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatorController implements UIController {

    private final static Logger logger = LoggerFactory.getLogger(SimulatorController.class);

    @Override
    public void init() {
        logger.info("Initializing SimulatorController...");
    }

    @FXML
    public void goHome() {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }
}
