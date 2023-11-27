package edu.vanier.animals_revenge.windows;

import static com.almasb.fxgl.dsl.FXGL.getGameTimer;
import com.almasb.fxgl.time.TimerAction;
import edu.vanier.animals_revenge.graphs.AccelerationGraph;
import edu.vanier.animals_revenge.graphs.DisplacementGraph;
import edu.vanier.animals_revenge.graphs.VelocityGraph;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Class representing the Projectile Motion Graph window.
 */
public class graphs extends Stage {

    /**
     * Constructor for the Graphs class.
     */
    public graphs() {
        TabPane tabPane = new TabPane();
        DisplacementGraph DisplacementGraph = new DisplacementGraph();
        VelocityGraph VelocityGraph = new VelocityGraph();
        AccelerationGraph AccelerationGraph = new AccelerationGraph();
        Tab tab1 = new Tab("Displacement");
        Tab tab2 = new Tab("Velocity");
        Tab tab3 = new Tab("Acceleration");
        tab1.setContent(DisplacementGraph);
        tab2.setContent(VelocityGraph);
        tab3.setContent(AccelerationGraph);
        
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        Scene scene = new Scene(tabPane, 600, 400);

        setTitle("Projectile Motion Graph");
        getIcons().add(new Image("assets/textures/graph.png"));
        setScene(scene);
        setX(1205);
        show();
    }
}
