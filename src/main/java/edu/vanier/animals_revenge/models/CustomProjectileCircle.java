/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

/**
 * This class represents a custom projectile
 * shaped as a circle in the application. 
 *
 * @author macke
 * @version 1.0
 */
public class CustomProjectileCircle extends CustomProjectile {

    private String fillProperty;
    private double radius;
    private String color;
    private String imgPath;

    /**
     * Constructs a new CustomProjectileCircle with the specified
     * radius, color, image path, restitution, and density properties.
     *
     * @param radius The size of the circular projectile.
     * @param color The color of the circular projectile.
     * @param imgPath The file path to the image used as a fill for the circular
     * projectile.
     * @param restitution The bounciness property of the projectile.
     * @param density The mass distribution property of the projectile.
     */
    public CustomProjectileCircle(double radius, String color, String imgPath, float restitution, float density) {
        super(restitution, density);
        this.radius = radius;
        this.color = color;
        this.imgPath = imgPath;
        this.fillProperty = imgPath;
    }

    /**
     * Constructs a new CustomProjectileCircle with the specified
     * radius, color, restitution, and density properties. Uses the color as the
     * fill property.
     *
     * @param radius The size of the circular projectile.
     * @param color The color of the circular projectile.
     * @param restitution The bounciness property of the projectile.
     * @param density The mass distribution property of the projectile.
     */
    public CustomProjectileCircle(double radius, String color, float restitution, float density) {
        super(restitution, density);
        this.radius = radius;
        this.color = color;
        this.fillProperty = color;
    }

    /**
     * Retrieves the radius property of the circular projectile.
     *
     * @return The size of the circular projectile.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius property of the circular projectile.
     *
     * @param radius The new size for the circular projectile.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Retrieves the color property of the circular projectile.
     *
     * @return The color of the circular projectile.
     */
    public String getColor() {
        return color;
    }

    /**
     * Sets the color property of the circular projectile.
     *
     * @param color The new color for the circular projectile.
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Retrieves the image path property of the circular projectile.
     *
     * @return The file path to the image used as a fill for the circular
     * projectile.
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * Sets the image path property of the circular projectile.
     *
     * @param imgPath The new file path for the image used as a fill for the
     * circular projectile.
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * Retrieves the fill property of the circular projectile.
     *
     * @return The color or image path used as the fill for the circular
     * projectile.
     */
    public String getFillProperty() {
        return fillProperty;
    }

    /**
     * Sets the fill property of the circular projectile.
     *
     * @param fillProperty The new color or image path used as the fill for the
     * circular projectile.
     */
    public void setFillProperty(String fillProperty) {
        this.fillProperty = fillProperty;
    }

}
