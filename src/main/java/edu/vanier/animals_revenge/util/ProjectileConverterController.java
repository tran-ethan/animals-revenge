package edu.vanier.animals_revenge.util;

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
 * This class is responsible for converting a CustomProjectile object to one of
 * its subclasses to access property-specific fields in those classes.
 *
 * @author Mackenzie Rouchdy
 */
public class ProjectileConverterController {

    /**
     * Creates a JavaFX shape from a {@link CustomProjectile} object. The shape
     * type is determined by the specific subclass of the custom projectile
     * ({@link CustomProjectileCircle} or {@link CustomProjectileSquare}). If
     * the projectile type is not recognized, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param projectile The custom projectile object to convert.
     * @return A JavaFX shape representing the custom projectile.
     * @throws IllegalArgumentException If the projectile type is not
     * recognized.
     */
    public Shape createShapeFromProjectile(CustomProjectile projectile) {
        if (projectile instanceof CustomProjectileCircle) {
            return createCircle((CustomProjectileCircle) projectile);
        } else if (projectile instanceof CustomProjectileSquare) {
            return createSquare((CustomProjectileSquare) projectile);
        } else {
            throw new IllegalArgumentException("Invalid projectile type!");
        }
    }

    /**
     * Creates a JavaFX circle shape from a {@link CustomProjectileCircle}
     * object.
     *
     * @param circleProjectile The custom projectile circle object to convert.
     * @return A JavaFX circle shape representing the custom projectile.
     */
    private Circle createCircle(CustomProjectileCircle circleProjectile) {
        Circle circle = new Circle(circleProjectile.getRadius());
        setFill(circle, circleProjectile.getFillProperty());
        return circle;
    }

    /**
     * Creates a JavaFX rectangle shape from a {@link CustomProjectileSquare}
     * object.
     *
     * @param squareProjectile The custom projectile square object to convert.
     * @return A JavaFX rectangle shape representing the custom projectile.
     */
    private Rectangle createSquare(CustomProjectileSquare squareProjectile) {
        Rectangle square = new Rectangle(squareProjectile.getShapeWidth(), squareProjectile.getShapeHeight());
        setFill(square, squareProjectile.getFillProperty());
        return square;
    }

    /**
     * Sets the fill property of a JavaFX shape based on the specified fill
     * value. The fill can be either a color or an image path. If the fill value
     * cannot be parsed as a color, it is treated as an image path. If the image
     * path is invalid, an error message is printed to the console.
     *
     * @param shape The JavaFX shape to set the fill for.
     * @param fill The fill value, which can be a color or an image path.
     */
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
