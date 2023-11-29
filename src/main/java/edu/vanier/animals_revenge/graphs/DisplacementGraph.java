/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.graphs;

import edu.vanier.animals_revenge.MainApp;
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
public class DisplacementGraph extends Pane {

    private XYChart.Series<Number, Number> series;

    private double time = 0.0;

    private Timeline timeline;

    public DisplacementGraph() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (ms)");
        yAxis.setLabel("Height (cm) ");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Displacement VS. Time");

        series = new XYChart.Series<>();
        series.setName("Projectile Path");
        lineChart.getData().add(series);

        getChildren().add(lineChart);

        //lower duration if experiencing lag
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateDisplacementGraph()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    /**
     * Updates the graph with a new data point.
     */
    private void updateDisplacementGraph() {
        
        //using real time
        if (MainApp.PosY >= 0) {
            series.getData().add(new XYChart.Data<>(MainApp.time, MainApp.PosY));
        }

        //if the ball is not moving in the y direction then stop
        if (series.getData().size() > 3) {
            double lastY1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastY2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastY3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();

            
            //stopping conditino
            if (lastY1 == lastY2 && lastY2 == lastY3) {
                timeline.stop();
            }
        }

    }

}
