package Menus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyTest {
    @Test
    public void testSetHighestScore() {
        MyUser user1, user2, user3, user4, user5, user6, user7, user8, user9, user10;
        user1 = new MyUser("username1" , "password1");
        user2 = new MyUser("username2" , "password2");
        user3 = new MyUser("username3" , "password3");
        user4 = new MyUser("username4" , "password4");
        user5 = new MyUser("username5" , "password5");
        user6 = new MyUser("username6" , "password6");
        user7 = new MyUser("username7" , "password7");
        user8 = new MyUser("username8" , "password8");
        user9 = new MyUser("username9" , "password9");
        user10 = new MyUser("username10" , "password10");


        user1.setHighestScore(100);
        user2.setHighestScore(200);
        user3.setHighestScore(400);
        user8.setHighestScore(1000);
        user4.setHighestScore(400);
        user5.setHighestScore(600);
        user6.setHighestScore(600);
        user7.setHighestScore(600);
        user5.setHighestScore(600);
        user8.setHighestScore(100);
        user9.setHighestScore(900);
        user10.setHighestScore(1000);

        assertEquals(user8 , MyUser.getUsers().get(0));

    }
}
