package Menus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;

public class LoginMenu extends MyMenu{
    public static void run(){
        launch();
    }


    //controller

    String username, password;
    boolean stayIn;

    public void cancel() throws Exception {
        StartMenu startMenu = new StartMenu();
        startMenu.start(stage);
    }

    public void login() throws Exception {
        username = getUsername();
        password = getPassword();
        stayIn = getStayIn();
        boolean doesMatch = MyUser.checkPassword(username , password);
        if (doesMatch) {
            message("درست بود" , messageGridPane);
            user = MyUser.getUserByUsername(username);
            user.getUsername();
            if (stayIn) {
                setLoggedInUser();
            }
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        } else {
            message("نام کاربری و گذرواژه با هم نمی‌خوانند!" , messageGridPane);
        }
    }

    public static void login(String username, String password, Boolean stayIn) throws Exception {
        boolean doesMatch = MyUser.checkPassword(username , password);
        if (doesMatch) {
            user = MyUser.getUserByUsername(username);
            user.getUsername();
            if (stayIn) {
                setLoggedInUser();
            }
            MainMenu mainMenu = new MainMenu();
            mainMenu.start(stage);
        } else {

        }
    }

    private static void setLoggedInUser() {
        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/data/StayInLoggedInUser.mta");
        } catch (IOException e) {
        }
        try {
            writer.write(user.getUsername());
        } catch (IOException e) {
        }
        try {
            writer.close();
        } catch (IOException e) {
        }
    }

    //VIEW
    Scene scene;
    public GridPane usernameGridPane, passwordGridPane, stayInGridPane, messageGridPane;

    public String getUsername() {
        Object[] children = usernameGridPane.getChildren().toArray();
        return ((TextField) children[0]).getText();
    }
    public String getPassword() {
        Object[] children = passwordGridPane.getChildren().toArray();

        return ((PasswordField) children[0]).getText();
    }
    public boolean getStayIn() {
        Object[] children = stayInGridPane.getChildren().toArray();
        return ((CheckBox) children[0]).isSelected();
    }

    @Override
    public void start(Stage myStage) throws Exception {
        BorderPane pane = FXMLLoader.load(getClass().getResource("LoginMenu.fxml"));
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("پکمن | ورود");
        stage.show();

    }

    public void cancelByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            cancel();
        } else if (isSecondary(mouseEvent)) {
        }
    }

    public void cancelByKey(KeyEvent keyEvent) throws Exception {
        String keyName = keyEvent.getCode().toString();
        if (keyName.equals("ENTER") || keyName.equals("SPACE")) {
            cancel();
        }
    }

    public void loginByMouse(MouseEvent mouseEvent) throws Exception {
        if (isPrimary(mouseEvent)) {
            login();
        } else if (isSecondary(mouseEvent)) {=
        }
    }

    public void loginByKey(KeyEvent keyEvent) throws Exception {
        if (isChoiceKey(keyEvent)) {
            login();
        }
    }
}
