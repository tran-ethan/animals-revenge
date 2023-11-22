/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.vanier.animals_revenge.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author macke
 */
public class HelpButtonController {

    public HelpButtonController() {

        URL urlPath = getClass().getResource("/other/README.md");
        String stringPath = urlPath.getPath().replace("%20", " ");

        File file = new File(stringPath);

        //opening file
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(file);
            } else {
                System.out.println("Failed to open this file...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
