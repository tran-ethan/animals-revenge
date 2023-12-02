package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class DisplacementGraph extends KinematicsGraph {

    VelocityGraph velocityGraph;

    AccelerationGraph accelerationGraph;

    public DisplacementGraph(VelocityGraph velocityGraph, AccelerationGraph accelerationGraph) {
        super("Height", "cm");
        this.velocityGraph = velocityGraph;
        this.accelerationGraph = accelerationGraph;
    }

    /**
     * Updates the graph with a new data point.
     */
    @Override
    public void updateGraph(ActionEvent event) {

        //using real time
        if (MainApp.PosY >= 0) {
            series.getData().add(new XYChart.Data<>(MainApp.time, MainApp.PosY));
        }

        // Get number of data points in series
        int size = series.getData().size();

        // If the ball is not moving in the y direction then stop
        if (series.getData().size() > 3) {
            double lastY1 = getYValue(size - 1);
            double lastY2 = getYValue(size - 2);
            double lastY3 = getYValue(size - 3);

            // Stopping condition
            if (lastY1 == lastY2 && lastY2 == lastY3) {
                // Stops this, velocity, and acceleration graph
                this.stop();
                velocityGraph.stop();
                accelerationGraph.stop();
            }
        }

    }
}

