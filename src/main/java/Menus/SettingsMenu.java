package Menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SettingsMenu extends MyMenu {
    public static void run() {
        try {
            new SettingsMenu().start(stage);
        } catch (Exception e) {
        }
    }
    //CONTROLLER
    private void cancel() {
        try {
            MainMenu.run();
        } catch (Exception e) {
        }
    }
    private void changePassword() {
        ChangePasswordMenu.run();
    }
    private void deleteAccount() {
        DeleteAccountMenu.run();
    }
    //VIEW
    Scene scene;
    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | تنظیمات");
        stage = myStage;
        stage.show();


    }

    public void changePasswordByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            changePassword();
        }
    }

    public void changePasswordByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            changePassword();
        }
    }

    public void doNothing() {
    }

    public void cancelByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            cancel();
        }
    }

    public void cancelByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {{
            cancel();
        }}
    }

    public void deleteAccountByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            deleteAccount();
        }
    }

    public void deleteAccountByKey(KeyEvent keyEvent)
    {
        if (isChoiceKey(keyEvent)) {
            deleteAccount();
        }
    }

    public void firstHealthPointByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            FirstHealthPointMenu.run();
        }
    }

    public void firstHealthPointByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            FirstHealthPointMenu.run();
        }
    }

    public void PersonalizeByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            PersonalizingMaps.run();
        }
    }

    public void PersonalizeByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            PersonalizingMaps.run();
        }
    }
}
