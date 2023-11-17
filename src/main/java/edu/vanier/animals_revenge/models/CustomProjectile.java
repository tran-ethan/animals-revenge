/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 *
 * @author macke
 */
public class CustomProjectile implements Serializable{

    
    private double size;
    //the Color, Image, and shape, are not serializable by default thus the transient keyword must be used
    //must manually serialize them with custom methods
    private transient Shape shape;
    private transient Color color;
    private transient Image img;

    public CustomProjectile(Shape shape, double size, Color color, Image img) {
        this.shape = shape;
        this.size = size;
        this.color = color;
        this.img = img;
    }
    
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    
    
    
    

}
