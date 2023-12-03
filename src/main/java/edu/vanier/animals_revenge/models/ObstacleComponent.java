package edu.vanier.animals_revenge.models;

import com.almasb.fxgl.entity.component.Component;

public class ObstacleComponent extends Component {

    private Obstacle obstacle;

    public ObstacleComponent(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }
}
