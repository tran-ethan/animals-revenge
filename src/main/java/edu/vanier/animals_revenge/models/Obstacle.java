/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import edu.vanier.animals_revenge.controllers.SimulatorController;

/**
 * The Obstacle class represents a basic obstacle in a simulator.
 * Each obstacle has attributes such as shape, type, and size, which determine its
 * appearance and characteristics. The class provides methods to access and
 * modify these attributes, as well as a method to generate a String
 * representation of the obstacle based on its type and size.
 *
 * @author Zachary Tremblay
 */
public class Obstacle {

    /**
     * The default shape of the obstacle.
     */
    private String shape = "square";

    /**
     * The type of the obstacle (e.g., brick, dirt, wood).
     */
    private String type;

    /**
     * The size of the obstacle (e.g., 16x16, 32x32, 64x64).
     */
    private String size;

    /**
     * Constructs a Obstacle object with default values.
     */
    public Obstacle() {
    }

    public String getShape() {
        return SimulatorController.getSelected().getId().substring(0, SimulatorController.getSelected().getId().length() - 1);
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getType() {
        String tempType = null;
        switch (SimulatorController.getSelected().getId().substring(SimulatorController.getSelected().getId().length() - 1)) {
            case "1" ->
                tempType = "brick";
            case "2" ->
                tempType = "dirt";
            case "3" ->
                tempType = "wood";

        }
        return tempType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        String tempSize = null;
        switch (SimulatorController.getSize()) {
            case 1 ->
                tempSize = "16x16";
            case 2 ->
                tempSize = "32x32";
            case 3 ->
                tempSize = "64x64";
            case 4 ->
                tempSize = "128x128";
            case 5 ->
                tempSize = "256x256";
        }
        return tempSize;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%s%s.png", getType(), getSize());
    }

}
