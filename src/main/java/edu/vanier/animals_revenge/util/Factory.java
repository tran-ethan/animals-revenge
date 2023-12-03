package edu.vanier.animals_revenge.util;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.DraggableComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import edu.vanier.animals_revenge.models.Obstacle;
import edu.vanier.animals_revenge.models.ObstacleComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Factory implements EntityFactory {

    
    //currently only used for the default projectile
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

    @Spawns("customProjectileSquare")
    public Entity spawnCustomProjectileSquare(SpawnData data) {
        double vX = data.get("vX");
        double vY = data.get("vY");

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(data.get("density")).restitution(data.get("restitution")));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 3, vY * 3));
        
       
        float density = data.get("density");
        float restitution = data.get("restitution");
        
       

        if (data.get("img") != "null") {

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
                    .type(Type.CUSTOM_PROJECTILE)
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

    @Spawns("customProjectileCircle")
    public Entity spawnCustomProjectileCircle(SpawnData data) {
        double vX = data.get("vX");
        double vY = data.get("vY");

        double radius = data.get("radius");

        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(new FixtureDef().density(data.get("density")).restitution(data.get("restitution")));
        physics.setOnPhysicsInitialized(() -> physics.setLinearVelocity(vX * 3, vY * 3));

        if (data.get("img") != "null") {

            String imgPath = data.get("img");
            Image image = new Image("file:" + imgPath);

            Circle circle = new Circle(radius);
            circle.setFill(new ImagePattern(image));

            System.out.println(image.getHeight());

            circle.setFill(new ImagePattern(image));

            //center radius in the middle
            circle.setTranslateX(radius);
            circle.setTranslateY(radius);

            return FXGL.entityBuilder(data)
                    .at(data.getX(), data.getY())
                    .type(Type.CUSTOM_PROJECTILE)
                    .view(circle)
                    .bbox(new HitBox(BoundingShape.circle(radius)))
                    .with(physics)
                    .with(new DraggableComponent())
                    .build();
        } else {

            Circle circle = new Circle(radius);

            //converts the string color into a paint object
            circle.setFill(Color.web(data.get("colour")));

            //center radius in the middle
            circle.setTranslateX(radius);
            circle.setTranslateY(radius);

            return FXGL.entityBuilder(data)
                    .at(data.getX(), data.getY())
                    .type(Type.PROJECTILE)
                    .view(circle)
                    .bbox(new HitBox(BoundingShape.circle(radius)))
                    .with(physics)
                    .with(new DraggableComponent())
                    .build();

        }
    }

    @Spawns("obstacle")
    public Entity spawnObstacle(SpawnData data) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);

        Obstacle obstacle = data.get("obstacle");
        Shape shape = obstacle.getShape();

        // Set obstacle density and friction fixtures
        physics.setFixtureDef(new FixtureDef().density(obstacle.getDensity()).friction(obstacle.getFriction()));

        // Default obstacle is a 16x16 square
        HitBox hitbox = new HitBox(BoundingShape.box(16, 16));

        // Customize the shape of the obstacle
        double x = 0;
        double y = 0;
        if (shape instanceof Rectangle rectangle) {
            hitbox = new HitBox(BoundingShape.box(rectangle.getWidth(), rectangle.getHeight()));
            // Get X and Y coords for the top left corner (spawn location)
            x = data.getX() - rectangle.getWidth() / 2;
            y = data.getY() - rectangle.getHeight() / 2;
        } else if (shape instanceof Circle circle) {
            hitbox = new HitBox(BoundingShape.circle(circle.getRadius()));
            // Get X and Y coords for the top left corner of circle
            x = data.getX() - circle.getRadius();
            y = data.getY() - circle.getRadius();
        }

        if (obstacle.getY() != 0) {
            x = obstacle.getX();
            y = obstacle.getY();
        }

        return FXGL.entityBuilder(data)
                .at(x, y)
                .type(Type.OBSTACLE)
                .rotate(obstacle.getRotate())
                .view(shape)
                .bbox(hitbox)
                .with(physics)
                .with(new ObstacleComponent(obstacle))
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
    
    @Spawns("background")
    public Entity newBackground(SpawnData data) {
        return FXGL.entityBuilder()
                .view(new ScrollingBackgroundView(new Image("/assets/textures/background/"+ data.get("background")), FXGL.getAppWidth(), FXGL.getAppHeight()))
                .zIndex(-500)
                .with(new IrremovableComponent())
                .build();
    }
}
