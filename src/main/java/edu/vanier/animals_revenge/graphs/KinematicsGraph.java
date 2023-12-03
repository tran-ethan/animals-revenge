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
 * @author Ethan Tran
 * @author Mackenzie Rouchdy
 */
public abstract class KinematicsGraph extends Pane {

    public XYChart.Series<Number, Number> series;

    public Timeline timeline;

    public Entity entity;

    public double time = 0;

    public static final double PX_TO_CM_CONVERSION = 0.0264583333;

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

    public abstract void updateGraph(ActionEvent event);

    public void stop() {
        timeline.stop();
    }
}
