package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import edu.vanier.animals_revenge.MainApp;
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
        double velocityY = -1 * entity.getComponent(PhysicsComponent.class).getVelocityY();
        // Add the current Y velocity to the list
        series.getData().add(new XYChart.Data<>(MainApp.time, velocityY));
    }

}
