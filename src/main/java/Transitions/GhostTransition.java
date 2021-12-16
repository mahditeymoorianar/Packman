package Transitions;

import GameObjects.Ghost;
import javafx.animation.Transition;
import javafx.util.Duration;

import java.util.Random;

public class GhostTransition extends Transition {
    Ghost ghost;
    int duration = 20;

    public GhostTransition(Ghost ghost) {
        this.ghost = ghost;
        this.setCycleDuration(Duration.millis(duration));
        this.setCycleCount(-1);
    }
    @Override
    protected void interpolate(double frac) {
        try {
            ghost.go();
            ghost.setPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
