package edu.vanier.animals_revenge.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This class is responsible for handling the functionality of a help button. It
 * opens the system's default text editor to view the README file associated
 * with the application.
 *
 * @author macke
 * @version 1.0
 */
public class HelpButtonController {

    /**
     * Constructs a new HelpButtonController.
     *
     */
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
