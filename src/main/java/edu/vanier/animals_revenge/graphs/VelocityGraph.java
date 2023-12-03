package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 * The VelocityGraph class represents a graph that visualizes the vertical velocity of an entity over time.
 * It extends the KinematicsGraph class and provides methods to update and manage the velocity graph.
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class VelocityGraph extends KinematicsGraph {

    /**
     * Constructs a VelocityGraph object.
     *
     * @param entity The entity whose velocity is being visualized.
     */
    public VelocityGraph(Entity entity) {
        super(entity, "Velocity", "cm/s");
    }

    /**
     * Updates the velocity graph based on the entity's vertical velocity over time.
     *
     * @param event The ActionEvent triggering the update.
     */
    @Override
    public void updateGraph(ActionEvent event) {
        // If entity does not have a physics component, that means the entity has been deleted
        if (entity.hasComponent(PhysicsComponent.class)) {
            // getVelocityY returns velocity positive increasing downwards in px/s
            double velocityY = -entity.getComponent(PhysicsComponent.class).getVelocityY() * PX_TO_CM_CONVERSION;

            // Add the current Y velocity to the list
            series.getData().add(new XYChart.Data<>(time, velocityY));

            time += 0.1;
        } else {
            stop();
        }
    }

}
