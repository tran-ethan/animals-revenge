/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

/**
 * This class represents a custom projectile
 * shaped as a square in the application. 
 *
 * @author macke
 * @version 1.0
 */
public class CustomProjectileSquare extends CustomProjectile {

    private String fillProperty;

    private double ShapeWidth;

    private double ShapeHeight;

    private String colour;

    private String imgPath;

    /**
     * Constructs a new CustomProjectileSquare with the specified width,
     * height, color, image path, restitution, and density properties.
     *
     * @param width The horizontal size of the square projectile.
     * @param height The vertical size of the square projectile.
     * @param colour The color of the square projectile.
     * @param imgPath The file path to the image used as a fill for the square
     * projectile.
     * @param restitution The bounciness property of the projectile.
     * @param density The mass distribution property of the projectile.
     */
    public CustomProjectileSquare(double width, double height, String colour, String imgPath, float restitution, float density) {
        super(restitution, density);

        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
        this.imgPath = imgPath;
        this.fillProperty = imgPath;

    }

    /**
     * Constructs a new CustomProjectileSquare with the specified width,
     * height, color, restitution, and density properties. Uses the colour as the
     * fill property.
     *
     * @param width The horizontal size of the square projectile.
     * @param height The vertical size of the square projectile.
     * @param colour The color of the square projectile.
     * @param restitution The bounciness property of the projectile.
     * @param density The mass distribution property of the projectile.
     */
    public CustomProjectileSquare(double width, double height, String colour, float restitution, float density) {

        super(restitution, density);

        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
        this.fillProperty = colour;

    }

    /**
     * Retrieves the fill property of the square projectile.
     *
     * @return The color or image path used as the fill for the square
     * projectile.
     */
    public String getFillProperty() {
        return fillProperty;
    }

    /**
     * Sets the fill property of the square projectile.
     *
     * @param fillProperty The new color or image path used as the fill for the
     * square projectile.
     */
    public void setFillProperty(String fillProperty) {
        this.fillProperty = fillProperty;
    }

    /**
     * Retrieves the width property of the square projectile.
     *
     * @return The horizontal size of the square projectile.
     */
    public double getShapeWidth() {
        return ShapeWidth;
    }

    /**
     * Sets the width property of the square projectile.
     *
     * @param ShapeWidth The new horizontal size for the square projectile.
     */
    public void setShapeWidth(double ShapeWidth) {
        this.ShapeWidth = ShapeWidth;
    }

    /**
     * Retrieves the height property of the square projectile.
     *
     * @return The vertical size of the square projectile.
     */
    public double getShapeHeight() {
        return ShapeHeight;
    }

    /**
     * Sets the height property of the square projectile.
     *
     * @param ShapeHeight The new vertical size for the square projectile.
     */
    public void setShapeHeight(double ShapeHeight) {
        this.ShapeHeight = ShapeHeight;
    }

    /**
     * Sets the width property of the square projectile.
     *
     * @param width The new vertical size for the square projectile.
     */
    public void setWidth(double width) {
        this.ShapeWidth = width;
    }

    /**
     * Sets the height property of the square projectile.
     *
     * @param height The new vertical size for the square projectile.
     */
    public void setHeight(double height) {
        this.ShapeHeight = height;
    }

    /**
     * Retrieves the colour property of the square projectile
     *
     */
    
    public String getColour() {
        return colour;
    }
    
    /**
     * Sets the colour property of the square projectile
     *
     * @param colour
     */

    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * Retrieves the image path property of the square projectile.
     *
     * 
     */
    public String getImgPath() {
        return imgPath;
    }

    
    /**
     * Sets the image path property of the square projectile.
     *
     * 
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

}
