package edu.vanier.animals_revenge.models;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.io.Serializable;

/**
 * The Obstacle class represents a basic obstacle in a simulator.
 * Each obstacle has attributes such as shape, type, and size, which determine its
 * appearance and characteristics. The class provides methods to access and
 * modify these attributes, as well as a method to generate a String
 * representation of the obstacle based on its type and size.
 *
 * @author Zachary Tremblay
 * @author Ethan Tran
 */
public class Obstacle implements Serializable {

    /**
     * The shape of the obstacle.
     */
    private final String shape;

    /**
     * The type of the obstacle (e.g., brick, dirt, wood).
     */
    private final String type;

    /**
     * An integer representing the size (1 through 5).
     */
    private final int size;

    /**
     * The rotation angle of the obstacle.
     */
    private double rotate;

    private final float friction;

    /**
     * The x-coordinate of the top-left of the obstacle.
     */
    private double x;

    /**
     * The y-coordinate of the top-left of the obstacle.
     */
    private double y;

    /**
     * Constructs an Obstacle object with specified values.
     *
     * @param id       An identifier representing by the selected obstacle in the simulator controller
     * @param size     the size of the obstacle (1 through 5)
     * @param rotate   the rotation angle of the obstacle
     * @param friction the friction coefficient of the obstacle
     */
    public Obstacle(String id, int size, double rotate, float friction) {
        this.shape = id.substring(0, id.length() - 1);
        this.type = setType(id.charAt(id.length() - 1));
        this.size = size;
        this.rotate = rotate;
        this.friction = friction;
    }

    /**
     * Sets the obstacle's type based on the given character.
     *
     * @param c the character representing the obstacle type
     * @return the type of the obstacle
     */
    public String setType(char c) {
        return switch (c) {
            case '1' -> "brick";
            case '2' -> "dirt";
            case '3' -> "wood";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

    /**
     * Retrieves the JavaFX shape of the obstacle based on its attributes. This will be used in the entity
     * factory to spawn obstacle.
     *
     * @return the shape representing the obstacle
     */
    public Shape getShape() {
        int[] sizes = {16, 32, 64, 128, 256};
        int size = sizes[this.size - 1];

        Shape shape;
        if (this.shape.equals("circle")) {
            int radius = size / 2;
            shape = new Circle(radius);

            // Centers the center of the circle at the cursor
            shape.setTranslateX(radius);
            shape.setTranslateY(radius);
            shape.setFill(new ImagePattern(new Image(String.format("/assets/textures/%s.png", type))));
        } else if (this.shape.equals("rectangle")) {
            // Elongate the rectangle to balance width and height
            int w = size / 2;
            int h = size * 2;
            shape = new Rectangle(w, h);
            shape.setFill(new ImagePattern(new Image(String.format("/assets/textures/long_%s.png", type))));
        } else {
            // Default shape is a square
            shape = new Rectangle(size, size);
            shape.setFill(new ImagePattern(new Image(String.format("/assets/textures/%s.png", type))));
        }


        return shape;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRotate() {
        return rotate;
    }

    public float getFriction() {
        return friction;
    }

    /**
     * Retrieves the density of the obstacle based on its type. The density of the
     * obstacle depends on the type of material used.
     *
     * @return the density of the obstacle material in kg/m^2
     */
    public float getDensity() {
        // Density order: brick > wood > dirt
        return switch (type) {
            case "brick" -> 0.8f;
            case "wood" -> 0.3f;
            case "dirt" -> 0.05f;
            default -> throw new IllegalStateException("Illegal type: " + type);
        };
    }
}
