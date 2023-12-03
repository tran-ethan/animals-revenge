package edu.vanier.animals_revenge.models;

import com.almasb.fxgl.entity.component.Component;

/**
 * The ObstacleComponent class represents a data component attached to entities
 * that act as obstacles in the game.
 * This component holds an Obstacle object associated with the entity, and is used as
 * a wrapper class for the Obstacle class for loading levels.
 */
public class ObstacleComponent extends Component {

    private final Obstacle obstacle;

    /**
     * Constructs an ObstacleComponent with the provided Obstacle object.
     *
     * @param obstacle the Obstacle object associated with this component
     */
    public ObstacleComponent(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    /**
     * Retrieves the Obstacle object associated with this component.
     * This will be used to fetch Obstacle data when saving a level.
     *
     * @return the Obstacle object
     */
    public Obstacle getObstacle() {
        return obstacle;
    }
}
