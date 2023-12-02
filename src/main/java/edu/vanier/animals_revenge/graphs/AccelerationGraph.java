package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

/**
 *
 * @author 2268182
 */
public class AccelerationGraph extends KinematicsGraph {

    double vF;

    double vI;

    public AccelerationGraph(Entity entity) {
        super(entity, "Acceleration", "cm/s^2");
    }

    @Override
    public void updateGraph(ActionEvent event) {
        double acceleration;
        // Get current Y velocity in cm/s (positive in upwards direction)
        vF = -1.0 * entity.getComponent(PhysicsComponent.class).getVelocityY() * PX_TO_CM_CONVERSION;

        // The only moment acceleration changes is when the previous velocity and current velocity are
        // opposite in direction, meaning it has collided with ceiling/floor
        // Ignore imprecision from small changes in acceleration, meaning it has not hit floor/ceiling
        if ((vF * vI) < 0 && Math.abs(vF - vI) > 1) {
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

    }

}
