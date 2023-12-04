package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.controllers.ProjectileSelectionController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The {@code Selection} class represents a custom JavaFX stage for displaying a
 * projectile selection window. It extends the {@link javafx.stage.Stage} class
 * and is responsible for loading the FXML file associated with the projectile
 * selection window. The window contains a {@link ProjectileSelectionController}
 * as its controller.
 * <p>
 * This class initializes the stage by loading the FXML file, setting the
 * controller, and creating the associated scene. Instances of this class are
 * expected to be used to show the projectile selection window in the
 * application's UI.
 *
 * @author Mackenzie Rouchdy
 */
public class SelectionWindow extends Stage {

    /**
     * Constructs a new {@code Selection} object. It initializes the stage by
     * loading the FXML file associated with the projectile selection window,
     * setting the {@link ProjectileSelectionController} as the controller, and
     * creating the scene.
     */
    public SelectionWindow() {

        Pane root = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/ui/ProjectileSelection.fxml"));

        loader.setController(new ProjectileSelectionController());

        try {
            root = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Scene scene = new Scene(root);

        setScene(scene);

    }

}
