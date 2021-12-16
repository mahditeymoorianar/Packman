package Transitions;

import GameObjects.Packman;
import Menus.MyMenu;
import javafx.animation.Transition;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.Objects;

public class PackmanTransition extends Transition {
    Packman packman;
    Label scoreLabel;
    int counter = 0;



    public PackmanTransition(Packman packman) {
        this.packman = packman;
        this.setCycleDuration(Duration.millis(20));
        this.setCycleCount(-1);
    }


    @Override
    protected void interpolate(double frac) {
        try {
            if (packman.checkIfCollectAFood()){
                packman.increaseScore(10);
            }
            packman.checkIfGottenEnergy();
            scoreLabel.setText("امتیاز : " + packman.getScore());
            packman.go();
            packman.setPosition();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packman.getHealthPoint()==0) {

        }
        counter++;
        if (counter == 1000000) counter = 0;
        if (counter % 50 == 25) {
            MyMenu.stage.getIcons().add(
                    new Image(
                            Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));
            MyMenu.stage.getIcons().remove(0);
        } else if (counter % 50 == 0) {
            MyMenu.stage.getIcons().add(
                    new Image(
                            Objects.requireNonNull(getClass().getResource("/images/" + "packmanCloseRight.png")).toExternalForm()));
            MyMenu.stage.getIcons().remove(0);
            String foods = "";
            if (counter % 200 == 0) {
                foods = "\uD83D\uDD39\uD83D\uDD39\uD83D\uDD39";
            } else if (counter % 200 == 50) {
                foods = "\uD83D\uDD39\uD83D\uDD39";
            } else if (counter % 200 == 100) {
                foods = "\uD83D\uDD39";
            } else if (counter % 200 == 150) {
                foods = "";
            }
            MyMenu.stage.setTitle(foods + "صفحه‌ی بازی ");
        }
    }

    public void setScoreLabel(Label scoreLabel) {

        this.scoreLabel = scoreLabel;
    }
}
