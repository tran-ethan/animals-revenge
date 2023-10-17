package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.MainApp;

/**
 * Controller class is the abstract superclass of all controllers for the app.
 * This controller handles common operations among all its controller subclasses.
 */
public abstract class Controller {

    /**
     * This method loads an FXML file and sets it as the root for the current scene.
     * @param fxml The filename of the FXML file to load.
     */
    protected void loadFXML(String fxml) {
        try {
            MainApp.setRoot(fxml);
        } catch (Exception e) {
            System.out.printf("Could not load %s.fxml\n", fxml);
        }
    }
}
