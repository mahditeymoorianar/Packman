package Menus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class DeleteAccountMenu extends MyMenu {
    public static void run() {
        try {
            new DeleteAccountMenu().start(new Stage());
        } catch (Exception e) {
        }
    }
    //CONTROLLER
    private void deleteAccount() {
        user.deleteAccount();
        try {
            StartMenu.run();
        } catch (Exception e) {
        }
    }
    //VIEW
    Scene scene;
    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("DeleteAccountMenu.fxml"));
        scene = new Scene(pane);
        myStage.setScene(scene);
        myStage.setTitle("پکمن | حذف حساب کاربری");
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.initOwner(stage);
        myStage.getIcons().add(
                new Image(
                        Objects.requireNonNull(getClass().getResource("/images/" + "packmanOpenRight.png")).toExternalForm()));

        myStage.show();
    }

    public void deleteAccountByMouse(MouseEvent mouseEvent) {
        if (isPrimary(mouseEvent)) {
            deleteAccount();
        }
    }

    public void deleteAccountByKey(KeyEvent keyEvent) {
        if (isChoiceKey(keyEvent)) {
            deleteAccount();
        }
    }
}
