package edu.vanier.animals_revenge.actions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import edu.vanier.animals_revenge.util.Type;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.getGameWorld;
import static com.almasb.fxgl.dsl.FXGL.getInput;

/**
 * The DeleteAction class represents an action that allows deleting specific obstacles or projectiles
 * While action is active, it will delete any objects in range of the mouse.
 *
 * @author Ethan Tran
 */
public class DeleteAction extends UserAction {

    /**
     * Constructs a DeleteAction instance.
     * Initializes the action with the name "Delete".
     */
    public DeleteAction() {
        super("Delete");
    }

    /**
     * Removes any entity in range of the mouse.
     * <p>
     * Called once in the same frame the action is triggered.
     */
    @Override
    protected void onAction() {
        double x = getInput().getMouseXWorld();
        double y = getInput().getMouseYWorld();

        getGameWorld().getEntitiesInRange(new Rectangle2D(x - 1.0, y - 1.0, 2.0, 2.0))
                .stream()
                .filter(entity -> entity.isType(Type.OBSTACLE) || entity.isType(Type.PROJECTILE))
                .findFirst()
                .ifPresent(Entity::removeFromWorld);
    }
}
