package edu.vanier.animals_revenge.models;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * This class represents a level in the game containing obstacles.
 * This class contains the data of all the obstacles within a single level, and will
 * be used to save and load levels from the menu.
 *
 * @author Ethan Tran
 */
public class Level implements Serializable {

    /**
     * The list of obstacles in the level.
     */
    List<Obstacle> obstacles;

    /**
     * Constructs a new Level object with an empty list of obstacles.
     */
    public Level() {
        obstacles = new ArrayList<>();
    }

    /**
     * Adds an obstacle to the level.
     *
     * @param obstacle the obstacle to add
     */
    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    /**
     * Spawns all obstacles in the level using FXGL.
     * Each obstacle is spawned based on its properties (density, friction, position, rotation, etc.)
     */
    public void spawnObstacles() {
        for (Obstacle obstacle: obstacles) {
            FXGL.spawn("obstacle", new SpawnData().put("obstacle", obstacle));
        }
    }
}
