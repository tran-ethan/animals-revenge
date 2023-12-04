package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 * The PositionGraph class represents a graph that visualizes the vertical position of an entity over time.
 * It extends the KinematicsGraph class and provides methods to update and manage the position graph.
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class PositionGraph extends KinematicsGraph {

    VelocityGraph velocityGraph;

    AccelerationGraph accelerationGraph;

    /**
     * Constructs a PositionGraph object.
     *
     * @param entity             The entity whose position is being visualized.
     * @param velocityGraph      The VelocityGraph associated with this PositionGraph, used for stopping timeline
     * @param accelerationGraph  The AccelerationGraph associated with this PositionGraph, used for stopping timeline
     */
    public PositionGraph(Entity entity, VelocityGraph velocityGraph, AccelerationGraph accelerationGraph) {
        super(entity, "Height", "cm");
        this.velocityGraph = velocityGraph;
        this.accelerationGraph = accelerationGraph;
    }

    /**
     * Updates the position graph based on the entity's vertical position over time.
     *
     * @param event The ActionEvent triggering the update.
     */
    @Override
    public void updateGraph(ActionEvent event) {
        // If entity does not have a physics component, that means the entity has been deleted
        if (entity.hasComponent(PhysicsComponent.class)) {
            // Get Y position from the bottom of the screen and convert to cm
            double posY = (MainApp.HEIGHT - entity.getPosition().getY() - entity.getHeight()) * PX_TO_CM_CONVERSION;

            if (posY >= 0) {
                series.getData().add(new XYChart.Data<>(time, posY));
            }

            time += 0.1;

            // Get number of data points in series
            int size = series.getData().size();

            // If the ball is not moving in the y direction then stop
            if (series.getData().size() > 3) {
                double lastY1 = getYValue(size - 1);
                double lastY2 = getYValue(size - 2);
                double lastY3 = getYValue(size - 3);

                // Stopping condition if the ball has stopped moving vertically, or projectile has been deleted from world
                if (lastY1 == lastY2 && lastY2 == lastY3) {
                    // Stops this, velocity, and acceleration graph once this graph is stopped
                    this.stop();
                    velocityGraph.stop();
                    accelerationGraph.stop();
                }
            }
        } else {
            stop();
        }

    }

    /**
     * Retrieves the Y value from the series data at the specified index.
     *
     * @param index The index of the data point in the series.
     * @return The Y value at the specified index.
     */
    public double getYValue(int index) {
        return series.getData().get(index).getYValue().doubleValue();
    }
}

