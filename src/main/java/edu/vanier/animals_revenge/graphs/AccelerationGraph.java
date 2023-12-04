package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;


/**
 * The AccelerationGraph class represents a graph that visualizes the vertical acceleration of an entity over time.
 * It extends the KinematicsGraph class.
 * <p>
 * This class calculates the acceleration based on gravity and changes in velocity over time.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 */
public class AccelerationGraph extends KinematicsGraph {

    /**
     * The current velocity of the entity at this moment
     */
    double vF;

    /**
     * The velocity of the entity 0.1s before (last time timeline was updated)
     */
    double vI;

    /**
     * Constructs an AccelerationGraph object.
     *
     * @param entity The entity whose acceleration is being visualized.
     */
    public AccelerationGraph(Entity entity) {
        super(entity, "Acceleration", "cm/s^2");
    }

    /**
     * Updates the acceleration graph based on the entity's vertical acceleration over time.
     *
     * @param event The ActionEvent triggering the update.
     */
    @Override
    public void updateGraph(ActionEvent event) {
        // If entity does not have a physics component, that means the entity has been deleted
        if (entity.hasComponent(PhysicsComponent.class)) {
            double acceleration;
            // Get current Y velocity in cm/s (positive in upwards direction)
            vF = -entity.getComponent(PhysicsComponent.class).getVelocityY() * PX_TO_CM_CONVERSION;

            // The only moment acceleration changes is when the previous velocity and current velocity are
            // opposite in direction, meaning it has collided with ceiling/floor
            // Ignore imprecision from small changes in acceleration, meaning it has not hit floor/ceiling
            if ((vF * vI) < 0 && Math.abs(vF - vI) > 2) {
                // Acceleration = final velocity - initial velocity / time
                acceleration = (vF - vI) / 0.1 ;
            } else {
                // Vertical acceleration of ball is constant gravity
                acceleration = FXGL.getPhysicsWorld().getJBox2DWorld().getGravity().y;
            }

            // Set previous velocity to current velocity for next time function is called
            vI = vF;

            series.getData().add(new XYChart.Data<>(time, acceleration));

            time += 0.1;
        } else {
            stop();
        }

    }

}
