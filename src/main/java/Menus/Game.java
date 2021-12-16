package Menus;
import GameObjects.*;


public class Game {
    public static int width = 600, height = 400;
    Ghost ghost1, ghost2, ghost3, ghost4;
    public Game() throws Exception {
        ghost1 = new Ghost("red");
        ghost2 = new Ghost("blue");
        ghost3 = new Ghost("green");
        ghost4 = new Ghost("purple");
        ghost1.setX(0); ghost1.setY(0);
    }

    int defaultPackmanPositionX , defaultPackmanPositionY;//should be in GameMap




}
