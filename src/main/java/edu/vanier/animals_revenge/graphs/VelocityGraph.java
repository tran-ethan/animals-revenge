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
    }

}
