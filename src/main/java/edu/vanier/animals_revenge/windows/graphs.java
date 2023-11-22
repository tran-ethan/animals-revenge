/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.windows;

import edu.vanier.animals_revenge.MainApp;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author macke
 */
public class graphs extends Stage {

    private XYChart.Series<Number, Number> series;

    private double time = 0.0;

    private Timeline timeline;

    public graphs() {

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        yAxis.setLabel("Height (px)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Displacement VS. Time");

        series = new XYChart.Series<>();
        
        series.setName("Projectile Path");

        lineChart.getData().add(series);

        Scene scene = new Scene(lineChart);

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateGraph()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

        setTitle("Projectile Motion Graph");

        getIcons().add(new Image("assets/textures/graph.png"));

        setScene(scene);

        setX(925);

        show();
    }

    private void updateGraph() {
        //adds a new data point to the graph by adding it to series

        if (MainApp.PosY >= 0) {
            series.getData().add(new XYChart.Data<>(time, MainApp.PosY));
        }

        //checks if last 3 y positions were the saem
        if (series.getData().size() > 3) {

            double lastY1 = series.getData().get(series.getData().size() - 1).getYValue().doubleValue();
            double lastY2 = series.getData().get(series.getData().size() - 2).getYValue().doubleValue();
            double lastY3 = series.getData().get(series.getData().size() - 3).getYValue().doubleValue();

            if (lastY1 == lastY2 && lastY2 == lastY3) {
                timeline.stop();
            }
        }

        //x increment
        time += 0.00001;
    }

}
