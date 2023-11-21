/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import javafx.scene.image.Image;

/**
 *
 * @author macke
 */
public class CustomProjectileSquare extends CustomProjectile{
    
    private static final long serialVersionUID = 1597950689045739892L;
    
    private double ShapeWidth;
    
    private double ShapeHeight;
    
    private String colour;
    
    private String imgPath;

    public CustomProjectileSquare() {
    }
    
    
    
    public CustomProjectileSquare(double width, double height, String colour, String imgPath) {
        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
        this.imgPath = imgPath;
    }
    
    public CustomProjectileSquare(double width, double height, String colour) {
        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
    }
    
    

    public double getShapeWidth() {
        return ShapeWidth;
    }

    public void setShapeWidth(double ShapeWidth) {
        this.ShapeWidth = ShapeWidth;
    }

    public double getShapeHeight() {
        return ShapeHeight;
    }

    public void setShapeHeight(double ShapeHeight) {
        this.ShapeHeight = ShapeHeight;
    }

    
    public void setWidth(double width) {
        this.ShapeWidth = width;
    }

    public void setHeight(double height) {
        this.ShapeHeight = height;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    
    public String getImgPath() {
        return imgPath;
    }

   
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    
    
    
}
