package Menus;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class ChangePasswordMenu extends MyMenu {
    public static void run(){
        try {
            new ChangePasswordMenu().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //CONTROLLER
    private void request() {
        String password = getPassword(), passwordRep = getPasswordRep();
        if (!password.equals(passwordRep)) {
            message("گذرواژه و تکرارش باید یکسان باشند" , messageGridPane);
        } else {
            user.changePassword(password);
            message("پیروزمندانه گذرواژه ویراسته شد" , messageGridPane);
        }
    }
    //VIEW
    Scene scene;
    @FXML
    GridPane passwordGridPane, passwordRepGridPane, messageGridPane;
    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("ChangePasswordMenu.fxml"));
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | ویراستن گذرواژه");
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.initOwner(stage);
        myStage.getIcons().add(
                new Image(
                        Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));

        myStage.show();


    }
    private String getPassword() {
        return ((PasswordField) passwordGridPane.getChildren().get(0)).getText();
    }
    private String getPasswordRep() {
        return ((PasswordField) passwordRepGridPane.getChildren().get(0)).getText();
    }

    public void requestByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            request();
        }
    }

    public void requestByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            request();
        }
    }
}
