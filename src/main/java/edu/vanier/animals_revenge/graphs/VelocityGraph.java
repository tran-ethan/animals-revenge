package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class VelocityGraph extends KinematicsGraph {

    public VelocityGraph() {
        super("Velocity", "cm/s");
    }

    @Override
    public void updateGraph(ActionEvent event) {
        // Add the current Y velocity to the list
        series.getData().add(new XYChart.Data<>(MainApp.time, MainApp.velocityY));

        // Get number of data points in series
        int size = series.getData().size();

        if (series.getData().size() > 3) {
            double lastVel = getYValue(size - 1);
            double lastVel2 = getYValue(size - 2);
            double lastVel3 = getYValue(size - 3);
            
            double resultantSpeed = Math.abs(lastVel) + Math.abs(lastVel2) + Math.abs(lastVel3);
            
            resultantSpeed = Math.abs(resultantSpeed);

            // Stopping condition
            if (resultantSpeed <= 0.00005) {
                timeline.stop();
            }
        }
    }

}
