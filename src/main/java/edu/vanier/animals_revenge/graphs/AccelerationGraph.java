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

    double deltaVelocity = 0;
    
    
    
    double lastY1;
    double lastY2;

    double displacement;

    ArrayList<Double> VelocityValues = new ArrayList<>();
    ArrayList<Double> Ypositions = new ArrayList<>();

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
        
        VelocityValues.add(MainApp.velocityY);
        
        Ypositions.add(MainApp.velocityY);
        
        if(VelocityValues.size() > 2) {
            lastV1 = VelocityValues.get(VelocityValues.size()-1);
            lastV2 = VelocityValues.get(VelocityValues.size()-2);
            
            //System.out.println(lastV1 + " : " + lastV2);
            
            acceleration = (lastV1 - lastV2) / MainApp.time;
            
            System.out.println(MainApp.time);
            
        }
        
        series.getData().add(new XYChart.Data<>(MainApp.time, acceleration));

        //TODO Add stopping condition

    }

}
