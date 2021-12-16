package Menus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.*;

import java.io.IOException;

public class SignUpMenu extends MyMenu{
    Scene scene;
    static int MINIMUMPASSWORDLENGTH = 6;
    static int MINIMUMUSERNAMELENGTH = 6;
    public GridPane usernameGridPane;
    public GridPane passwordGridPane;
    public GridPane passwordReputationGridPane;
    public GridPane messageGridPane;
    public CheckBox loginAfterIt;

    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("SignUpMenu.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("پکمن | عضویت");
        stage.show();

    }

    public static boolean checkPowerOfPassword(String password) {
        if (password.length()<MINIMUMPASSWORDLENGTH) {
            return false;
        }
        return true;
    }

    public static boolean checkUsernameValid(String username) {
        if (username.length()<MINIMUMUSERNAMELENGTH) {
            return false;
        }
        return true;
    }

    public void signUp() throws IOException {
        Object[] usernameChildren = usernameGridPane.getChildren().toArray();
        TextField usernameField = (TextField) usernameChildren[0];
        String usernameString = usernameField.getText();

        Object[] passwordChildren = passwordGridPane.getChildren().toArray();
        PasswordField passwordField = (PasswordField) passwordChildren[0];
        String passwordString = passwordField.getText();

        Object[] passwordRepChildren = passwordReputationGridPane.getChildren().toArray();
        PasswordField passwordRepField = (PasswordField) passwordRepChildren[0];
        String passwordRepString = passwordRepField.getText();

        if (MyUser.doesUsernameExist(usernameString)) {
            message("چنین نام کاربری‌ای هست" , messageGridPane);
        }
        else if (!passwordRepString.equals(passwordString)){
            message("گذرواژه و تکرارش باید یکی باشند." , messageGridPane);
        } else if (!checkUsernameValid(usernameString)) {
            message("نام کاربری نامعتبر است! زیرا باید دست کم ۶ کارکتر بدارد" , messageGridPane);
        } else if (!checkPowerOfPassword(passwordString)) {
            message("گذرواژه باید دست کم "+MINIMUMPASSWORDLENGTH+" کارکتر بدارد" , messageGridPane);
        } else {
            new MyUser(usernameString, passwordString).saveUser();
            message("تبریک! شما پیروزمندانه عضو شدید" , messageGridPane);

        }

        if (loginAfterIt.isSelected()) {
            for (int i = 0; i < 1000000; i++) {
                for (int j = 0; j < 10; j++) {

                }
            }
            try {
                LoginMenu.login(usernameString, passwordString, true);
            } catch (Exception e) {

            }
        }
    }

    public void cancel() throws Exception {
        StartMenu startMenu = new StartMenu();
        startMenu.start(stage);
    }

    public void signUpByMouse(MouseEvent mouseEvent) throws IOException {
        if (isPrimary(mouseEvent)) {
            signUp();
        }
        else if (isSecondary(mouseEvent)) {
            message("برای بررسی امکان ایجاد حساب و ایجاد آن در صورت امکان این گزینه را بزنید!");
        }
    }
    public void signUpByKey(KeyEvent keyEvent) throws IOException {
        if (isChoiceKey(keyEvent)) {
            signUp();
        }
    }

    public void cancelByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            cancel();
        } else if (isSecondary(mouseEvent)) {
            message("برای برگشتن به صفحه‌ی اصلی این گزینه را بزنید");
        }
    }
    public void cancelByKey(KeyEvent keyEvent) throws Exception {
        if (isChoiceKey(keyEvent)) {
            cancel();
        }
    }
    public void message(String message){
        Object[] children = messageGridPane.getChildren().toArray();
        Label label = (Label) children[0];
        label.setText(message);
    }
}
