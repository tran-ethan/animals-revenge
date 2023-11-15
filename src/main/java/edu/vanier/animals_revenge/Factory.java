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
    
    /* @Spawns("projectile")
    public Entity spawnProjectile(SpawnData data) {
    double vX = data.get("vX");
    double vY = data.get("vY");
    
    PhysicsComponent physics = new PhysicsComponent();
    physics.setBodyType(BodyType.DYNAMIC);
    physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(0.7f));
    physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 3, vY * 3));
    
    return FXGL.entityBuilder(data)
    .at(data.getX(), data.getY())
    .type(Type.PROJECTILE)
    .view("soccer.png")
    .bbox(new HitBox(BoundingShape.circle(32)))
    .with(physics)
    .with(new DraggableComponent())
    .build();
    }*/
    
    @Spawns("projectile")
public Entity spawnProjectile(SpawnData data, String shapeType, String imageFileName, String colorRepresentation) {
    PhysicsComponent physics = new PhysicsComponent();
    physics.setBodyType(BodyType.DYNAMIC);
    physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(0.7f));
    
    
    //placeholder variables for linear velocity
    physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(50 * 3, 50 * 3));

    Entity projectile;

    if ("circle".equals(shapeType)) {
        projectile = createCircleProjectile(data, imageFileName, colorRepresentation);
    } else if ("rectangle".equals(shapeType)) {
        projectile = createRectangleProjectile(data, imageFileName, colorRepresentation);
    } else {
        throw new IllegalArgumentException("Invalid shape type: " + shapeType);
    }

    return projectile;
}

private Entity createCircleProjectile(SpawnData data, String imageFileName, String colorRepresentation) {
    return FXGL.entityBuilder(data)
            .type(Type.PROJECTILE)
            .viewWithBBox(FXGL.texture(imageFileName, 32, 32))
            .bbox(new HitBox(BoundingShape.circle(16)))
            .with(new PhysicsComponent())
            .with(new DraggableComponent())
            .build();
}

private Entity createRectangleProjectile(SpawnData data, String imageFileName, String colorRepresentation) {
    return FXGL.entityBuilder(data)
            .type(Type.PROJECTILE)
            .viewWithBBox(FXGL.texture(imageFileName, 32, 32))
            .bbox(new HitBox(BoundingShape.box(32, 32)))
            .with(new PhysicsComponent())
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
                .type(Type.OBSTACLE)
                .view(imgFile)
                .bbox(new HitBox(BoundingShape.box(64, 64)))
                .with(physics)
                .build();
    }

    @Spawns("wall")
    public Entity spawnWall(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);
        double width = data.get("width");
        double height = data.get("height");
        return FXGL.entityBuilder(data)
                .at(data.getX(), data.getY())
                .type(Type.WALL)
                // .view(new Rectangle(width, height, Color.RED))
                .bbox(new HitBox(BoundingShape.box(width, height)))
                .with(physics)
                .build();
    }

    @Spawns("launcher")
    public Entity spawnLauncher(SpawnData data) {
        return FXGL.entityBuilder(data)
                .at(data.getX(), data.getY())
                .type(Type.LAUNCHER)
                .view("cannon.png")
                .build();
    }
}
