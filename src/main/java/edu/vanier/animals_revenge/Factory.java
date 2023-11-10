package edu.vanier.animals_revenge;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;

public class Factory implements EntityFactory {
    @Spawns("projectile")
    public Entity spawnProjectile(SpawnData data) {
        double vX = data.get("vX");
        double vY = data.get("vY");

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(0.8f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 5, vY * 5));

        return FXGL.entityBuilder(data)
                .at(data.getX(), data.getY())
                .type(Type.PROJECTILE)
                .view("soccer.png")
                .bbox(new HitBox(BoundingShape.circle(32)))
                .with(physics)
                .with(new DraggableComponent())
                .build();
    }

    @Spawns("obstacle")
    public Entity spawnObstacle(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.3f));
        String imgFile = data.get("img");

        return FXGL.entityBuilder(data)
                .at(data.getX(), data.getY())
                // .view(new Rectangle(40, 40, Color.RED))
                .type(Type.OBSTACLE)
                .view(imgFile)
                .bbox(new HitBox("main", BoundingShape.box(64, 64)))
                .with(physics)
                .build();
    }
}
