package edu.vanier.animals_revenge.actions;

import com.almasb.fxgl.input.UserAction;
import edu.vanier.animals_revenge.MainApp;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * The LaunchAction class represents an action that animates an initial velocity vector
 * in the simulator screen.
 */
public class LaunchAction extends UserAction {

    /**
     * Flag to determine if launching is in progress
     */
    boolean isLaunching = false;

    /**
     * Constructs a LaunchAction instance.
     * Initializes the action with the name "Launch".
     */
    public LaunchAction() {
        super("Launch");
    }

    /**
     * Called when the action is initiated (mouse button pressed).
     * Sets the flag isLaunching to true if the mouse position
     * falls within a specified area.
     */
    @Override
    protected void onActionBegin() {
        double x = getInput().getMouseXWorld();
        double y = getInput().getMouseYWorld();

        if (x >= 0 && x <= 80 && y >= MainApp.HEIGHT - 80 && y <= MainApp.HEIGHT) {
            isLaunching = true;
        }
    }

    /**
     * Called repeatedly while the action is ongoing (mouse button held down).
     * If isLaunching is true, triggers the vector animation based on the
     * current mouse position in the game world.
     */
    @Override
    protected void onAction() {
        if (isLaunching) {
            MainApp.animateVector(getInput().getMouseXWorld(), getInput().getMouseYWorld());
        }
    }

    /**
     * Called when the action ends (mouse button released).
     * Resets the isLaunching flag to false, signifying the end of the launching process.
     */
    @Override
    protected void onActionEnd() {
        isLaunching = false;
    }
}
