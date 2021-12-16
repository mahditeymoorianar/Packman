package Menus;
import Transitions.GhostTransition;
import Transitions.PackmanTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import GameObjects.*;
import javafx.scene.control.Label;


import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class GameMenu extends MyMenu{
    Game game;
    boolean isPaused = false;
    Packman packman;
    MediaPlayer mediaPlayer;
    Ghost ghost1, ghost2, ghost3, ghost4;
    GhostTransition ghost1Transition, ghost2Transition, ghost3Transition, ghost4Transition;
    String[] map;

    ArrayList<Rock> rocks = new ArrayList<Rock>();
    ArrayList<Food> foods = new ArrayList<Food>();
    ArrayList<Energy> energies = new ArrayList<Energy>();
    public MyMenu previousMenu;

    public void setMap(String[] map) {
        this.map = map;
    }
    public void setMap(int choice) {
        map = GameMaps.maps[choice%GameMaps.maps.length];
    }

    //VIEW
    Scene scene;
    Pane pane;
    Label isSecuredLabel;
    Label scoreLabel;
    Label healthPointLabel;
    public GameMenu() throws Exception {
        MoverGameObject.toPlay = true;
        packman = new Packman();
        game = new Game();
        ghost1 = game.ghost1;
        ghost2 = game.ghost2;
        ghost3 = game.ghost3;
        ghost4 = game.ghost4;
    }

    @Override
    public void start(Stage myStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("GameMenu.fxml"));
        pane.setStyle("-fx-background-color: #" + "4c3675");

        scoreLabel = new Label("امتیاز : " + packman.getScore());
        scoreLabel.setFont(Font.font(24));
        scoreLabel.setTextFill(Color.web("#ffffff"));
        isSecuredLabel = new Label("                    ");
        isSecuredLabel.setTextFill(Color.web("#ffffff"));
        isSecuredLabel.setFont(Font.font(24));

        packman.setIsSecuredLabel(isSecuredLabel);

        healthPointLabel = new Label("جان : " +  packman.getHealthPoint());
        healthPointLabel.setFont(Font.font(24));
        healthPointLabel.setTextFill(Color.web("#ffffff"));

        packman.setHealthPointLabel(healthPointLabel);
        pane.getChildren().add(packman);
        pane.getChildren().add(scoreLabel);
        pane.getChildren().add(isSecuredLabel);
        pane.getChildren().add(healthPointLabel);
        PackmanTransition packmanTransition = new PackmanTransition(packman);
        packmanTransition.setScoreLabel(scoreLabel);
        packmanTransition.play();
        pane.getChildren().get(0).requestFocus();
        packman.setGameMenu(this);
        pane.getChildren().get(0).setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().toString();
                MoverGameObject.toPlay = true;
                packmanTransition.play();

                if (keyName.equals("LEFT")) {
                    try {
                        packman.setDirection("left");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("RIGHT")) {
                    try {
                        packman.setDirection("right");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("UP")) {
                    try {
                        packman.setDirection("up");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("DOWN")) {
                    try {
                        packman.setDirection("down");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("ESCAPE")) {
                    try {
                        if (user != null) {
                            user.setHighestScore(packman.getScore());
                            user.saveUser();
                        }
                        mediaPlayer.stop();
                        packmanTransition.stop();
                        ghost1Transition.stop();
                        ghost2Transition.stop();
                        ghost3Transition.stop();
                        ghost4Transition.stop();
                        packman.init();
                        previousMenu.start(stage);
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("SPACE")) {
                    MoverGameObject.toPlay = !MoverGameObject.toPlay;
                } else if (keyName.equals("M")) {
                    mediaPlayer.setMute(!mediaPlayer.isMute());
                }
            }
        });

        pane.getChildren().add(ghost1);
        pane.getChildren().add(ghost2);
        pane.getChildren().add(ghost3);
        pane.getChildren().add(ghost4);

        ghost1.setPackman(packman);
        ghost2.setPackman(packman);
        ghost3.setPackman(packman);
        ghost4.setPackman(packman);

        ghost1Transition = new GhostTransition(ghost1);
        ghost2Transition = new GhostTransition(ghost2);
        ghost3Transition = new GhostTransition(ghost3);
        ghost4Transition = new GhostTransition(ghost4);




        setXYs();
        initRocks();
        initFoods();
        initEnergies();
        ghost1Transition.play();
        ghost2Transition.play();
        ghost3Transition.play();
        ghost4Transition.play();

        String path = "src/main/resources/sounds/Moon_Beach_Streets_of_Rage_1_Original_Soundtrack_OST_.hd_.mp3";

        Media media = new Media(new File(path).toURI().toString());

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setAutoPlay(true);


        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | صفحه‌ی بازی");
        stage = myStage;

        packman.requestFocus();
        stage.show();
        Set<Thread> threads = Thread.getAllStackTraces().keySet();

        for (Thread t : threads) {
            String name = t.getName();
            Thread.State state = t.getState();
            int priority = t.getPriority();
            if (priority < 9) {
                t.setPriority(10);
            }
        }


    }

    private void initEnergies() {
        packman.setEnergies(energies);
    }

    public void setXYs() {
        int rows = map.length;
        int columns = SelectingMap.len(map[0]);
        pane.setMinWidth((columns) * Packman.getSize());
        pane.setPrefWidth((columns) * Packman.getSize());
        pane.setMinHeight((rows + 2) * Packman.getSize());
        pane.setPrefHeight((rows + 2) * Packman.getSize());
        GameObject.setWindowHeight((rows + 400) * Packman.getSize());
        GameObject.setWindowHeight((columns + 4) * Packman.getSize());

        isSecuredLabel.setLayoutX((columns-15) * Packman.getSize() / 2);
        isSecuredLabel.setLayoutY((rows+1)*Packman.getSize());
        scoreLabel.setLayoutX(0);
        healthPointLabel.setLayoutX(columns*Packman.getSize() - 80 );

        for (int i = 0; i < rows; i++) {
            char[] str = map[i].toCharArray();
            for (int j = 0; j < columns; j++) {
                if (str[j] == '0') {
                    packman.setXY(i+1 , j);
                }
                else if (str[j] == '1') {
                    ghost1.setXY(i+1 , j);
                }
                else if (str[j] == '2') {
                    ghost2.setXY(i+1 , j);
                }
                else if (str[j] == '3') {
                    ghost3.setXY(i+1 , j);
                }
                else if (str[j] == '4') {
                    ghost4.setXY(i+1 , j);
                }
                else if (str[j] == 'R') {
                    Rock rock = new Rock();
                    rock.setXY(i+1 , j);
                    rocks.add(rock);
                    pane.getChildren().add(rock);
                } else  if (str[j] == 'E') {
                    Energy energy = new Energy(packman);
                    energy.setXY(i+1 , j);
                    energies.add(energy);
                    pane.getChildren().add(energy);

                }
                if (str[j] != 'R' && str[j] != 'E' ) {
                    Food food = new Food();
                    food.setXY(i+1 , j);
                    foods.add(food);
                    pane.getChildren().add(food);
                }
            }
        }
    }

    private void initRocks() {
        packman.setRocks(rocks);
        ghost1.setRocks(rocks);
        ghost2.setRocks(rocks);
        ghost3.setRocks(rocks);
        ghost4.setRocks(rocks);
    }
    private void initFoods() {
        packman.setFoods(foods);
    }


    @FXML
    public void initialize() throws Exception {
        packman.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String keyName = event.getCode().toString();
                if (keyName.equals("LEFT")) {
                    try {
                        packman.setDirection("left");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("RIGHT")) {
                    try {
                        packman.setDirection("right");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("UP")) {
                    try {
                        packman.setDirection("up");
                    } catch (Exception e) {
                    }
                } else if (keyName.equals("DOWN")) {
                    try {
                        packman.setDirection("down");
                    } catch (Exception e) {
                    }
                }
            }
        });
    }




    private boolean checkIfHasCollectedAllFoodsAndEnergies() {
        for (Food food:
             foods) {
            if (!food.isCollected) return false;
        }
        for (Energy energy :
                energies) {
            if (!energy.isCollected) return false;
        }
        return true;
    }

    public void hasCollectedAllFoodsAndEnergies() {
        boolean hasCollectedAll = checkIfHasCollectedAllFoodsAndEnergies();
        if (hasCollectedAll) {
            packman.increaseHealthPoint();
            MoverGameObject.toPlay = false;
            int rows = map.length;
            int columns = map[0].length();
            for (int i = 0; i < rows; i++) {
                char[] str = map[i].toCharArray();
                for (int j = 0; j < columns; j++) {
                     if (str[j] == 'E') {
                        Energy energy = new Energy(packman);
                        energy.setXY(i+1 , j);
                        energies.add(energy);
                        pane.getChildren().add(energy);

                    }
                    if (str[j] != 'R' && str[j] != 'E' ) {
                        Food food = new Food();
                        food.setXY(i+1 , j);
                        foods.add(food);
                        pane.getChildren().add(food);
                    }
                }
            }
            initEnergies();
            initFoods();
        }
    }
}
