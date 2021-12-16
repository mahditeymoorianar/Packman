import Menus.MainMenu;
import Menus.MyMenu;
import Menus.MyUser;
import Menus.StartMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends MainMenu {
    public static void main(String[] args) {
        try {
            initializeData();
        } catch (Exception e) {
        }
    }

    private static void initializeData() {
        MyUser.loadUsers();
        String username = getStayInLoggedInUser();
        if (username == null || username.equals("null")) {
            StartMenu.run();
        }
        else {
            user = MyUser.getUserByUsername(username);
            MainMenu.directRun();
        }
    }

    private static String getStayInLoggedInUser() {
        try {
            File file = new File("src/main/resources/data/StayInLoggedInUser.mta");
            Scanner scanner = new Scanner(file);
            return scanner.nextLine();
        } catch (Exception e) {
            File file = new File("./src");
            File[] files = file.listFiles();
            for (File aFile :
                    files) {
            }
        }
        return null;
    }
}
