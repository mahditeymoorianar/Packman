package Menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class HelpMenu extends MyMenu {
    public static void run() {
        try {
            new HelpMenu().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //VIEW
    Scene scene;
    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("HelpMenu.fxml"));
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | راهنمای بازی");
        stage = myStage;
        stage.show();

    }


    public void returnByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            back();
        }
    }

    public void returnByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            back();
        }
    }

    private void back() {
        try {
            StartMenu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToTheLink(MouseEvent mouseEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/mahditeymoorianar/Packman/").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {

        }
    }
}
