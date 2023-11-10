package edu.vanier.animals_revenge;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.controllers.HomeController;
import javafx.scene.Cursor;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * Main entry point of the FXGL application.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 * @author Zachary Tremblay
 * @author Anton Lisunov
 */
public class MainApp extends GameApplication {

    final static double WIDTH = 1480;

    final static double HEIGHT = 820;

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
        settings.setWidth((int) WIDTH);
        settings.setHeight((int) HEIGHT);
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

    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
        getGameScene().setCursor(Cursor.DEFAULT);

        initScreenBounds();
    }

    /**
     * Initializes game objects and spawns them into the game world.
     * There is only a single projectile which represents the animal that the player can shoot,
     * and multiple obstacles in a single execution of the simulator.
     */
    public static void initGameObjects() {
        spawn("projectile", new SpawnData(WIDTH / 2, HEIGHT / 2).put("vX", 0.0).put("vY", 0.0));
        spawn("obstacle", new SpawnData(50, HEIGHT - 100).put("img", "brick.png"));
        spawn("obstacle", new SpawnData(100, HEIGHT - 100).put("img", "brick.png"));
        spawn("obstacle", new SpawnData(150, HEIGHT - 100).put("img", "brick.png"));
    }

    /**
     * Initializes and adds screen boundary walls to the game world. Screen boundary walls
     * are used to restrict the movement of entities within the game world. These walls
     * are placed around the visible screen area (window) to prevent entities from moving outside of it.
     *
     * @see com.almasb.fxgl.dsl.EntityBuilder#buildScreenBounds(double)
     */
    private void initScreenBounds() {
        Entity walls = entityBuilder()
                .collidable()
                .buildScreenBounds(150);

        getGameWorld().addEntity(walls);
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
