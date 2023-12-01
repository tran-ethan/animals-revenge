package edu.vanier.animals_revenge.graphs;

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
 * @author Ethan Tran
 */
public abstract class KinematicsGraph extends Pane {

    public XYChart.Series<Number, Number> series;

    public Timeline timeline;

    public KinematicsGraph(String yAxisLabel, String unit) {
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
        timeline = new Timeline(new KeyFrame(Duration.millis(100), onFinished));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public abstract void updateGraph(ActionEvent event);

    public double getYValue(int index) {
        return series.getData().get(index).getYValue().doubleValue();
    }
}
