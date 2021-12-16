package GameObjects;

public class Collectable extends GameObject {
    public boolean isCollected = false;
    private static int sensitivity = 0;
    private static int difficulty = 10;
    public boolean doesCollect(Packman packman) {
        if (isCollected) {return false;}
        if ((packman.x >= this.x - size + difficulty && packman.x <= this.x + size - difficulty) &&
            (packman.y >= this.y - size + difficulty && packman.y <= this.y + size - difficulty)) {
            if (!isCollected) {
                action();
            }
            isCollected = true;
            this.initializeImage("none");
            return true;
        }
        return false;
    }

    public void action() {
    }
}
