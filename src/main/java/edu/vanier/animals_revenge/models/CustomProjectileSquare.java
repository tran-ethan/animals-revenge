/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import java.io.Serializable;

/**
 *
 * @author macke
 */
public class CustomProjectileSquare extends CustomProjectile implements Serializable{
    
    private static final long serialVersionUID = 1597950689045739892L;
    
    private double width;
    
    private double height;
    
    private String colour;
    
    private String imgPath;
    
    
    public CustomProjectileSquare(double width, double height, String colour, String imgPath) {
        this.width = width;
        this.height = height;
        this.colour = colour;
        this.imgPath = imgPath;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
