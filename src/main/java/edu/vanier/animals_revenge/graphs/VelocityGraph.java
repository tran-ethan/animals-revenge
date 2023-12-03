package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class VelocityGraph extends KinematicsGraph {

    public VelocityGraph(Entity entity) {
        super(entity, "Velocity", "cm/s");
    }

    @Override
    public void updateGraph(ActionEvent event) {
        if (entity.hasComponent(PhysicsComponent.class)) {
            // getVelocityY returns velocity positive increasing downwards in px/s
            double velocityY = -1.0 * entity.getComponent(PhysicsComponent.class).getVelocityY() * PX_TO_CM_CONVERSION;

            // Add the current Y velocity to the list
            series.getData().add(new XYChart.Data<>(time, velocityY));

            time += 0.1;
        } else {
            stop();
        }
    }

}
