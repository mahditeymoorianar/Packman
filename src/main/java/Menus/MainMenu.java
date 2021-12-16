package Menus;
import GameObjects.Packman;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.util.Objects;

public class MainMenu extends MyMenu {
    public static void run() throws Exception {
        Packman.firstHealthPoint = user.firstHealthPoint;
        new MainMenu().start(stage);
    }
    public static void directRun() {
        Packman.firstHealthPoint = user.firstHealthPoint;
        launch();
    }


    //VIEW
    Scene scene;
    Label usernameLabel = new Label(), highestScoreLabel = new Label();
    @Override
    public void start(Stage myStage) throws Exception {
        Pane pane = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("پکمن | صفحه‌ی شخصی کاربر");


        usernameLabel.setText("نام کاربری : " + user.getUsername() + "@");
        highestScoreLabel.setText("بیشینه امتیاز شما : " + user.getHighestScore());
        highestScoreLabel.setLayoutX(0);
        highestScoreLabel.setLayoutY(0);
        pane.getChildren().add(highestScoreLabel);
        usernameLabel.setLayoutX(pane.getPrefWidth() - usernameLabel.getText().length()*10);
        usernameLabel.setLayoutY(0);
        pane.getChildren().add(usernameLabel);

        usernameLabel.setFont(Font.font(20));
        highestScoreLabel.setFont(Font.font(20));
        MyMenu.stage.getIcons().clear();
        stage.getIcons().add(
                new Image(
                        Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));

        stage.show();

    }

    private String getText() {
        String text = "";
        for (int i = 0; i < 50 - user.getUsername().length() - (""+user.getHighestScore()).length() ; i++) {
            text += " ";
        }
        return text;
    }

    public void newGameByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            newGame();
        }
    }

    public void newGameByKey(KeyEvent keyEvent) throws Exception {
        if (isChoiceKey(keyEvent)) {
            newGame();
        }
    }

    private void newGame() throws Exception {
        SelectingMap selectingMap = new SelectingMap();
        selectingMap.previousMenu = this;
        selectingMap.start(stage);
    }

    private void quitAccount() throws Exception {
        user = null;
        FileWriter fileWriter = new FileWriter("src/main/resources/data/StayInLoggedInUser.mta");
        fileWriter.write("null");
        fileWriter.close();
        StartMenu.run();
    }

    public void quitAccountByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            quitAccount();
        }
        else if (isSecondary(mouseEvent)) {

        }
    }

    public void quitAccountByKey(KeyEvent keyEvent) throws Exception {
        if (isChoiceKey(keyEvent)) {
            quitAccount();
        }
    }

    public void seeScoresByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            seeScores();
        }
    }

    public void seeScoresByKey(KeyEvent keyEvent) throws Exception {
        if (isChoiceKey(keyEvent)) {
            seeScores();
        }
    }

    private void seeScores() throws Exception {
        ScoreBoardMenu.run();
    }

    public void settingsMenuByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            SettingsMenu.run();
        }
    }

    public void settingsMenuByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            SettingsMenu.run();
        }
    }
}
