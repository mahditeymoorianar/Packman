package Menus;

import GameObjects.Packman;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class FirstHealthPointMenu extends MyMenu {
    public static void run() {
        try {
            new FirstHealthPointMenu().start(new Stage());
        } catch (Exception e) {
        }
    }
    //CONTROLLER
    private void increase() {
        if (Packman.firstHealthPoint >= 5) {
            message("شمار جان‌های آاغازین را نمی‌توانید بیشتر از ۵ تا تنظیم‌کنید" ,messageGridPane);
            Packman.firstHealthPoint = 5;
        } else {
            Packman.firstHealthPoint++;
            user.firstHealthPoint++;
            user.saveUser();
            message(Packman.firstHealthPoint+"" , numberGridPane);
        }
    }
    private void decrease() {
        if (Packman.firstHealthPoint <= 2) {
            message("شمار جان‌های آغازین نمی‌تواد کمتر از ۲ باشد" , messageGridPane);
            Packman.firstHealthPoint = 2;
        } else {
            Packman.firstHealthPoint--;
            user.firstHealthPoint--;
            user.saveUser();
            message(Packman.firstHealthPoint+"" , numberGridPane);
        }
    }
    //VIEW
    Scene scene;
    Label numberLabel, messageLabel;
    BorderPane pane;
    public GridPane numberGridPane;
    public GridPane messageGridPane;
    @Override
    public void start(Stage myStage) throws Exception {
        pane = FXMLLoader.load(getClass().getResource("FirstHealthPointMenu.fxml"));
        initGridPanes();
        System.out.println("numberGridPane : "+numberGridPane+"");
        Object[] children = numberGridPane.getChildren().toArray();
        numberLabel = (Label) children[0];
        numberLabel.setFont(Font.font(30));
        numberLabel.setText(Packman.firstHealthPoint+"");
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.initOwner(stage);
        myStage.setTitle("پکمن | شمار جان‌های آغازین");
        myStage.getIcons().add(
                new Image(
                        Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));

        myStage.show();
    }

    private void initGridPanes() {
        VBox vBox = (VBox) pane.getChildren().get(0);
        numberGridPane = (GridPane) (vBox.getChildren().get(2));
        messageGridPane = (GridPane) (vBox.getChildren().get(5));
    }

    public void increaseByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            increase();
        }
    }

    public void increaseByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            increase();
        }
    }

    public void decreaseByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            decrease();
        }
    }

    public void decreaseByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            decrease();
        }
    }
}
