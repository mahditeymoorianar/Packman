package GameObjects;

import Menus.MyUser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

public class GameMaps {
    public static String[][] maps = {
            {"RRRRRRRRRRRRRRRRRRRRRRR" ,
             "R   3ER         R R RER" ,
             "R RRRRRRRRR RRRRR R R R" ,
             "R   R     R R     R R R" ,
             "RRR R RRR R R RRR R R R" ,
             "R R R R R       R     R" ,
             "R R RRR RRR RRRRRRRRRRR" ,
             "R     R           4   R" ,
             "R RRR R RRR RRRRR RRRRR" ,
             "R R R  2  R R     R   R" ,
             "R R RRRRRRR RRRRRRR RRR" ,
             "R R       R           R" ,
             "R RRR RRR R RRRRRRRRRRR" ,
             "R0   1R   R          ER" ,
             "RRRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRR" ,
             "R    0  RR   RRRR    R" ,
             "RRR RR   1 R R  R  R R" ,
             "R R RR RR  R R  RR R R" ,
             "R    R  R  2  R R  RRR" ,
             "RRR R R R RR    RR  RR" ,
             "RRR R   R    RR E    R" ,
             "RR 3R R R RR R RR RR R" ,
             "RR R    R  R    4  R R" ,
             "R  RRRR  R   RRRR RR R",
             "RER      R R RR   R  R",
             "RRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRR" ,
             "R RRRRRRE RRRRRRRRR R" ,
             "R  1  RR2 RR     RR R" ,
             "R RRRRRR  RR RRRRRR R" ,
             "R RR  RR  RR3       R" ,
             "R RR   R  RR RRRRRRRR" ,
             "R RRRR  R RR RRRRRRRR" ,
             "R  0              4ER" ,
             "RRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRR" ,
             "R                     R" ,
             "R RRR R3RR RRRR R RR  R" ,
             "R ER  RR      R4RR  R R" ,
             "R  R  RR    RRR RR  R R" ,
             "R  R  RR   R0ER RR  R R" ,
             "R 2R  RR   R    RR  R R" ,
             "R RRR RR    RRR RR  R R" ,
             "R           1         R" ,
             "RRRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRR" ,
             "RRE     RRR4       R" ,
             "R0 RRRR RRRR RRRRE R" ,
             "RRRRRRR RRRR       R" ,
             "RRR     RRRR RRRRRRR" ,
             "RR  RRR RRRR RRRRR3R" ,
             "RRR RRR RRRR RRRRR R" ,
             "RRR1 R    R   RRRR R" ,
             "RRRRRRRR    RRRRRR R" ,
             "R 2                R" ,
             "RRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRR" ,
             "RR0                  R" ,
             "RR RRRRR RRRRR RRRRR R" ,
             "RR RE1RR RRRRR RR4ER R" ,
             "RR RR RR RRRRR RR RR R" ,
             "RR    RR R R R RR    R" ,
             "RRRRRRRR R R R RRRRRRR" ,
             "RRE RRRR R R R RRRR  R" ,
             "RRR 2RR    R   RRR3 RR" ,
             "RRRR     RRRRR     RRR" ,
             "RRRRRRRR       RRRRRRR" ,
             "RRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRR" ,
             "R   R0RRR RRE3R" ,
             "R R     R4R R R" ,
             "R RRRER R R R2R" ,
             "R R   R R   R R" ,
             "R   R  1  RE  R" ,
             "RRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRR" ,
             "R R R R  R  R R R R  R R" ,
             "R       RRR   3   RR   R" ,
             "R R RR RR   RR RR RR R R" ,
             "R R R R   R  R R4  R R R" ,
             "R R     RRR RRRR R  ER R" ,
             "RR  R R RR  R  R R RR  R" ,
             "R  RR RR R RRR R   R  0R" ,
             "R RR         R R R   R R" ,
             "R E1RR2R R R     R R RRR" ,
             "RRRRRRRRRRRRRRRRRRRRRRRR" ,
            }
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRRR" ,
             "R                 R RE4 R" ,
             "R R R RRRRR RRR RRR RRR R" ,
             "R RER     R   R     R   R" ,
             "R RRR RRR RR RR RRR R R R" ,
             "R       R R    3  R   R R" ,
             "R RRRRRRR R RRRRRRRRRRR R" ,
             "R   R R1  R R  2R     R R" ,
             "R RRR R R R R R R RR RR R" ,
             "R R0    R     R R       R" ,
             "RRRRRRRRRRRRRRRRRRRRRRRRR" ,
            }
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRR" ,
             "R0        R R R     RER" ,
             "R RR R RR R R R RRR R4R" ,
             "R       R       R   R R" ,
             "R RRR RRR RRRRR RRR R R" ,
             "R1R R       R         R" ,
             "RRR R RRR R RRRRR RRR R" ,
             "R     R   R R     R   R" ,
             "R R RRR RRR R RRRRR RRR" ,
             "R2R     R   R     R 3ER" ,
             "RRRRRRRRRRRRRRRRRRRRRRR" ,
            }
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRR" ,
             "R0R R R  RRR R        4R" ,
             "R     RR  R    R RRR R R" ,
             "R RR        R RR R RRR R" ,
             "R  R RRRRRR R R     R  R" ,
             "RR R  R R R RRRRR R RR R" ,
             "R1 R RR     R  R  R    R" ,
             "R    R2  RRRR RRRR R R3R" ,
             "R RERR R       E   R   R" ,
             "RRRRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRRR" ,
             "R0        R          RR R" ,
             "R  RRRRRR   RRRRRRRRE   R" ,
             "R R      RE         RR RR" ,
             "R   RRRR  R R  RRR   R RR" ,
             "R  R1     R2R  R    RR  R" ,
             "R R  RRR  R R RRRRRR   RR" ,
             "R R R   R R R        R  R" ,
             "R R R R R R R3R RRRRRRR R" ,
             "R R RER R R R R R  RE   R" ,
             "R R  RR R RRR RRR RRRR  R" ,
             "R  R    R  RE      R  4 R" ,
             "RRRRRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRRR" ,
             "RE          R          ER" ,
             "RRR RRRRRRR R RRRRRRR RRR" ,
             "R      4R   R   R3      R" ,
             "R RRR R R RRRRR R R RRR R" ,
             "R R R R R   R   R R R R R" ,
             "R R R RRRRRRRRRRRRR R R R" ,
             "R R R     R 0 R     R R R" ,
             "R R R RRR R R R RRR R R R" ,
             "R R R R   R R R   R R R R" ,
             "R R R RRRRR R RRRRR R R R" ,
             "R   R       R       R   R" ,
             "RRRRRRRRRRR R RRRRRRRRRRR" ,
             "R         R R R         R" ,
             "R RRRRRRR R R R RRRRRRR R" ,
             "R RE    2 R R R 1    ER R" ,
             "R RRRRRRRRR R RRRRRRRRR R" ,
             "R           R           R" ,
             "RRRRRRRRRRRRRRRRRRRRRRRRR"}
            ,
            {"RRRRRRRRRRRRRRRRRRRRRRRRR",
             "R0    R  RR   R      RERR",
             "R RRR     R R   R RR  3RR",
             "R R1  R R R RRRRR RR R RR",
             "R R R   R         R  R RR",
             "R   R RRRR R RRRR R  R RR",
             "RR    RR   R RR    R R RR",
             "R2 RR RR RRR RRRRR R R RR",
             "RE R       R         4 RR",
             "RRRRRRRRRRRRRRRRRRRRRRRRR",}
    };
    ArrayList<String[]> mapsArr = new ArrayList<String[]>();

    public static String[][] getMaps(MyUser aUser) {
        if (aUser == null) {
            return maps;
        }
        GameMaps gameMaps = new GameMaps();
        gameMaps.initializeMapsArr(aUser);
        return gameMaps.getMapsArrStr();
    }
    private String[][] getMapsArrStr() {
        String[][] answer = new String[mapsArr.size()][];
        for (int i = 0; i < mapsArr.size(); i++) {
            answer[i] = mapsArr.get(i);
        }
        return answer;
    }

    private String[] removeEnters(String[] strings) {
        String[] ans = new String[strings.length];
        for (int i = 0; i < ans.length; i++) {
            try {
                ans[i] = ans[i].replace('\n' , 'R');
            } catch (Exception e) {
            }
        }
        return ans;
    }



    public ArrayList<String[]> getMapsArr() {
        return mapsArr;
    }

    private void initializeMapsArr(MyUser aUser) {
        if (aUser == null) {
            return;
        }
        try {
            File file = new File("src/main/resources/data/Maps/" + aUser.getUsername() + "/");
            File[] files = file.listFiles();
            for (String[] aMap :
                    maps) {
                mapsArr.add(aMap);
            }
            for (File aFile :
                    files) {
                try {
                    mapsArr.add((new String(Files.readAllBytes(Paths.get(aFile.getPath())))).split("\n"));
                } catch (IOException e) {
                }
            }
        } catch (Exception e) {

        }
    }

    public static String[] newMap(int rows , int columns) {
        String newMapGenerated = generateNewMap(rows , columns);
        char[] mapCharArr = newMapGenerated.toCharArray();
        for (int i = 0; i < mapCharArr.length; i++) {
            if (mapCharArr[i] == '1' || mapCharArr[i] == 'e') {
                mapCharArr[i] = 'R';
            } else if (mapCharArr[i] == '0' || mapCharArr[i] == '*') {
                mapCharArr[i] = ' ';
            }
        }
        Random random = new Random();
        boolean toContinue = true;
        do {
            int aRandomHomeOfArray = random.nextInt(mapCharArr.length);
            if (mapCharArr[aRandomHomeOfArray] == ' ') {
                mapCharArr[aRandomHomeOfArray] = '0';
                toContinue = false;
            }
        } while (toContinue);
        toContinue = true;
        while (toContinue) {
            int aRandomHomeOfArray = random.nextInt(mapCharArr.length);
            if (mapCharArr[aRandomHomeOfArray] == ' ') {
                mapCharArr[aRandomHomeOfArray] = '1';
                toContinue = false;
            }
        }
        toContinue = true;
        while (toContinue) {
            int aRandomHomeOfArray = random.nextInt(mapCharArr.length);
            if (mapCharArr[aRandomHomeOfArray] == ' ') {
                mapCharArr[aRandomHomeOfArray] = '2';
                toContinue = false;
            }
        }
        toContinue = true;
        while (toContinue) {
            int aRandomHomeOfArray = random.nextInt(mapCharArr.length);
            if (mapCharArr[aRandomHomeOfArray] == ' ') {
                mapCharArr[aRandomHomeOfArray] = '3';
                toContinue = false;
            }
        }
        toContinue = true;
        while (toContinue) {
            int aRandomHomeOfArray = random.nextInt(mapCharArr.length);
            if (mapCharArr[aRandomHomeOfArray] == ' ') {
                mapCharArr[aRandomHomeOfArray] = '4';
                toContinue = false;
            }
        }
        for (int i = 0; i < mapCharArr.length; i++) {
            if (mapCharArr[i] == ' ') {
                int toAdd = random.nextInt(50);
                if (toAdd == 0) {
                    mapCharArr[i] = 'E';
                }
            }
        }
        for (int i = 2*columns+4; i < mapCharArr.length-2*columns-4; i++) {
            if (i%(2*columns+2) > 2 && i %(2*columns+2) <2*columns-2) {
                if (mapCharArr[i] == 'R') {
                    int toDelete = random.nextInt(5);
                    if (toDelete == 0) {
                        mapCharArr[i] = ' ';
                    }
                }
            }
        }
        String ans = "";
        for (int i = 0; i < 2*rows+1; i++) {
            for (int j = 0; j < 2 * columns + 2; j++) {
                try {
                    ans += (mapCharArr[i*(2*columns+2) + j]);
                } catch (Exception e) {
                }
            }
        }
        String[] aNewMap = ans.split("\n");
        return aNewMap;

    }

    public static String generateNewMap(int rows , int columns) {
        Random rand = new Random();
        int height = rows;
        int width = columns;
        int numOfMazes = 1;

        char[][] maze = new char[2 * height + 1][2 * width + 1];
        initializeMaze(maze, 2 * height + 1, 2 * width + 1);

        int i = 0;
        StringBuilder ans = new StringBuilder();
        boolean[][] visited = new boolean[height][width];
        visited[0][0] = true;
        generateMaze(maze, height, width, visited, rand, 0, 0);
        for (int j = 0; j < height * 2 + 1; j++) {
            for (int k = 0; k < width * 2 + 1; k++) {
                ans.append(maze[j][k]);
                if (maze[j][k] == '0') maze[j][k] = '1';
            }
            ans.append("\n");
        }
        return (ans.toString());

    }

    static void initializeMaze(char[][] maze, int height, int width) {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if (i % 2 == 0 || j % 2 == 0) maze[i][j] = '1';
                else maze[i][j] = '*';

        maze[0][1] = maze[height - 1][width - 2] = 'e';
    }

    static void generateMaze(char[][] maze, int height, int width, boolean[][] visited, Random rand, int currentX, int currentY) {
        char[] freeNeighbours = new char[4];
        int numOfFreeNeighbours = 0;
        if (currentX + 1 < width && !visited[currentY][currentX + 1]) {
            freeNeighbours[numOfFreeNeighbours] = 'R';
            numOfFreeNeighbours++;
        }
        if (currentX - 1 >= 0 && !visited[currentY][currentX - 1]) {
            freeNeighbours[numOfFreeNeighbours] = 'L';
            numOfFreeNeighbours++;
        }
        if (currentY + 1 < height && !visited[currentY + 1][currentX]) {
            freeNeighbours[numOfFreeNeighbours] = 'D';
            numOfFreeNeighbours++;
        }
        if (currentY - 1 >= 0 && !visited[currentY - 1][currentX]) {
            freeNeighbours[numOfFreeNeighbours] = 'U';
            numOfFreeNeighbours++;
        }
        if (numOfFreeNeighbours == 0) return;

        int direction = rand.nextInt(numOfFreeNeighbours);

        move(freeNeighbours[direction], maze, currentY, currentX, visited, height, width, rand);
        if (numOfFreeNeighbours == 1) return;
        generateMaze(maze, height, width, visited, rand, currentX, currentY);
    }

    static void move(char direction, char[][] maze, int currentY, int currentX, boolean[][] visited, int height, int width, Random rand) {
        int newX = currentX, newY = currentY;
        switch (direction) {
            case 'R':
                maze[currentY * 2 + 1][currentX * 2 + 2] = '0';
                newX++;
                break;
            case 'L':
                maze[currentY * 2 + 1][currentX * 2] = '0';
                newX--;
                break;
            case 'D':
                maze[currentY * 2 + 2][currentX * 2 + 1] = '0';
                newY++;
                break;
            case 'U':
                maze[currentY * 2][currentX * 2 + 1] = '0';
                newY--;
                break;
        }
        visited[newY][newX] = true;
        generateMaze(maze, height, width, visited, rand, newX, newY);
    }
}
