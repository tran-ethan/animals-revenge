package edu.vanier.animals_revenge.controllers;

import edu.vanier.animals_revenge.util.ProjectileConverterController;
import edu.vanier.animals_revenge.models.CustomProjectile;
import java.io.File;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * This class is responsible for handling the functionality related to the
 * selection and import of custom projectiles in the application.
 *
 * @author Mackenzie Rouchdy
 */
public class ProjectileSelectionController {

    @FXML
    public HBox customObjectRow;

    public static CustomProjectile finalProjectile;

    public static final Color borderColor = Color.BLACK;

    ArrayList<CustomProjectile> customProjectiles;

    FileChooser fileChooser = new FileChooser();


    /**
     * Initializes the controller. Populates the customObjectRow HBox with
     * existing custom projectiles during initialization.
     */
    @FXML
    public void initialize() {

        customObjectRow.getChildren().clear();

        ProjectileConverterController conv = new ProjectileConverterController();

        customProjectiles = CustomProjectileController.customProjectiles;

        //adding the custom projectiles to the hbox
        //normal rectangle works but custom projectile doesn't
        for (int i = 0; i < customProjectiles.size(); i++) {

            Shape projectile = conv.createShapeFromProjectile(customProjectiles.get(i));

            final int finalIndex = i;

            projectile.setOnMouseEntered(
                    (event) -> projectile.setStroke(borderColor));

            projectile.setOnMouseExited(
                    (event) -> {

                        projectile.setStroke(Color.TRANSPARENT);

                    });

            projectile.setOnMouseClicked((event) -> {

                finalProjectile = customProjectiles.get(finalIndex);

                ((Stage) projectile.getScene().getWindow()).close();

            });

            customObjectRow.getChildren().add(projectile);
        }

    }

    /**
     * Handles the user's request to import a new projectile and display it in
     * the UI.
     *
     * @param event The ActionEvent triggered by clicking the import button.
     */
    @FXML
    void importProjectile(ActionEvent event) {

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

            CustomProjectile p = CustomProjectileController.deserialize(selectedFile.getAbsolutePath());

            customProjectiles.add(p);

            // Add the new projectile to the HBox
            Shape projectile = new ProjectileConverterController().createShapeFromProjectile(p);

            projectile.setOnMouseEntered(
                    (mouseEvent) -> projectile.setStroke(borderColor)
            );

            projectile.setOnMouseExited(
                    (mouseEvent) -> projectile.setStroke(Color.TRANSPARENT)
            );
            projectile.setOnMouseClicked((mouseEvent) -> {

                ProjectileSelectionController.finalProjectile = p;
                ((Stage) projectile.getScene().getWindow()).close();

            });

            customObjectRow.getChildren().add(projectile);

        } else {
            SimulatorController.throwWarning("No File Chosen!", "File Error");
        }

    }

    /**
     * Retrieves the final selected custom projectile.
     *
     * @return The final selected custom projectile.
     */
    public static CustomProjectile getFinalProjectile() {
        return finalProjectile;
    }

    /**
     * Sets the final selected custom projectile.
     *
     * @param finalProjectile The custom projectile to set as the final
     * selection.
     */
    public static void setFinalProjectile(CustomProjectile finalProjectile) {
        ProjectileSelectionController.finalProjectile = finalProjectile;
    }

}
