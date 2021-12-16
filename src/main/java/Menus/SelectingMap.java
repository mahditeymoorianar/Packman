package Menus;

import GameObjects.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SelectingMap extends MainMenu{
    public MyMenu previousMenu;
    //VIEW
    Pane pane;
    Scene scene;
    int numberOfMap = 0;
    String[][] maps;
    @Override
    public void start(Stage myStage) throws Exception {

        maps = GameMaps.getMaps(user);
        pane = FXMLLoader.load(getClass().getResource("SelectingMap.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        Label label = new Label("با دکمه‌های چپ و راست میان نقشه‌ها جا به جا شوید و با اینتر برگزینید");
        Label number = new Label(""+numberOfMap);
        VBox vBox = new VBox();
        HBox hBox = new HBox();


        hBox.getChildren().add(label);
        hBox.getChildren().add(number);
        pane.getChildren().add(hBox);

        showMap(0);
        label.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                clearMap(numberOfMap);
                String keyName = event.getCode().toString();
                if (keyName.equals("LEFT") && numberOfMap>0) {
                    numberOfMap --;
                }
                else if (keyName.equals("RIGHT")) {
                    numberOfMap ++;
                } else if ((keyName.equals("ENTER"))) {
                    try {
                        GameMenu gameMenu = new GameMenu();
                        gameMenu.setMap(maps[numberOfMap]);
                        gameMenu.previousMenu = previousMenu;
                        gameMenu.start(stage);
                    } catch (Exception e) {
                    }
                }
                number.setText(""+numberOfMap%maps.length);
                showMap(numberOfMap);
            }
        });


        hBox.getChildren().get(0).requestFocus();
        stage.setTitle("پکمن | صفحه‌ی گزیدن نقشه‌ی بازی");

        stage.show();

    }

    public void clearMap(int id) {
        String[] map = maps[id%maps.length];
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

    public void showMap(int id){
        String[] map = maps[id%maps.length];
        int rows = map.length;
        int columns = len(map[0]);
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        if (map[0].toCharArray()[columns-1] == '\n') columns--;
        pane.setMinHeight((rows+2) * Packman.getSize());
        pane.setPrefWidth((columns+2) * Packman.getSize());
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
}
