/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.models.CustomProjectile;
import edu.vanier.animals_revenge.models.CustomProjectileCircle;
import edu.vanier.animals_revenge.models.CustomProjectileSquare;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 *
 * @author macke
 * 
 * The purpose of this class is to convert a Custom Projectile object to one of its subclasses in order
 * to access the property-specific fields in those classes
 */
public class ProjectileConverterController {

    public Shape createShapeFromProjectile(CustomProjectile projectile) {
        if (projectile instanceof CustomProjectileCircle) {
            return createCircle((CustomProjectileCircle) projectile);
        } else if (projectile instanceof CustomProjectileSquare) {
            return createSquare((CustomProjectileSquare) projectile);
        } else {
            throw new IllegalArgumentException("Invalid projectile type!");
        }
    }

    private Circle createCircle(CustomProjectileCircle circleProjectile) {
        Circle circle = new Circle(circleProjectile.getRadius());
        setFill(circle, circleProjectile.getFillProperty());
        return circle;
    }

    private Rectangle createSquare(CustomProjectileSquare squareProjectile) {
        Rectangle square = new Rectangle(squareProjectile.getShapeWidth(), squareProjectile.getShapeHeight());
        setFill(square, squareProjectile.getFillProperty());
        return square;
    }
    
    private void setFill(Shape shape, String fill) {
        try {
            //will first try to parse as a colour
            shape.setFill(Color.web(fill));
        } catch (IllegalArgumentException e) {
            try {
                shape.setFill(new ImagePattern(new Image(fill)));
            } catch (Exception f) {
                System.out.println("Error During Filling Process!");
            }
        }
    }

}
