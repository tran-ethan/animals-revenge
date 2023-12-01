/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import java.io.Serializable;
import javafx.scene.shape.Shape;


/**
 *
 * @author macke
 */
public class CustomProjectile extends Shape implements Serializable {
    
    float restitution;
    double mass;
    float density;

    public CustomProjectile(float restitution, double mass, float density) {
        this.restitution = restitution;
        this.mass = mass;
        this.density = density;
    }

    public float getRestitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }
    
    

    
    
}
