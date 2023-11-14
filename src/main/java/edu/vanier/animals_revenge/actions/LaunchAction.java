package edu.vanier.animals_revenge.actions;

import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.input.UserAction;
import edu.vanier.animals_revenge.MainApp;

import static com.almasb.fxgl.dsl.FXGL.*;

public class LaunchAction extends UserAction {

    boolean isLaunching = false;

    public LaunchAction() {
        super("Launch");
    }

    @Override
    protected void onActionBegin() {
        double x = getInput().getMouseXWorld();
        double y = getInput().getMouseYWorld();

        if (x >= 0 && x <= 80 && y >= MainApp.HEIGHT - 80 && y <= MainApp.HEIGHT) {
            isLaunching = true;
        }
    }

    @Override
    protected void onAction() {
        if (isLaunching) {
            // TODO Draw arrow
        }
    }

    @Override
    protected void onActionEnd() {
        if (isLaunching) {
            double vX = getInput().getMouseXWorld();
            double vY = MainApp.HEIGHT - getInput().getMouseYWorld();
            spawn("projectile", new SpawnData(0, MainApp.HEIGHT).put("vX", vX).put("vY", vY));
            isLaunching = false;
        }
    }
}
