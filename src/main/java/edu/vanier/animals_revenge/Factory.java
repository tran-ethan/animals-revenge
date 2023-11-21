package edu.vanier.animals_revenge;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Factory implements EntityFactory {

    @Spawns("projectile")
    public Entity spawnProjectile(SpawnData data) {
        double vX = data.get("vX");
        double vY = data.get("vY");

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(0.7f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 3, vY * 3));

        String imgPath = data.get("img");

        Image image = new Image("file:" + imgPath);

        return FXGL.entityBuilder(data)
                .at(data.getX(), data.getY())
                .type(Type.PROJECTILE)
                .view(imgPath)
                .bbox(new HitBox(BoundingShape.circle(14)))
                .with(physics)
                .with(new DraggableComponent())
                .build();
    }

    @Spawns("customProjectile")
    public Entity spawnCustomProjectile(SpawnData data) {
        double vX = data.get("vX");
        double vY = data.get("vY");

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(0.3f).restitution(0.7f));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 3, vY * 3));

        if (data.get("img") != "null") {

            CustomProjectileSquare squareProjectile = new CustomProjectileSquare(
                    data.get("width"),
                    data.get("height"),
                    data.get("colour"),
                    data.get("img"));

            String imgPath = data.get("img");
            Image image = new Image("file:" + imgPath);

            System.out.println(image.getHeight());

            ImageView imgView = new ImageView(image);

            imgView.setFitHeight(data.get("height"));
            imgView.setFitWidth(data.get("width"));
            
            double imageWidth = imgView.getFitWidth();
            double imageHeight = imgView.getFitHeight();
            
            return FXGL.entityBuilder(data)
                    .at(data.getX(), data.getY())
                    .type(Type.PROJECTILE)
                    .view(imgView)
                    .bbox(new HitBox(BoundingShape.box(imageWidth, imageHeight)))
                    .with(physics)
                    .with(new DraggableComponent())
                    .build();
        } else {

            Rectangle rect = new Rectangle(data.get("width"), data.get("height"));

            rect.setFill(Color.web(data.get("colour")));

            return FXGL.entityBuilder(data)
                    .at(data.getX(), data.getY())
                    .type(Type.PROJECTILE)
                    .view(rect)
                    .bbox(new HitBox(BoundingShape.box(rect.getWidth(), rect.getHeight())))
                    .with(physics)
                    .with(new DraggableComponent())
                    .build();
        }

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
                .viewWithBBox(imgFile)
                // .bbox(new HitBox(BoundingShape.box(32, 32)))
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
