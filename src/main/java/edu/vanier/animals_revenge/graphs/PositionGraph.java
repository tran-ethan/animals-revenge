package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Mackenzie Rouchdy
 * @author Ethan Tran
 */
public class PositionGraph extends KinematicsGraph {

    VelocityGraph velocityGraph;

    AccelerationGraph accelerationGraph;

    public PositionGraph(Entity entity, VelocityGraph velocityGraph, AccelerationGraph accelerationGraph) {
        super(entity, "Height", "cm");
        this.velocityGraph = velocityGraph;
        this.accelerationGraph = accelerationGraph;
    }

    /**
     * Updates the graph with a new data point.
     */
    @Override
    public void updateGraph(ActionEvent event) {
        double pxToCm = 0.0264583333;
        // Get Y position from the bottom of the screen and convert to cm
        double posY = (MainApp.HEIGHT - entity.getPosition().getY() - entity.getHeight()) * pxToCm;

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

            // Stopping condition
            if (lastY1 == lastY2 && lastY2 == lastY3) {
                // Stops this, velocity, and acceleration graph once this graph is stopped
                this.stop();
                velocityGraph.stop();
                accelerationGraph.stop();
            }
        }

    }

    public double getYValue(int index) {
        return series.getData().get(index).getYValue().doubleValue();
    }
}

