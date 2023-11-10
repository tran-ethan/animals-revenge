package edu.vanier.animals_revenge;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.controllers.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.almasb.fxgl.dsl.FXGL.getAssetLoader;
import static com.almasb.fxgl.dsl.FXGL.getGameScene;

/**
 * Main entry point of the FXGL application.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 * @author Zachary Tremblay
 * @author Anton Lisunov
 */
public class MainApp extends GameApplication {

    final int WIDTH = 1480;

    final int HEIGHT = 820;

    private final static Logger logger = LoggerFactory.getLogger(MainApp.class);

    private static UI ui;

    /**
     * Initializes application settings.
     *
     * @param settings the settings of the game
     */
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setGameMenuEnabled(false);
        settings.setWidth(WIDTH);
        settings.setHeight(HEIGHT);
    }

    /**
     * Initializes the UI layer which is built on top of the game world layer.
     * Called once when the app is first launched to render the home screen.
     */
    @Override
    protected void initUI() {
        HomeController controller = new HomeController();
        ui = getAssetLoader().loadUI("Home.fxml", controller);
        getGameScene().addUI(ui);
    }

    /**
     * Loads and displays an FXML-based user interface defined by the given FXML file.
     * Removes previous scene from the UI layer to prevent adding a UI node on top of the previously existing one.
     *
     * @param fxml the string representation of the FXML file including its file extension from assets/ui/ folder
     * @param controller the instance of the controller linked to the appropriate FXML file
     */
    public static void loadFXML(String fxml, UIController controller) {
        getGameScene().removeUI(ui);
        ui = getAssetLoader().loadUI(fxml, controller);
        getGameScene().addUI(ui);
    }

    /**
     * Main method for starting the application.
     * Launches the application by calling GameApplication.launch()
     *
     * @see GameApplication#launch(String[])
     * @param args the command-line arguments passed to the application at startup
     */
    public static void main(String[] args) {
        launch(args);
    }
}
