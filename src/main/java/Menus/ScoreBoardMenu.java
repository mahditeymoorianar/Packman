package Menus;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ScoreBoardMenu extends MyMenu {
    public static void run() throws Exception {
        new ScoreBoardMenu().start(stage);
    }
    //VIEW
    Scene scene;
    Pane pane;
    public void start(Stage myStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("ScoreBoardMenu.fxml"));
        createThePane();

        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | جدول امتیازها");
        stage = myStage;
        stage.show();


    }

    private void createThePane() {
        addTitle();
        addScores();
        addReturnButton();
    }

    private void addReturnButton() {
        Button button = new Button("برگشتن") ;
        button.setLayoutX(300);
        button.setLayoutY(14*20+200);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (isPrimary(event)) {
                    try {
                        returnToTheMainMenu();
                    } catch (Exception e) {
                    }
                }
            }
        });
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (isChoiceKey(event)) {
                    try {
                        returnToTheMainMenu();
                    } catch (Exception e) {
                    }
                }
            }
        });
        pane.getChildren().add(button);
    }

    private void returnToTheMainMenu() throws Exception {
        MainMenu.run();
    }

    private void addScores() {
        boolean hasShowedCurrentUser = false;
        ArrayList<MyUser> users = MyUser.getUsers();
        
        MyUser aUser;
        for (int i = 0; i < 10; i++) {
            if (i >= users.size()) {
                return;
            }
            aUser = users.get(i);
            if (aUser == user) {
                hasShowedCurrentUser = true;
                showUser(i , user , "#3f731a");
            } else {
                showUser(i , aUser , "#000000");
            }
        }
        if (!hasShowedCurrentUser) {
            showUser(11 , user , "#ba005d");
        }
    }

    private void showUser(int place, MyUser aUser, String color) {
        String string = aUser.getRank()+" . @" +aUser.getUsername();
        String score = aUser.getHighestScore()+"";
        Label userLabel = new Label( string+getSpaces(string+score)+score);
        userLabel.setFont(Font.font(18));
        userLabel.setTextFill(Color.web(color));
        userLabel.setLayoutX(20);
        userLabel.setLayoutY(100+ place*20);
        pane.getChildren().add(userLabel);
    }

    private String getSpaces(String s) {
        String string = "";
        int len = 100 - s.length();
        for (int i = 0; i < len; i++) {
            string += " ";
        }
        return string;
    }

    private void addTitle() {
        Label titleLabel = new Label("*** جدول امتیاز کاربران ***");
        titleLabel.setLayoutX(200);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(24));
        pane.getChildren().add(titleLabel);
    }

}
