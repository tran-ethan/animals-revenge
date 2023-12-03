/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author macke
 */
public class CustomProjectileCircle extends CustomProjectile {

    private String fillProperty;
    private double radius;
    private String color;
    private String imgPath;

    public CustomProjectileCircle(double radius, String color, String imgPath, float restitution, float density) {
        super(restitution, density);
        this.radius = radius;
        this.color = color;
        this.imgPath = imgPath;
        this.fillProperty = imgPath;
    }

    public CustomProjectileCircle(double radius, String color, float restitution, float density) {
        super(restitution, density);
        this.radius = radius;
        this.color = color;
        this.fillProperty = color;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getFillProperty() {
        return fillProperty;
    }

    public void setFillProperty(String fillProperty) {
        this.fillProperty = fillProperty;
    }

    

    

}
