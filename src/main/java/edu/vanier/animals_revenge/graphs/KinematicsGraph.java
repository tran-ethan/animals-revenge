package edu.vanier.animals_revenge.graphs;

import com.almasb.fxgl.entity.Entity;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;


/**
 * The abstract class KinematicsGraph represents a graph visualizing kinematic data over time.
 * Subclasses of KinematicsGraph must implement the updateGraph method to define how the graph data is updated.
 * Subclasses of KinematicsGraph will represent a function of motion over time, being either
 * position, velocity, or acceleration.
 *
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 */
public abstract class KinematicsGraph extends Pane {

    public XYChart.Series<Number, Number> series;

    public Timeline timeline;

    public Entity entity;

    public double time = 0;

    /**
     * Conversion factor from pixels to centimeters
     */
    public static final double PX_TO_CM_CONVERSION = 0.0264583333;

    /**
     * Constructs a KinematicsGraph object.
     *
     * @param entity      The entity associated with the kinematic data.
     * @param yAxisLabel  The label for the y-axis representing the data.
     * @param unit        The unit of measurement for the y-axis data.
     */
    public KinematicsGraph(Entity entity, String yAxisLabel, String unit) {
        this.entity = entity;

        // Axis
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        yAxis.setLabel(String.format("%s (%s)", yAxisLabel, unit));

        // Line Chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(String.format("%s vs. Time Graph", yAxisLabel));

        // Value series
        series = new XYChart.Series<>();
        series.setName(yAxisLabel);

        lineChart.getData().add(series);

        getChildren().add(lineChart);

        EventHandler<ActionEvent> onFinished = this::updateGraph;

        // Timeline
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), onFinished));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Abstract method that must be implemented by subclasses to update the graph's data
     * every 0.1s.
     *
     * @param event The ActionEvent triggering the update.
     */
    public abstract void updateGraph(ActionEvent event);

    /**
     * Stops the timeline, thereby halting the graph updates.
     */
    public void stop() {
        timeline.stop();
    }
}
