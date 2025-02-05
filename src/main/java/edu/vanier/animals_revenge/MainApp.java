package edu.vanier.animals_revenge;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.InputModifier;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.actions.DeleteAction;
import edu.vanier.animals_revenge.actions.DragAction;
import edu.vanier.animals_revenge.actions.LaunchAction;
import edu.vanier.animals_revenge.controllers.HomeController;
import edu.vanier.animals_revenge.controllers.ParametersController;
import edu.vanier.animals_revenge.controllers.ProjectileSelectionController;
import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.util.Factory;
import edu.vanier.animals_revenge.util.SavedSetting;
import edu.vanier.animals_revenge.windows.GraphWindow;
import javafx.scene.Cursor;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static com.almasb.fxgl.dsl.FXGL.*;
import static edu.vanier.animals_revenge.util.Type.*;

/**
 * Main entry point of the FXGL application.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 * @author Zachary Tremblay
 * @author Anton Lisunov
 */
public class MainApp extends GameApplication {

    public final static double WIDTH = 1480;

    public final static double HEIGHT = 820;

    private static Rectangle vector;

    private static Polygon vectorHead;

    private static UI ui;

    private SavedSetting settings;

    
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

    @Override
    protected void initPhysics() {
        getPhysicsWorld().addCollisionHandler(new CollisionHandler(OBSTACLE, WALL) {

            // order of types is the same as passed into the constructor
            @Override
            protected void onCollisionBegin(Entity obstacle, Entity wall) {
               settings.playSound();
            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(OBSTACLE, PROJECTILE) {
            @Override
            protected void onCollisionBegin(Entity obstacle, Entity projectile) {
               settings.playSound();

            }
        });

        getPhysicsWorld().addCollisionHandler(new CollisionHandler(OBSTACLE, OBSTACLE) {
            @Override
            protected void onCollisionBegin(Entity obstacle1, Entity obstacle2) {
               settings.playSound();

            }
        });
    }

    @Override
    protected void onPreInit() {
        // Load music
        settings = new SavedSetting();
        getSettings().setGlobalSoundVolume(settings.getSoundVolume());
        getSettings().setGlobalMusicVolume(settings.getMusicVolume());
        
        FXGL.getAudioPlayer().loopMusic(settings.getMusic());
    }

    /**
     * Initializes the UI layer which is built on top of the game world layer.
     * Called once when the app is first launched to render the home screen.
     */
    @Override
    protected void initUI() {
        // Add home screen UI
        HomeController controller = new HomeController();
        ui = getAssetLoader().loadUI("Home.fxml", controller);
        getGameScene().addUI(ui);

        // Create a rectangle representing the velocity vector body
        vector = new Rectangle(2, 2, Color.RED);
        vector.setY(HEIGHT);

        // Create the vector head as an equilateral triangle with side 12px
        double s = 12, h = s * Math.sqrt(3) / 2;
        vectorHead = new Polygon(0, -h / 2, s / 2, h / 2, -s / 2, h / 2);
        vectorHead.setFill(Color.RED);
        getGameScene().addUINodes(vector, vectorHead);
    }

    /**
     * Initializes the game world with entity factory responsible for spawning
     * objects into game world and screen bounds.
     */
    @Override
    protected void initGame() {
        getGameWorld().addEntityFactory(new Factory());
        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
        getGameScene().setCursor(Cursor.DEFAULT);

        initScreenBounds();

        
        spawn("background", new SpawnData().put("background", settings.getBackground()).put("color", settings.getColor()));
        settings.playMusic();
    }

    /**
     * Initializes and adds screen boundary walls to the game world. Screen
     * boundary walls are used to restrict the movement of entities within the
     * game world. These walls are placed around the visible screen area
     * (window) to prevent entities from moving outside of it.
     *
     * @see com.almasb.fxgl.dsl.EntityBuilder#buildScreenBounds(double)
     */
    private void initScreenBounds() {
        // Wall around window frame
        Entity walls = entityBuilder()
                .collidable()
                .buildScreenBounds(150);

        // Wall covering top menu bar
        spawn("wall", new SpawnData(0, 0).put("width", WIDTH).put("height", 65.0));
        // Wall covering right side
        spawn("wall", new SpawnData(WIDTH - 350.0, 0).put("width", 350.0).put("height", HEIGHT));

        getGameWorld().addEntity(walls);

    }

    /**
     * Maps the corresponding user inputs to their respective actions.
     * <p>
     * Right-click and hold from launcher to create initial velocity vector.
     * Left-click and hold to drag obstacles around.
     * Hold CTRL + Left-click and to create new obstacle (hold to drag).
     * Hold BACK_SPACE and move cursor around to delete obstacles at cursor position
     */
    @Override
    protected void initInput() {
        getInput().addAction(new LaunchAction(), MouseButton.SECONDARY);
        getInput().addAction(new DragAction(false), MouseButton.PRIMARY);
        getInput().addAction(new DragAction(true), MouseButton.PRIMARY, InputModifier.CTRL);
        getInput().addAction(new DeleteAction(), KeyCode.BACK_SPACE);
    }

    /**
     * Loads and displays an FXML-based user interface defined by the given FXML
     * file. Removes previous scene from the UI layer to prevent adding a UI
     * node on top of the previously existing one.
     *
     * @param fxml the string representation of the FXML file including its file
     * extension from assets/ui/ folder
     * @param controller the instance of the controller linked to the
     * appropriate FXML file
     */
    public static void loadFXML(String fxml, UIController controller) {
        // Remove existing UI layer to prevent stacking
        getGameScene().removeUI(ui);
        // Load fxml and controller
        ui = getAssetLoader().loadUI(fxml, controller);
        getGameScene().addUI(ui);
    }

    /**
     * Animates the vector representing the initial velocity by using
     * pythagorean theorem and trigonometry to find the hypotenuse and angle of
     * rotation
     *
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    public static void animateVector(double x, double y) {
        // Y position of mouse is calculated from top of screen, so opp side corresponds to difference
        double opp = HEIGHT - y;
        // From pythagorean theorem: hyp^2 = opp^2 + adj^2
        double hyp = Math.sqrt(Math.pow(opp, 2) + Math.pow(x, 2));
        // From trigonometry: tan(angle) = opp / adj
        double angle = Math.toDegrees(Math.atan(opp / x));

        // Set scale Y instead of height to scale from center point (0, HEIGHT) top and bottom
        vector.setScaleY(hyp);
        vector.setRotate(90 - angle);
        vectorHead.setLayoutX(x);
        vectorHead.setLayoutY(y + 2);
        vectorHead.setRotate(90 - angle);
    }

    /**
     * Launches the projectile by calculating initial x and y velocity using
     * velocity vector
     */
    public static void launch() {
        double hyp = vector.getScaleY();
        double angle = 90 - vector.getRotate();
        // From trigonometry: cos(angle) = adj / hyp
        double vX = Math.cos(Math.toRadians(angle)) * hyp;
        // From trigonometry: sin(angle) = opp / hyp
        double vY = Math.sin(Math.toRadians(angle)) * hyp;

        CustomProjectile proj = ProjectileSelectionController.finalProjectile;

        Entity e;
        if (proj instanceof CustomProjectileSquare) {

            CustomProjectileSquare p = (CustomProjectileSquare) proj;

            if (p.getImgPath() != null) {
                e = spawn("customProjectileSquare", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("colour", p.getColour())
                        .put("img", p.getImgPath())
                        .put("width", p.getShapeWidth())
                        .put("restitution", p.getRestitution())
                        .put("density", p.getDensity())
                        .put("height", p.getShapeHeight()));

            } else {
                e = spawn("customProjectileSquare", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("img", "null")
                        .put("colour", p.getColour())
                        .put("restitution", p.getRestitution())
                        .put("density", p.getDensity())
                        .put("width", p.getShapeWidth())
                        .put("height", p.getShapeHeight()));

            }

        } else if (proj instanceof CustomProjectileCircle) {

            CustomProjectileCircle p = (CustomProjectileCircle) proj;

            if (p.getImgPath() != null) {
                e = spawn("customProjectileCircle", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("colour", p.getColor())
                        .put("img", p.getImgPath())
                        .put("restitution", p.getRestitution())
                        .put("density", p.getDensity())
                        .put("radius", p.getRadius()));

            } else {
                e = spawn("customProjectileCircle", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("img", "null")
                        .put("radius", p.getRadius())
                        .put("restitution", p.getRestitution())
                        .put("density", p.getDensity())
                        .put("colour", p.getColor()));

            }

        } else {
            e = spawn("projectile", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                    .put("vY", vY)
                    .put("img", "red.png"));

        }

        // Register collision handler
        graphSetup(e);

    }

    /**
     * Creates the graph windows using the appropriate entity.
     *
     * @param e the entity representing the projectile
     */
    public static void graphSetup(Entity e) {
        if (!ParametersController.isIsGraphOff()) {
            GraphWindow graphWindow = new GraphWindow(e);
            graphWindow.show();
            graphWindow.setX(720);
        }
    }

    /**
     * Main method for starting the application. Launches the application by
     * calling GameApplication.launch()
     *
     * @see GameApplication#launch(String[])
     * @param args the command-line arguments passed to the application at startup
     */
    public static void main(String[] args) {
        launch(args);

    }
}
