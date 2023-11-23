/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author macke
 */
public class VelocityGraph extends Pane {

    double lastY1 = 0;
    double lastY2 = 0;

    double displacement;

    boolean gotRidOfZeroValues = false;

    ArrayList<Double> Ypositions = new ArrayList<>();

    private XYChart.Series<Number, Number> series;

    private double speed;

    private Timeline timeline;

    public VelocityGraph() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Velocity (px/s)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Velocity VS. Time");

        series = new XYChart.Series<>();
        series.setName("Velocity");

        lineChart.getData().add(series);

        getChildren().add(lineChart);

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateVelocitytGraph()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    private void updateVelocitytGraph() {

        // Add the current Y position to the list
        Ypositions.add(MainApp.PosY);

        if (Ypositions.size() > 2) {
            lastY1 = Ypositions.get(Ypositions.size() - 1);
            lastY2 = Ypositions.get(Ypositions.size() - 2);

            displacement = lastY1 - lastY2;
            
            //bigger numbers are nicer
            displacement = displacement / 0.01;

            speed = displacement / MainApp.time;

            series.getData().add(new XYChart.Data<>(MainApp.time, speed));
        }

        if (series.getData().size() > 3) {
            double lastSpeed1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastSpeed2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastSpeed3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();
            
            double resultantSpeed = Math.abs(lastSpeed1) + Math.abs(lastSpeed2) + Math.abs(lastSpeed3);
            
            Math.abs(resultantSpeed);
            
            if (resultantSpeed <= 0.005) {
                timeline.stop();
            }
        }
    }

}
