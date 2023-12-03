/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * The Obstacle class represents a basic obstacle in a simulator.
 * Each obstacle has attributes such as shape, type, and size, which determine its
 * appearance and characteristics. The class provides methods to access and
 * modify these attributes, as well as a method to generate a String
 * representation of the obstacle based on its type and size.
 *
 * @author Zachary Tremblay
 */
public class Obstacle {

    /**
     * The default shape of the obstacle.
     */
    private String shape;

    /**
     * The type of the obstacle (e.g., brick, dirt, wood).
     */
    private String type;

    /**
     * The size of the obstacle (e.g., 16x16, 32x32, 64x64).
     */
    private int size;

    private int rotate;

    private float friction;

    /**
     * Constructs a Obstacle object with default values.
     */
    public Obstacle(String id, int size, int rotate, float friction) {
        this.shape = id.substring(0, id.length() - 1);
        this.type = setType(id.charAt(id.length() - 1));
        this.size = size;
        this.rotate = rotate;
        this.friction = friction;
    }

    public String setType(char c) {
        return switch (c) {
            case '1' -> "brick";
            case '2' -> "dirt";
            case '3' -> "wood";
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

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

    public int getRotate() {
        return rotate;
    }

    public float getFriction() {
        return friction;
    }

    public float getDensity() {
        // Density of obstacle depends on the type of material (brick, wood, dirt)
        return switch (type) {
            case "brick" -> 0.8f;
            case "wood" -> 0.3f;
            case "dirt" -> 0.05f;
            default -> throw new IllegalStateException("Illegal type: " + type);
        };
    }
}
