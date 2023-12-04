package edu.vanier.animals_revenge.controllers;

import com.almasb.fxgl.ui.UIController;
import edu.vanier.animals_revenge.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.StringConverter;

/**
 *
 * @author Anton Lisunov
 */
public class SettingsController implements UIController {

    @FXML
    private ChoiceBox<String> chbScene;

    @FXML
    private ColorPicker clrPickScene;

    @FXML
    private ChoiceBox<String> chbMusic;

    @FXML
    private Slider sldMusic;

    @FXML
    private Slider slbSound;

    @FXML
    private AnchorPane bkgnSettings;

    @FXML
    private StackPane paneMenu;
     
    private final String[] dataBackground = {"Moon","Mountains","Ocean","Sky","Snow"};
    
    private final String[] dataMusic = {null};
    
    private Rectangle menuBackground = new Rectangle(400,500);
    
    @Override
    public void init() {
        
        chbScene.getItems().addAll(dataBackground);
        chbScene.setOnAction(this::getBackground);
        chbScene.setConverter(new StringConverter<String>(){
            @Override
            public String toString(String s) {
                return (s == null) ? "Select Background" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        
        });
        
        
        chbMusic.getItems().addAll(dataMusic);
        chbMusic.setOnAction(this::getBackground);
        chbMusic.setConverter(new StringConverter<String>(){
            @Override
            public String toString(String s) {
                return (s == null) ? "Select Music" : s;
            }

            @Override
            public String fromString(String s) {
                return null;
            }
        
            
        });
        
        
        
        menuBackground.setFill(Color.LIGHTBLUE);
        menuBackground.setOpacity(0.3);
        menuBackground.setArcHeight(50);
        menuBackground.setArcWidth(50);
        
        paneMenu.getChildren().add(0,menuBackground);
          setBackground("sky.png");
        

    }

    public void setBackground(String chosenBackground) {
        Image image = new Image("/assets/textures/background/" + chosenBackground);
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        bkgnSettings.setBackground(background);
        
        
    }

    private void getBackground(ActionEvent event) {
        switch(chbScene.getValue()){
            case "Moon" -> {setBackground("moon.png");menuBackground.setFill(Color.DARKGREY);}
            case "Mountains" -> {setBackground("mountains.png");menuBackground.setFill(Color.LIGHTCYAN);}
            case "Ocean" -> {setBackground("ocean.png");menuBackground.setFill(Color.LIGHTBLUE);}
            case "Sky" -> {setBackground("sky.png");menuBackground.setFill(Color.AZURE);}
            case "Snow" -> {setBackground("snow.png");menuBackground.setFill(Color.WHITE);}
        }
    }
    
    @FXML
    public void goHome() {
        MainApp.loadFXML("Home.fxml", new HomeController());
    }
    
     @FXML
    void chooseColor(ActionEvent event) {
        
            menuBackground.setFill(clrPickScene.getValue());
        
    }


    

}
