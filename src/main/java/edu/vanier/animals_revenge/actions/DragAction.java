package edu.vanier.animals_revenge.actions;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import edu.vanier.animals_revenge.Type;
import javafx.geometry.Rectangle2D;

import static com.almasb.fxgl.dsl.FXGL.*;

public class DragAction extends UserAction {

    /**
     * A boolean representing whether it selects an obstacle or creates a new one
     */
    private final boolean newObstacle;

    /**
     * The offset between the top left X position of the selected entity and the cursor X position at the moment the
     * action is triggered
     */
    private double offsetX = 0;

    /**
     * The offset between the top left Y position of the selected entity and the cursor Y position at the moment the
     * action is triggered
     */
    private double offsetY = 0;

    private Entity selected = null;

    /**
     * Constructor for the DragAction. This class represents the action of either dragging a block around the
     * game area or creating a new block and dragging it.
     *
     * @param newObstacle whether to spawn new obstacle or to drag existing one
     */
    public DragAction(boolean newObstacle) {
        super(newObstacle ? "Drag" : "Create");
        this.newObstacle = newObstacle;
    }

    /**
     * If the user is trying to create a new obstacle, this method will spawn a new obstacle and set that equal
     * to the current selected entity.
     * If the user is trying to drag an already existing obstacle, this method will create a bounding rectangle and
     * select the first obstacle in that area. It will then calculate the difference between the selected entity's top
     * left corner and the current cursor position.
     * <p>
     * Called once in the same frame the action is triggered.
     */
    @Override
    protected void onActionBegin() {
        double x = getInput().getMouseXWorld();
        double y = getInput().getMouseYWorld();

        if (newObstacle) {
            selected = spawn("obstacle", new SpawnData(x, y).put("img", "brick.png"));
            // Mouse offset to the middle of the spawned obstacle
            offsetX = selected.getWidth() / 2;
            offsetY = selected.getHeight() / 2;
        } else {
            getGameWorld().getEntitiesInRange(new Rectangle2D(x - 1.0, y - 1.0, 2.0, 2.0))
                    .stream()
                    .filter(entity -> entity.isType(Type.OBSTACLE))
                    .findFirst()
                    .ifPresent(entity -> {
                        selected = entity;
                        // Mouse offset to the position mouse at which it initiated the drag
                        offsetX = x - selected.getX();
                        offsetY = y - selected.getY();
                    });
        }
    }

    /**
     * Moves the selected entity by settings its linear velocity equal to the difference between its position
     * and the cursor's current position, while taking into account offsets generated by corner positioning.
     * <p>
     * Called every frame while the action is still active.
     */
    @Override
    protected void onAction() {
        if (selected != null) {
            double velocityX = getInput().getMouseXWorld() - offsetX - selected.getX();
            double velocityY = getInput().getMouseYWorld() - offsetY - selected.getY();

            selected.getComponent(PhysicsComponent.class).setLinearVelocity(velocityX * 4, velocityY * 4);
        }
    }

    /**
     * Deselects the entity to prevent removing the same entity the next time the action is triggered
     * <p>
     * Called at the frame the action is stopped.
     */
    @Override
    protected void onActionEnd() {
        selected = null;
    }
}
