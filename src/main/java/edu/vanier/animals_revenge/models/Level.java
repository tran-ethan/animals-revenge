package edu.vanier.animals_revenge.models;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Level implements Serializable {

    List<Obstacle> obstacles;

    public Level() {
        obstacles = new ArrayList<>();
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public void spawnObstacles() {
        for (Obstacle obstacle: obstacles) {
            FXGL.spawn("obstacle", new SpawnData().put("obstacle", obstacle));
        }
    }
}
