package Menus;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;


public class StartMenu extends MyMenu {
    Scene scene;
    public static void run() {
        try {
            launch();
        } catch (Exception e) {
            try {
                new StartMenu().start(stage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("StartMenu.fxml"));
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن");
        stage = myStage;
        MyMenu.stage.getIcons().clear();
        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));

        stage.show();


    }


    public static void signUp() throws Exception {
        SignUpMenu signUpMenu = new SignUpMenu();
        signUpMenu.start(stage);
    }
    public static void signIn() throws Exception{
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.start(stage);
    }

    public void singUp(MouseEvent mouseEvent) throws Exception {
        String mouseButton = mouseEvent.getButton()+"";
        System.out.println("mouseEvent : "+mouseEvent.toString());
        if (mouseButton.equals("PRIMARY")) {
            signUp();
        }
    }

    public void signUpByEnter(KeyEvent keyEvent) throws Exception {
        String keyName = ""+keyEvent.getCode();
        if (keyName.equals("ENTER")||keyName.equals("SPACE")){
            signUp();
        }
    }

    public void signIn(MouseEvent mouseEvent) throws Exception {
        String mouseButton = mouseEvent.getButton()+"";
        if (mouseButton.equals("PRIMARY")) {
            signIn();
        }
        if (mouseButton.equals("SECONDARY")) {
        }
    }

    public void signInByEnter(KeyEvent keyEvent) throws Exception {
        String keyName = ""+keyEvent.getCode();
        if (keyName.equals("ENTER")||keyName.equals("SPACE")) {
            signIn();
        }
    }

    public void cursorToHand(MouseEvent mouseEvent) {
        try {
            scene.getCursor();
            System.out.println("OK");
        } catch (Exception e) {
        }
    }

    public void fastGame(MouseEvent mouseEvent) throws Exception{
        if (isPrimary(mouseEvent)) {
            SelectingMap selectingMap = new SelectingMap();
            selectingMap.previousMenu = this;
            selectingMap.start(stage);
        } else if (isSecondary(mouseEvent)) {

        }
    }

    public void openHelpMenu(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            HelpMenu.run();
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
