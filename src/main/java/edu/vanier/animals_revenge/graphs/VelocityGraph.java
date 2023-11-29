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
 * @author macke
 */
public class VelocityGraph extends Pane {

    double lastV1 = 0;
    double lastY2 = 0;

    double displacement;
    
    private XYChart.Series<Number, Number> series;

    public double velocity;

    private Timeline timeline;

    public VelocityGraph() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time");
        yAxis.setLabel("Velocity");

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

        // Add the current Y velocity to the list
        series.getData().add(new XYChart.Data<>(MainApp.time, MainApp.velocityY));

        if (series.getData().size() > 3) {
            double lastSpeed1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastSpeed2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastSpeed3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();
            
            double resultantSpeed = Math.abs(lastSpeed1) + Math.abs(lastSpeed2) + Math.abs(lastSpeed3);
            
            Math.abs(resultantSpeed);
            
            
            //stopping condition 
            if (resultantSpeed <= 0.00005) {
                timeline.stop();
            }
        }
    }

}
