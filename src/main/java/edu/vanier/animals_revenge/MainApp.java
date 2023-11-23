package edu.vanier.animals_revenge;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.InputModifier;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.actions.DragAction;
import edu.vanier.animals_revenge.actions.LaunchAction;
import edu.vanier.animals_revenge.controllers.HomeController;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.almasb.fxgl.dsl.FXGL.*;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import edu.vanier.animals_revenge.windows.graphs;
import javafx.util.Duration;

/**
 * Main entry point of the FXGL application.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 * @author Zachary Tremblay
 * @author Anton Lisunov
 */
public class MainApp extends GameApplication {

    public static double PosX = 0;
    public static double PosY = 0;
    public static double time = 0;
    public static long startTime;

    public final static double WIDTH = 1480;

    public final static double HEIGHT = 820;

    private static Rectangle vector;

    private static Polygon vectorHead;

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
    }

    /**
     * Initializes game objects and spawns them into the game world. There is
     * only a single projectile which represents the animal that the player can
     * shoot, and multiple obstacles in a single execution of the simulator.
     */
    public static void initGameObjects() {

        spawn("launcher", 0, HEIGHT - 80);

        spawn("obstacle", new SpawnData(WIDTH - 500, HEIGHT - 65).put("img", "brick32x32.png"));
        spawn("obstacle", new SpawnData(WIDTH - 600, HEIGHT - 65).put("img", "brick32x32.png"));
        spawn("obstacle", new SpawnData(WIDTH - 700, HEIGHT - 65).put("img", "brick32x32.png"));
        spawn("obstacle", new SpawnData(WIDTH - 550, HEIGHT - 130).put("img", "brick32x32.png"));
        spawn("obstacle", new SpawnData(WIDTH - 650, HEIGHT - 130).put("img", "brick32x32.png"));
        spawn("obstacle", new SpawnData(WIDTH - 600, HEIGHT - 195).put("img", "brick32x32.png"));

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
        Entity walls = entityBuilder()
                .collidable()
                .buildScreenBounds(150);

        spawn("wall", new SpawnData(0, 0).put("width", WIDTH).put("height", 65.0));
        spawn("wall", new SpawnData(WIDTH - 350.0, 0).put("width", 350.0).put("height", HEIGHT));

        getGameWorld().addEntity(walls);
    }

    /**
     * Maps the corresponding user inputs to their respective actions.
     * <p>
     * Right-click and hold from launcher to create initial velocity vector.
     * Left-click and hold to drag obstacles around. Hold CTRL + Left-click and
     * hold to create new obstacle.
     */
    @Override
    protected void initInput() {
        getInput().addAction(new LaunchAction(), MouseButton.SECONDARY);
        getInput().addAction(new DragAction(false), MouseButton.PRIMARY);
        getInput().addAction(new DragAction(true), MouseButton.PRIMARY, InputModifier.CTRL);
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
     * Animates the vector representing the initial velocity by using the
     * pythagorean theorem to find the hypotenuse and trigonometry to find the
     * angle of rotation
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
     *
     * @param s
     * @param c
     *
     */
    public static void launch(CustomProjectileSquare s, CustomProjectileCircle c) {

        graphs graphWindow = new graphs();
        double hyp = vector.getScaleY();
        double angle = 90 - vector.getRotate();
        // From trigonometry: cos(angle) = adj / hyp
        double vX = Math.cos(Math.toRadians(angle)) * hyp;
        // From trigonometry: sin(angle) = opp / hyp
        double vY = Math.sin(Math.toRadians(angle)) * hyp;

        if (s != null) {
            System.out.println(s.getColour() + " " + s.getHeight() + " " + s.getImgPath());

            if (s.getImgPath() != null) {
                Entity e = spawn("customProjectileSquare", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("colour", s.getColour())
                        .put("img", s.getImgPath())
                        .put("width", s.getShapeWidth())
                        .put("height", s.getShapeHeight()));

                //start of timer
                startTime = System.currentTimeMillis();

                //gets position of entity every unit of time
                getGameTimer().runAtInterval(() -> {

                    //end of timer
                    time = System.currentTimeMillis() - startTime;

                    PosY = (HEIGHT - e.getPosition().getY() - e.getHeight());

                    //lower the duration if experiencing lag
                }, Duration.seconds(0.001));

            } else {
                Entity e = spawn("customProjectileSquare", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("img", "null")
                        .put("colour", s.getColour())
                        .put("width", s.getShapeWidth())
                        .put("height", s.getShapeHeight()));

                //start of timer
                startTime = System.currentTimeMillis();

                //gets position of entity every unit of time
                getGameTimer().runAtInterval(() -> {

                    //end of timer
                    time = System.currentTimeMillis() - startTime;

                    PosY = (HEIGHT - e.getPosition().getY() - e.getHeight());

                    //lower the duration if experiencing lag
                }, Duration.seconds(0.001));

            }

        } else if (c != null) {

            if (c.getImgPath() != null) {
                Entity e = spawn("customProjectileCircle", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("colour", c.getColor())
                        .put("img", c.getImgPath())
                        .put("radius", c.getRadius()));

                //start of timer
                startTime = System.currentTimeMillis();

                //gets position of entity every unit of time
                getGameTimer().runAtInterval(() -> {

                    //end of timer
                    time = System.currentTimeMillis() - startTime;

                    PosY = (HEIGHT - e.getPosition().getY() - e.getHeight());

                    //lower the duration if experiencing lag
                }, Duration.seconds(0.001));

            } else {
                Entity e = spawn("customProjectileCircle", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                        .put("vY", vY)
                        .put("img", "null")
                        .put("radius", c.getRadius())
                        .put("colour", c.getColor()));

                ///start of timer
                startTime = System.currentTimeMillis();

                //gets position of entity every unit of time
                getGameTimer().runAtInterval(() -> {

                    //end of timer
                    time = System.currentTimeMillis() - startTime;

                    PosY = (HEIGHT - e.getPosition().getY() - e.getHeight());

                    //lower the duration if experiencing lag
                }, Duration.seconds(0.001));

            }

        } else {
            System.out.println("Default Projectile");
            Entity e = spawn("projectile", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX)
                    .put("vY", vY)
                    .put("img", "soccer.png"));

            //start of timer
            startTime = System.currentTimeMillis();

            //gets position of entity every unit of time
            getGameTimer().runAtInterval(() -> {

                //end of timer
                time = System.currentTimeMillis() - startTime;

                PosY = (HEIGHT - e.getPosition().getY() - e.getHeight());

                //lower the duration if experiencing lag
            }, Duration.seconds(0.001));

        }

        // TODO fix projectile spawn y location ( do not hard code 32, get obstacle height)
        //spawn("projectile", new SpawnData(0, MainApp.HEIGHT - 32).put("vX", vX).put("vY", vY).put("img", "soccer.png"));
    }

    /**
     * Main method for starting the application. Launches the application by
     * calling GameApplication.launch()
     *
     * @see GameApplication#launch(String[])
     * @param args the command-line arguments passed to the application at
     * startup
     */
    public static void main(String[] args) {
        launch(args);
    }
}
