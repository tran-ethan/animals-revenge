package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.util.Type;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;

/**
 * This controller contains logic common to all controllers in the application.
 * It is the superclass of all controllers.
 *
 * @author Ethan Tran
 */
public abstract class Controller {

    /**
     * Switches the scene to the home screen.
     */
    @FXML
    public void goHome() {
        // Remove all objects from the game world so that it does not overlay
        // on other scenes
        try {
            for (Entity e : getGameWorld()
                    .getEntities()
                    .stream()
                    .filter(entity
                            -> entity.isType(Type.OBSTACLE) ||
                            entity.isType(Type.PROJECTILE) ||
                            entity.isType(Type.LAUNCHER)
                    ).toList()) {
                e.removeFromWorld();
            }
        } catch (Exception ignored) {}
        MainApp.animateVector(0, FXGL.getAppHeight());
        MainApp.loadFXML("Home.fxml", new HomeController());
    }

    /**
     * Displays a warning message in an alert dialog box.
     *
     * @param warningMessage The message to be displayed as a warning.
     * @param title The title for the warning alert dialog box.
     */
    public static void throwWarning(String warningMessage, String title) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(warningMessage);
        alert.setTitle(title);
        alert.showAndWait();
    }
}
