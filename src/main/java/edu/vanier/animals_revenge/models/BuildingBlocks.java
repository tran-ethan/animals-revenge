/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.models;

import edu.vanier.animals_revenge.controllers.SimulatorController;

/**
 *
 * @author Zachary Tremblay
 */
public class BuildingBlocks {

    private String Shape;
    private String type;
    private String size;

    public BuildingBlocks() {
    }
    
    

    public String getShape() {

        return SimulatorController.getSelected().getId().substring(0, SimulatorController.getSelected().getId().length() - 1);
    }

    public void setShape(String Shape) {
        this.Shape = Shape;
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
        switch(SimulatorController.getSize()){
            case 1 -> tempSize = "16x16";
            case 2 -> tempSize = "32x32";
            case 3 -> tempSize = "64x64";
            case 4 -> tempSize = "128x128";
            case 5 -> tempSize = "256x256";
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
