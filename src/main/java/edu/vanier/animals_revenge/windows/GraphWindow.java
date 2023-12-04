package edu.vanier.animals_revenge.windows;

import com.almasb.fxgl.entity.Entity;
import edu.vanier.animals_revenge.graphs.AccelerationGraph;
import edu.vanier.animals_revenge.graphs.PositionGraph;
import edu.vanier.animals_revenge.graphs.VelocityGraph;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class representing the Projectile Motion Graph window, which contains tabs containing
 * the position, velocity, and acceleration graphs.
 *
 * @author Mackenzie Rouchdy
 */
public class GraphWindow extends Stage {

    /**
     * Constructor for the Graphs class.
     */
    public GraphWindow(Entity e) {
        TabPane tabPane = new TabPane();

        // Create graphs
        VelocityGraph velocityGraph = new VelocityGraph(e);
        AccelerationGraph accelerationGraph = new AccelerationGraph(e);
        PositionGraph positionGraph = new PositionGraph(e, velocityGraph, accelerationGraph);

        // Create tabs
        Tab tab1 = new Tab("Displacement");
        Tab tab2 = new Tab("Velocity");
        Tab tab3 = new Tab("Acceleration");

        // Set content to corresponding graph
        tab1.setContent(positionGraph);
        tab2.setContent(velocityGraph);
        tab3.setContent(accelerationGraph);
        
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        Scene scene = new Scene(tabPane, 600, 500);

        setTitle("Projectile Motion Graph");
        getIcons().add(new Image("assets/textures/graph.png"));
        setScene(scene);
    }
}
