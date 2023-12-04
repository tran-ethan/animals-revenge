package edu.vanier.animals_revenge.util;

import com.almasb.fxgl.audio.Music;
import com.almasb.fxgl.audio.Sound;
import com.almasb.fxgl.dsl.FXGL;
import java.util.prefs.Preferences;
import javafx.scene.paint.Color;

/**
 *
 * @author Anton Lisunov
 */
public class SavedSetting {

    private static final String KEY_SOUND_VOLUME = "svolume";
    private static final String KEY_MUSIC_VOLUME = "mvolume";
    private static final String KEY_TYPE = "music";
    private static final String KEY_COLOR = "color";
    private static final String KEY_BACKGROUND = "background";

    private double soundVolume;
    private double musicVolume;
    private String musicType;
    private Color color;
    private String background;

    private static Sound sound;
    private static Music music;

    public SavedSetting() {
        // Load settings from preferences
        loadSettings();

        sound = FXGL.getAssetLoader().loadSound("brick_hit.wav");
        music = FXGL.getAssetLoader().loadMusic(musicType);
        music.getAudio$fxgl_core().setLooping(true);

    }

    private void loadSettings() {
        Preferences preferences = Preferences.userNodeForPackage(SavedSetting.class);

        // Load volume setting
        // Default is 0.1 if not found
        this.soundVolume = preferences.getDouble(KEY_SOUND_VOLUME, 0.2);
        this.musicVolume = preferences.getDouble(KEY_MUSIC_VOLUME, 0.2);
        this.musicType = preferences.get(KEY_TYPE, "music1.mp3");
        this.color = stringToColor(preferences.get(KEY_COLOR, "0 0 0"));
        this.background = preferences.get(KEY_BACKGROUND, "");
    }

    private void saveSettings() {
        Preferences preferences = Preferences.userNodeForPackage(SavedSetting.class);

        // Save setting
        preferences.putDouble(KEY_SOUND_VOLUME, soundVolume);
        preferences.putDouble(KEY_MUSIC_VOLUME, musicVolume);
        preferences.put(KEY_TYPE, musicType);
        preferences.put(KEY_COLOR, colorToString(color));
        preferences.put(KEY_BACKGROUND, background);

    }

    public static String colorToString(Color color) {
        int red = (int) Math.round(color.getRed() * 255);
        int green = (int) Math.round(color.getGreen() * 255);
        int blue = (int) Math.round(color.getBlue() * 255);

        return red + " " + green + " " + blue;
    }

    public static Color stringToColor(String colorString) {
        String[] components = colorString.split(" ");
        // Convert the components to integers
        int red = Integer.parseInt(components[0]);
        int green = Integer.parseInt(components[1]);
        int blue = Integer.parseInt(components[2]);

        return Color.rgb(red, green, blue);
    }

    public double getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(double volume) {
        this.soundVolume = volume;
        sound.getAudio$fxgl_core().setVolume(soundVolume);
        // Save settings to preferences
        saveSettings();
    }

    public double getMusicVolume() {
        return musicVolume;
    }

    public void setMusicVolume(double volume) {
        this.musicVolume = volume;
        music.getAudio$fxgl_core().setVolume(musicVolume);
        saveSettings();
    }
    
    public String getMusic() {
        return musicType;
    }

    public void setMusic(String musicType) {
        this.musicType = musicType;
        stopMusic();
        FXGL.getAssetLoader().clearCache();
        music = FXGL.getAssetLoader().loadMusic(musicType);
        playMusic();
        saveSettings();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        this.background = "";
        saveSettings();
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
        saveSettings();
    }

    public void playSound() {
        sound.getAudio$fxgl_core().play();
    }

    public void playMusic() {
        music.getAudio$fxgl_core().play();
    }

    public void stopMusic() {
        music.getAudio$fxgl_core().stop();
    }
}
