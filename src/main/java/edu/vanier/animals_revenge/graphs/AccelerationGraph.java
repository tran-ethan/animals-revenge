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

    ArrayList<Double> VelocityValues = new ArrayList<>();

    private double acceleration;

    public AccelerationGraph(Entity entity) {
        super(entity, "Acceleration", "cm/s^2");
    }

    @Override
    public void updateGraph(ActionEvent event) {
        //        VelocityValues.add(MainApp.velocityY);
        //
        //        //System.out.println("Velocity: " + (MainApp.velocityY) + " at Time:" + MainApp.time);
        //
        //        double timeInterval;
        //
        //        if (VelocityValues.size() > 2) {
        //
        //            //velocity is given in pixels per second
        //            lastV1 = VelocityValues.get(VelocityValues.size() - 1);
        //            lastV2 = VelocityValues.get(VelocityValues.size() - 2);
        //
        //            //System.out.println(lastV1 + " : " + lastV2);
        //            if (MainApp.timeVelocityValues.size() > 2) {
        //                if (MainApp.timeVelocityValues.containsKey(lastV1) && MainApp.timeVelocityValues.containsKey(lastV2)) {
        //                    double timeV1 = MainApp.timeVelocityValues.get(lastV1);
        //                    double timeV2 = MainApp.timeVelocityValues.get(lastV2);
        //
        //                    timeInterval = timeV1 - timeV2;
        //                    System.out.println(timeInterval);
        //                    if (timeInterval != 0) {
        //                        acceleration = (lastV1 - lastV2) / timeInterval;
        //                        //System.out.println(acceleration);
        //                    }
        //                }
        //            }
        //
        //            series.getData().add(new XYChart.Data<>(MainApp.time, acceleration));
        //
        //        }
        //
        //        if (MainApp.timeVelocityValues.size() < 25) {
        //            System.out.println(MainApp.timeVelocityValues);
        //        }


        // Vertical acceleration of ball is constant gravity
        double gravity = FXGL.getPhysicsWorld().getJBox2DWorld().getGravity().y;

        series.getData().add(new XYChart.Data<>(time, gravity));

        time += 0.1;

    }

}
