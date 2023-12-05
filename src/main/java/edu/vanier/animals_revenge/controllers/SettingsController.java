package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import edu.vanier.animals_revenge.util.SavedSetting;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;
import static com.almasb.fxgl.dsl.FXGL.spawn;

/**
 *
 * @author Anton Lisunov
 */
public class SettingsController extends Controller implements UIController {

    @FXML
    private ChoiceBox<String> chbScene;

    @FXML
    private ColorPicker clrPickScene;

    @FXML
    private ChoiceBox<String> chbMusic;

    @FXML
    private Slider sldMusic = new Slider(0, 100, 20);

    @FXML
    private Slider sldSound = new Slider(0, 100, 20);

    @FXML
    private StackPane paneMenu;

    private final String[] dataBackground = {"Moon", "Mountains", "Ocean", "Sky", "Snow"};

    private final String[] dataMusic = {"Music1", "Music2", "Music3"};

    private final Rectangle menuBackground = new Rectangle(400, 500);

    private SavedSetting settings;

    @Override
    public void init() {
        menuBackground.setFill(Color.LIGHTBLUE);
        menuBackground.setOpacity(0.3);
        menuBackground.setArcHeight(50);
        menuBackground.setArcWidth(50);
        paneMenu.getChildren().add(0, menuBackground);

        settings = new SavedSetting();

        setupChoiceBox();

        if ("".equals(settings.getBackground())) {
            clrPickScene.setValue(settings.getColor());
        }

        sldMusic.setValue(100 * settings.getMusicVolume());
        sldSound.setValue(100 * settings.getSoundVolume());
    }

    public void setBackground(String chosenBackground) {
        clrPickScene.setValue(Color.WHITE);
        settings.setBackground(chosenBackground);
        spawn("background", new SpawnData().put("background", settings.getBackground()));

    }

    private void getBackground(ActionEvent event) {
        switch (chbScene.getValue()) {
            case "Moon" -> {
                setBackground("moon.png");
                menuBackground.setFill(Color.DARKGREY);
            }
            case "Mountains" -> {
                setBackground("mountains.png");
                menuBackground.setFill(Color.LIGHTCYAN);
            }
            case "Ocean" -> {
                setBackground("ocean.png");
                menuBackground.setFill(Color.LIGHTBLUE);
            }
            case "Sky" -> {
                setBackground("sky.png");
                menuBackground.setFill(Color.AZURE);
            }
            case "Snow" -> {
                setBackground("snow.png");
                menuBackground.setFill(Color.WHITE);
            }
        }
    }

    private void getMusic(ActionEvent event) {
        switch (chbMusic.getValue()) {
            case "Music1" -> settings.setMusic("music1.mp3");
            case "Music2" -> settings.setMusic("music2.mp3");
            case "Music3" -> settings.setMusic("music3.mp3");
        }
    }

    public void setupChoiceBox() {
        chbScene.getItems().addAll(dataBackground);
        chbScene.setOnAction(this::getBackground);
        chbScene.setConverter(new StringConverter<>() {
            @Override
            public String toString(String s) {
                return (s == null) ? transformWord(settings.getBackground()) : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }

        });

        chbMusic.getItems().addAll(dataMusic);
        chbMusic.setOnAction(this::getMusic);
        chbMusic.setConverter(new StringConverter<>() {
            @Override
            public String toString(String s) {
                return (s == null) ? transformWord(settings.getMusicType()) : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }

        });
    }

    public static String transformWord(String input) {
        String[] parts = input.split("\\.");

        if (parts.length > 0) {
            String word = parts[0];
            if (word.isEmpty()) {
                return "Choose background";
            }
            return Character.toUpperCase(word.charAt(0)) + word.substring(1);

        }
        return "Choose background";
    }

    @FXML
    public void setSoundVolume() {
        settings.setSoundVolume(sldSound.getValue() / 100);
    }

    @FXML
    public void setMusicVolume() {
        settings.setMusicVolume(sldMusic.getValue() / 100);
    }

    /**
     * Changes the color to the color picker's value
     */
    @FXML
    public void chooseColor() {
        settings.setColor(clrPickScene.getValue());
        menuBackground.setFill(Color.LIGHTBLUE);
        spawn("background", new SpawnData().put("background", "").put("color", settings.getColor()));
    }

}
