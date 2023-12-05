package edu.vanier.animals_revenge.models;

import java.io.Serializable;
import javafx.scene.shape.Shape;

/**
 * This class represents a custom projectile in the application.
 *
 * @author Mackenzie Rouchdy
 */
public class CustomProjectile extends Shape implements Serializable {

    float restitution;

    float density;

    /**
     * Constructs a new CustomProjectile with the specified restitution
     * and density properties.
     *
     * @param restitution The bounciness property of the projectile.
     * @param density The mass distribution property of the projectile.
     */
    public CustomProjectile(float restitution, float density) {
        this.restitution = restitution;
        this.density = density;
    }

    /**
     * Retrieves the restitution property of the projectile.
     *
     * @return The restitution property of the projectile.
     */
    public float getRestitution() {
        return restitution;
    }

    /**
     * Sets the restitution property of the projectile.
     *
     * @param restitution The new restitution property for the projectile.
     */
    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    /**
     * Retrieves the density property of the projectile.
     *
     * @return The density property of the projectile.
     */
    public float getDensity() {
        return density;
    }

    /**
     * Sets the density property of the projectile.
     *
     * @param density The new density property for the projectile.
     */
    public void setDensity(float density) {
        this.density = density;
    }

}
