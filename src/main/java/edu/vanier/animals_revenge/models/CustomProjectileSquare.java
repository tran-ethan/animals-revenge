/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author macke
 */
public class CustomProjectileSquare extends CustomProjectile{
    
    private String fillProperty;
    
    private double ShapeWidth;
    
    private double ShapeHeight;
    
    private String colour;
    
    private String imgPath;
    
    public CustomProjectileSquare(double width, double height, String colour, String imgPath, float restitution, double mass, float density) {
        super(restitution, mass, density);
        
        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
        this.imgPath = imgPath;
        this.fillProperty = imgPath;
        
    }
    
    public CustomProjectileSquare(double width, double height, String colour, float restitution, double mass, float density) {
        
        super(restitution, mass, density);
        
        
        this.ShapeWidth = width;
        this.ShapeHeight = height;
        this.colour = colour;
        this.fillProperty = colour;
       
    }

    public String getFillProperty() {
        return fillProperty;
    }

    public void setFillProperty(String fillProperty) {
        this.fillProperty = fillProperty;
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
