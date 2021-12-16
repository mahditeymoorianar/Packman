package GameObjects;

import java.util.ArrayList;

public class MoverGameObject extends GameObject {
    public static boolean toPlay = true;
    protected String direction = "right"; /* can be "right", "left", "up", "down" */
    protected float speed = 1;
    ArrayList<Rock> rocks;
    protected int defaultSensitivity = 1;
    protected int sensitivity;
    public String name;
    int counter = 0;
    int periodOfCorrectingPosition = 4;

    public MoverGameObject() {
        sensitivity = defaultSensitivity;
    }

    public void setDirection(String direction) throws Exception{
        if (direction == null) {
            throw new NullPointerException();
        } else if (direction.equals("right") || direction.equals("left") || direction.equals("up") || direction.equals("down")) {
            this.direction = direction;
        } else {
            throw new IllegalArgumentException();
        }

    }

    public void move() throws Exception {
        counter++;
        if (counter%periodOfCorrectingPosition == 0) {
            correctPosition();
        }

        if (direction.equals("right")) {
            if (!checkHitRockAtRight()) {
                x += speed;
                if (checkHitRockAtRight()) {
                    x -= speed;
                }
            }
        }
        else if (direction.equals("left")) {
            if (!checkHitRockAtLeft()) {
                x -= speed;
                if (checkHitRockAtLeft()) {
                    x += speed;
                }
            }
        }
        else if (direction.equals("up")) {
            if (!checkHitRockAtUp()) {
                y -= speed;
                if (checkHitRockAtUp()) {
                    y += speed;
                }
            }
        }
        else if (direction.equals("down")) {
            if (!checkHitRockAtBottom()) {
                y += speed;
                if (checkHitRockAtBottom()) {
                    y -= speed;
                }
            }
        }
        else {
            throw new RuntimeException();
        }
    }

    protected void correctPosition() {
        sensitivity = 0;
        if (checkHitRockAtUp()) {
            y += 1;
        }
        if (checkHitRockAtRight()) {
            x -= 1;
        }
        if (checkHitRockAtBottom()) {
            y -= 1;
        }
        if (checkHitRockAtLeft()) {
            x += 1;
        }
        sensitivity = defaultSensitivity;
    }

    

    public boolean checkHitUpWall() {
        return (this.y <= 0);
    }
    public boolean checkHitRightWall() {
        return this.x >= windowWidth -size;
    }
    public boolean checkHitLeftWall() {
        return this.x <= 0;
    }

    public boolean checkHitFloor() {
        return this.y >= windowHeight -size;
    }


    public boolean checkHitRockAtRight(Rock rock) {
        return (this.x + size - sensitivity > rock.x && this.x < rock.x - size/2 &&
                this.y + size - sensitivity > rock.y && this.y < rock.y + size );
    }
    public boolean checkHitRockAtRight() {
        for (Rock rock : rocks) {
            if (checkHitRockAtRight(rock)){
                return true;
            }
        }
        return false;
    }

    public boolean checkHitRockAtLeft(Rock rock) {
        return (this.x + sensitivity < rock.x + size && this.x > rock.x + size/2 &&
                this.y + size - sensitivity > rock.y && this.y < rock.y + size - sensitivity);
    }
    public boolean checkHitRockAtLeft() {
        for (Rock rock : rocks) {
            if (checkHitRockAtLeft(rock)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkHitRockAtUp(Rock rock) {
        return (this.x + size - sensitivity > rock.x && this.x < rock.x + size &&
                this.y + sensitivity < rock.y + size && this.y > rock.y + size/2);
    }
    public boolean checkHitRockAtUp() {
        for (Rock rock : rocks) {
            if (checkHitRockAtUp(rock)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkHitRockAtBottom(Rock rock) {
        return (this.x + size - sensitivity > rock.x && this.x < rock.x + size &&
                this.y +size - sensitivity > rock.y && this.y < rock.y - size/2);
    }
    public boolean checkHitRockAtBottom() {
        for (Rock rock :
                rocks) {
            if (checkHitRockAtBottom(rock)) {
                return true;
            }
        }
        return false;
    }

    public void setRocks(ArrayList<Rock> rocks) {
        this.rocks = rocks;
    }
}
