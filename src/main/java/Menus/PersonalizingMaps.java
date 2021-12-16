package Menus;

import GameObjects.GameMaps;
import GameObjects.GameObject;
import GameObjects.Packman;
import GameObjects.Rock;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Handler;

public class PersonalizingMaps extends MyMenu {
    public static void run() {
        try {
            PersonalizingMaps personalizingMaps = new PersonalizingMaps();
            Random random = new Random();
            int columns = random.nextInt(6)+4;
            int raws = random.nextInt(6) + 4;
            personalizingMaps.map = GameMaps.newMap(raws , columns);
            personalizingMaps.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Controller
    String[] map;
    //VIEW
    Scene scene;
    Pane pane;
    Label helperLabel;
    @Override
    public void start(Stage myStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("PersonalizingMaps.fxml"));
        helperLabel = new Label("با دکمه‌ی ESC بیرون بروید و با دکمه‌ی SPACE نقشه‌ی دیگری ساخته‌شود و با ENTER برگزینید و بینبارید");
        helperLabel.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                handler(event);
            }
        });
        pane.getChildren().add(helperLabel);
        showMap(map);
        pane.setPrefWidth((2*10+4)*Packman.getSize());
        pane.setPrefHeight((2*10+4)*Packman.getSize());
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | شخصی سازی نقشه‌ها");
        stage = myStage;
        pane.getChildren().get(0).requestFocus();
        stage.show();


    }

    public void showMap(String[] map){
        for (String str :
                map) {
            System.out.println(str);
        }
        int rows = map.length;
        int columns = len(map[0]);
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        for (int i = 0; i < rows; i++) {
            char[] str = map[i].toCharArray();
            for (int j = 0; j < columns; j++) {
                try {
                    if (str[j] == 'R') {
                        Rock rock = new Rock();
                        rock.setXY(i + 2, j);
                        pane.getChildren().add(rock);
                    } else {
                        GameObject gameObject = new GameObject();
                        gameObject.initializeImage("white");
                        gameObject.setXY(i + 2, j);
                        pane.getChildren().add(gameObject);
                    }
                } catch (Exception e) {
                }
            }
        }
    }
    public void clearMap() {
        int rows = map.length;
        int columns = map[0].length();
        for (int i = 0; i < rows; i++) {
            char[] str = map[i].toCharArray();
            for (int j = 0; j < columns; j++) {

                GameObject gameObject = new GameObject();
                gameObject.initializeImage("white");
                gameObject.setXY(i+2 , j);
                pane.getChildren().add( gameObject);

            }
        }
    }
    public static int len(String str) {
        char[] chars = str.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'R') {
                ans++;
            }
        }
        return ans;
    }

    public void handler(KeyEvent keyEvent) {
        String keyName = keyEvent.getCode().toString();
        if (keyName.equals("ESCAPE")) {
            SettingsMenu.run();
        } else if (keyName.equals("SPACE")) {
            makeAndShowANewMap();
        } else if (keyName.equals("ENTER")) {
            selectAndSaveTheMap();
        }
    }

    private void selectAndSaveTheMap() {
        try {
            FileWriter writer = new FileWriter("src/main/resources/data/Maps/"+user.getUsername()+"/"+getALongRandomString()+".mtamap");
            writer.write(getTextOfMapToSave());
            writer.close();
        } catch (IOException e) {
            File file = new File("src/main/resources/data/Maps/"+user.getUsername()+"/");
            try {
                file.mkdir();
                FileWriter writer = new FileWriter("src/main/resources/data/Maps/"+user.getUsername()+"/"+getALongRandomString()+".mtamap");
                writer.write(getTextOfMapToSave());
                writer.close();
            } catch (Exception e1) {
            }
        }

    }
    private String getTextOfMapToSave() {
        String ans = "";
        for (String str :
                map) {
            ans += str+"\n";
        }
        return ans;
    }

    private void makeAndShowANewMap() {
        Random random = new Random();
        int columns = random.nextInt(6)+6;
        int raws = random.nextInt(6) + 6;
        clearMap();
        map = GameMaps.newMap(raws , columns);
        showMap(map);
    }

    private String getALongRandomString() {
        Random random = new Random();
        return random.nextLong()+""+ random.nextLong()+""+ random.nextLong();
    }
}
