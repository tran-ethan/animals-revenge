package edu.vanier.animals_revenge.models;

import com.almasb.fxgl.entity.component.Component;

/**
 * The ObstacleComponent class represents a data component attached to entities
 * that act as obstacles in the game.
 * <p>
 * This component holds an Obstacle object associated with the entity, and is used as
 * a wrapper for containing data of an Obstacle for entities that are obstacles in the game world.
 * <p>
 * This class is necessary because FXGL utilizes an entity-component system (ECS),
 * whereby entities in the game world are represented not as a standard Java class,
 * but as entities to which component(s) can be attached. Components model behaviors or
 * contains relevant data regarding the entity, and multiple components can be attached
 * to a single entity. Therefore, the only way to obtain relevant information for an obstacle
 * is to attach an obstacle component to it, which contains information of the Obstacle class.
 *
 * @author Ethan Tran
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
