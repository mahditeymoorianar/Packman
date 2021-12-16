package Menus;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class MyUser {
    private String username, password;
    public int firstHealthPoint = 3;
    private static ArrayList<MyUser> users = new ArrayList<MyUser>();
    private int highestScore = 0;

    public MyUser(String username, String password) {
        this.username = username;
        this.password = password;

        if (doesUsernameExist(username)) {
            return;
        }
        users.add(this);
        saveUser();
        saveFileOfUsernames();
    }

    public static boolean doesUsernameExist(String username) {
        return (getUserByUsername(username) != null);
    }
    public static MyUser getUserByUsername(String username) {
        for (MyUser user :
                users) {
            if (user.username.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void loadUsers() {
        try {
            File file = new File("src/main/resources/data/Users/");
            File[] files = file.listFiles();
            for (File userFile :
                    files) {
                loadUser(userFile.getName());
            }
            sortArrayListFromText();
        } catch (Exception e) {
            createDirectory();
        }

    }

    private static void createDirectory() {
        File file = new File("src/main/resources/data/Users/");
        file.mkdir();
        file = new File("src/main/resources/data/Users/ArtificialIntelligence.mta");
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        file = new File("src/main/resources/data/Users/listOfUsernames.mta");
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        file = new File("src/main/resources/data/Users/StayInLoggedInUser.mta");
        try {
            file.createNewFile();
        } catch (IOException e) {
        }
        saveFileOfUsernames();
    }

    public boolean checkPassword(String password) {
        return password.equals(this.password);
    }
    public static boolean checkPassword(String username , String password) {
        MyUser user = getUserByUsername(username);
        if (user == null) {
            return false;
        }
        else {
            return user.checkPassword(password);
        }
    }

    public String getUsername() {
        return username;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        if (highestScore > this.highestScore) {
            this.highestScore = highestScore;
            for (int i = 0; i < users.indexOf(this); i++) {
                if (this.doesHaveMoreScoreThan(users.get(i))) {
                    for (int j = users.indexOf(this)-1; j >= i; j--) {
                        users.set(j+1 , users.get(j));
                    }
                    users.set(i , this);
                }
            }
            saveFileOfUsernames();
        }
    }

    private boolean doesHaveMoreScoreThan(MyUser theUserToCompare) {
        return this.highestScore > theUserToCompare.highestScore;
    }

    public static ArrayList<MyUser> getUsers() {
        return users;
    }

    public int getRank() {
        int rank = 1;
        for (MyUser anotherUser :
                users) {
            if (anotherUser.doesHaveMoreScoreThan(this)) {
                rank ++;
            }
        }
        return rank;
    }

    public String getJSon() {
        String gson = new Gson().toJson(this);
        return gson;
    }

    public void saveUser() {

        FileWriter writer = null;
        try {
            writer = new FileWriter("src/main/resources/data/Users/"+username+".mta");
        } catch (IOException e) {
        }
        try {
            writer.write(getTextToSave());
        } catch (IOException e) {
        }
        try {
            writer.close();
        } catch (IOException e) {
        }
    }

    private MyUser(String username, String password, int highestScore) {
        this.username = username;
        this.password = password;
        this.highestScore = highestScore;
        if (doesUsernameExist(username)) {
            return;
        }
        users.add(this);
    }

    public static void loadUser(String fileName) {
        File file = new File("src/main/resources/data/users/"+fileName);
        try {
            Scanner scanner = new Scanner(file);
            String username = scanner.nextLine();
            String password = scanner.nextLine();
            String highestScoreString = scanner.nextLine();
            String firstHealthPointString = scanner.nextLine();
            MyUser myUser = new MyUser(username, password, Integer.parseInt(highestScoreString));
            myUser.firstHealthPoint = Integer.parseInt(firstHealthPointString);
            if (myUser.firstHealthPoint > 5) {
                myUser.firstHealthPoint = 5;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
        }
    }
    public static void loadUser2(String username) {
        String text;
        try {
            text = new String(Files.readAllBytes(Paths.get("src/main/resources/data/users/"+username+".mta")));
            addUserFromText(text);
        } catch (IOException e) {
        }

    }

    private String getTextToSave() {
        return username + "\n" + password + "\n" + highestScore+"\n"+firstHealthPoint;
    }

    private static void addUserFromText(String text) throws IOException {
        String[] fields = text.split("\n");
        MyUser loadedUser = new MyUser(fields[0] , fields[1]);
        loadedUser.setHighestScore(Integer.parseInt(fields[2]));
        loadedUser.firstHealthPoint = Integer.parseInt(fields[3]);
    }

    private static String getTextOfUsernames() {
        String text = "";
        for (MyUser aUser :
                users) {
            text += aUser.getUsername() + "\n";
        }
        return text;
    }
    private static void saveFileOfUsernames() {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/data/listOfUsernames.mta");
            fileWriter.write(getTextOfUsernames());
            fileWriter.close();
        } catch (IOException e) {
        }
    }
    private static void sortArrayListFromText() {
        ArrayList<MyUser> myUsers = (ArrayList<MyUser>) users.clone();
        String[] usernames = new String[0];
        try {
            usernames = new String(Files.readAllBytes(Paths.get("src/main/resources/data/listOfUsernames.mta"))).split("\n");
        } catch (IOException e) {
        }
        for (int i = 0; i < usernames.length; i++) {
            myUsers.set(i , getUserByUsername(usernames[i]));
        }
        users = myUsers;
    }

    public void changePassword(String newPassword) {
        password = newPassword;
    }

    public void deleteAccount() {
        //delete userFile from files
        File file = new File("src/main/resources/data/users/"+username+".mta");
        file.delete();
        //delete userFolder
        //delete username from lastLoggedIn
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/data/StayInLoggedInUser.mta");
        } catch (IOException e) {
        }
        try {
            fileWriter.write("null");
        } catch (IOException e) {
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
        }
        //delete from users ArrayList
        users.remove(this);
        //delete username from saved list of usernames
        saveFileOfUsernames();
        //set user = null
        MyMenu.user = null;
    }
}
