package GameObjects;

import Menus.GameMenu;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Packman extends MoverGameObject {
    private static int lastId = 0;
    public static int firstHealthPoint = 3;
    private int id = 0;
    Label isSecuredLabel;
    GameMenu gameMenu;
    int healthPoint = firstHealthPoint;
    boolean openPhoto = true;
    int counter = 0;
    ArrayList<Food> foods;
    ArrayList<Energy> energies;
    int score;
    public boolean isHurt = false;
    Label healthPointLabel;
    public boolean isSecured = false;
    public int security = 0;
    public int highestSecurity = 2200;
    private int numberOfHittingGhosts = 0;
    private static Packman instance;
    private int slowly = 1;
    private int slowlyCounter = 0;

    public static Packman getInstance(){
        if (instance == null) {
            instance = new Packman();
        }
        return instance;
    }

    public void init() {
        healthPoint = firstHealthPoint;
        isSecuredLabel = null;
        gameMenu = null;
        openPhoto = true;
        counter = 0;
        foods = null;
        energies = null;
        score = 0;
        isHurt = false;
        isSecured = false;
        security = 0;
        numberOfHittingGhosts = 0;
        x = 0;
        y = 0;
        speed = 1;
        defaultSensitivity = 6;
        setLayoutX(0);
        setLayoutY(0);
        direction = "right";
        healthPoint = 3;


    }

    public Packman() {
        lastId ++;
        id = lastId;
        super.name = "packman";
        defaultSensitivity = 6;
        this.speed = 1;
        score = 0;
        initializeImage();
        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().toString();
                if (keyName.equals("LEFT")) {
                    try {
                        setDirection("left");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("RIGHT")) {
                    try {
                        setDirection("right");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("UP")) {
                    try {
                        setDirection("up");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("DOWN")) {
                    try {
                        setDirection("down");
                    } catch (Exception e) {
                    }
                }
            }
        });
    }



    public void initializeImage() {
        initializeImage("packman" + openOrClose() + directionName());

    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    private boolean checkIfCollectAFood(Food food) {
        return  (food.doesCollect(this));
    }

    public boolean checkIfCollectAFood() {
        for (Food food :
                foods) {
            if (checkIfCollectAFood(food)) {
                return true;
            }
        }
        return false;
    }

    public void increaseScore(int toIncrease) {
        score += toIncrease;
    }

    public int getScore() {
        return score;
    }
    public String openOrClose() {
        if (openPhoto) {
            return "Open";
        }
        return "Close";
    }
    private String directionName() {
        if (direction.equals("up")) {
            return "Up";
        }
        if (direction.equals("down")) {
            return "Down";
        }
        if (direction.equals("left")) {
            return "Left";
        }
        if (direction.equals("right")) {
            return "Right";
        }
        return null;
    }
    public void go() throws Exception {
        gameMenu.hasCollectedAllFoodsAndEnergies();
        if (toPlay) {
            if (isSecured) {
                if (security % 110 == 0) {
                    isSecuredLabel.setText("        شما تا " + security / 110 + " در پناه پروردگار ایمن از گزند ارواح خبیسه هستید.");
                }
                if (security < 1) {
                    isSecuredLabel.setText("                                                       ");
                    isSecured = false;
                }

                security--;
            }
            if (healthPoint > 0) {
                initializeImage();
                counter++;
                if (counter > 1000000) {
                    counter = 0;
                }
                if (counter % 20 == 0) {
                    openPhoto = !openPhoto;
                }
                if (slowlyCounter % slowly == 0)
                    move();
                slowlyCounter ++;
                if (slowlyCounter > 1000000)
                    slowlyCounter = 0;
            } else {
                initializeImage("packmanDead");
            }
        }
    }

    public void decreaseHealthPoint() {
        if (healthPoint > 0) {

            healthPoint--;
            healthPointLabel.setText("جان : '" + healthPoint
            +"'");
            initializeImage("packmanDead");
        }
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPointLabel(Label healthPointLabel) {
        this.healthPointLabel = healthPointLabel;
    }

    public void hitAGhost() {
        numberOfHittingGhosts++;
        this.score += 200 * numberOfHittingGhosts;
    }

    public void setSecured() {
        if (!isSecured) {
            security = highestSecurity;
            isSecured = true;
        } else {
            security = highestSecurity;
        }
    }

    public void setEnergies(ArrayList<Energy> energies) {
        this.energies = energies;
    }

    public void setIsSecuredLabel(Label isSecuredLabel) {
        this.isSecuredLabel = isSecuredLabel;
    }

    public void checkIfGottenEnergy() {
        for (Energy energy : energies) {
            if (energy.doesCollect(this)) {
                energy.action();
            }
        }
    }

    public void setGameMenu(GameMenu gameMenu) {
        this.gameMenu = gameMenu;
    }
    public void increaseHealthPoint() {
        healthPoint++;
        healthPointLabel.setText("جان : " + healthPoint);
    }
}
