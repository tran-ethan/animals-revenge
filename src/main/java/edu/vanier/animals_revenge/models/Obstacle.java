/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import edu.vanier.animals_revenge.controllers.SimulatorController;

/**
 * The BuildingBlock class represents a basic building block in a simulator. Each block has attributes such as
 * shape, type, and size, which determine its appearance and characteristics. The class provides methods to access
 * and modify these attributes, as well as a method to generate a String representation of the block based on its
 * type and size.
 *
 * @author Zachary Tremblay
 */
<<<<<<< Updated upstream:src/main/java/edu/vanier/animals_revenge/models/Obstacle.java
public class Obstacle {

=======
public class BuildingBlock {
    
    /** The default shape of the building block. */
>>>>>>> Stashed changes:src/main/java/edu/vanier/animals_revenge/models/BuildingBlock.java
    private String shape = "square";
    
    /** The type of the building block (e.g., brick, dirt, wood). */
    private String type;
    
     /** The size of the building block (e.g., 16x16, 32x32, 64x64). */
    private String size;
<<<<<<< Updated upstream:src/main/java/edu/vanier/animals_revenge/models/Obstacle.java

    public Obstacle() {}

=======
    
     /**
     * Constructs a BuildingBlock object with default values.
     */
    public BuildingBlock() {}
    
    
>>>>>>> Stashed changes:src/main/java/edu/vanier/animals_revenge/models/BuildingBlock.java
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
