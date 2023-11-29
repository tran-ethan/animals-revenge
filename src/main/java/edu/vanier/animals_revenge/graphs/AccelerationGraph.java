/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
import static edu.vanier.animals_revenge.graphs.VelocityGraph.VelocityValues;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author 2268182
 */
public class AccelerationGraph extends Pane {

    double lastV1 = 0;
    double lastV2 = 0;

    double lastY1;
    double lastY2;

    double displacement;

    ArrayList<Double> VelocityValues;
    ArrayList<Double> Ypositions = new ArrayList<>();

    double deltaVelocity;

    private final XYChart.Series<Number, Number> series;

    private double acceleration;

    private final Timeline timeline;

    public AccelerationGraph() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Acceleration");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Acceleration VS. Time");

        series = new XYChart.Series<>();
        series.setName("Acceleration");

        lineChart.getData().add(series);

        getChildren().add(lineChart);

        timeline = new Timeline(new KeyFrame(Duration.millis(100),
                event -> updateAccelerationGraph()
        ));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void updateAccelerationGraph() {

        VelocityValues = VelocityGraph.VelocityValues;

        Ypositions.add(MainApp.PosY);

        if (Ypositions.size() > 2) {
            lastY1 = Ypositions.get(Ypositions.size() - 1);
            lastY2 = Ypositions.get(Ypositions.size() - 2);

            displacement = lastY1 - lastY2;

            //acceleration is defined as the change in displacement over the change in time squared
            // m/s^2
            acceleration = displacement / MainApp.time * MainApp.time;

            VelocityValues.add(acceleration);
            series.getData().add(new XYChart.Data<>(MainApp.time, acceleration));

        }

        if (series.getData().size() > 3) {
            double lastSpeed1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastSpeed2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastSpeed3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();

            double resultantSpeed = Math.abs(lastSpeed1) + Math.abs(lastSpeed2) + Math.abs(lastSpeed3);

            Math.abs(resultantSpeed);

            //stopping condition
            if (resultantSpeed <= 0.00000005) {
                timeline.stop();
            }
        }

    }

}
