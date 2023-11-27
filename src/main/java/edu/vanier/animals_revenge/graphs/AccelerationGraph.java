/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
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

    double deltaVelocity;

    ArrayList<Double> VelocityValues = VelocityGraph.VelocityValues;

    private XYChart.Series<Number, Number> series;

    private double acceleration;

    private Timeline timeline;

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

        // Add the current  position to the list
        
        
        
        //VelocityValues.add();

        if (VelocityValues.size() > 2) {
            lastV1 = VelocityValues.get(VelocityValues.size() - 1);
            lastV2 = VelocityValues.get(VelocityValues.size() - 2);

            deltaVelocity = lastV1 - lastV2;

            //bigger numbers are nicer
            deltaVelocity = deltaVelocity / 0.01;

            acceleration = deltaVelocity / MainApp.time;

            series.getData().add(new XYChart.Data<>(MainApp.time, acceleration));
        }

        if (series.getData().size() > 3) {
            double lastSpeed1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastSpeed2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastSpeed3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();

            double resultantSpeed = Math.abs(lastSpeed1) + Math.abs(lastSpeed2) + Math.abs(lastSpeed3);

            Math.abs(resultantSpeed);

            //stopping condition 
            if (resultantSpeed <= 0.005) {
                timeline.stop();
            }
        }
    }

}
