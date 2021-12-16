package GameObjects;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.logging.Handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ghost extends MoverGameObject {
    Packman packman;
    boolean setSecured = false;
    final int defaultNear = 40;
    int stop = 0;
    int defaultX=0, defaultY=0;
    int near = 12;
    int slowly = 3;
    int slowlyCounter = 0;
    int sensitivityToPackman = 10;
    private int counter = -1;
    private int counterOfPackman = 0;
    private int counterOfCorrectPosition = 0;
    private final int periodOfCheckingPackman = 40;
    int waitingTime = 0;
    String color;
    public Ghost(String color) throws Exception {
        this.color = color;
        defaultSensitivity = 1;
        try {
            initializeImage(color + "Ghost");
        } catch (Exception e) {
            throw new Exception("نام رنگ وارد شده نادرست است گویا!");
        }
        this.speed = 2;
        super.name = color + "Ghost";

    }

    public void go() throws Exception {
        if (!setSecured && packman.isSecured) {
            initializeImage("secured");
            setSecured = true;
        } else if (setSecured && !packman.isSecured) {
            changeImage();
            setSecured = false;
        }
        if (toPlay) {
            slowlyCounter++;
            if (slowlyCounter > 1000000)
                slowlyCounter = 0;
            if (!setSecured && slowlyCounter%200 == 0) {
                changeImage();
            }
            if (slowlyCounter % slowly == 1) {
                if (stop == 0) {
                    if (waitingTime > 0) waitingTime --;
                    if (waitingTime == 0 && checkIfIsOnThePackman()) {
                        if (counterOfPackman % periodOfCheckingPackman == 0) {
                            if (packman.isSecured) {
                                packman.hitAGhost();
                                this.x = defaultX;
                                this.y = defaultY;
                                findTheBestDirection();
                                stop = 100;
                                waitingTime = 200;
                            } else {
                                toPlay = false;
                                waitingTime = 200;
                                packman.decreaseHealthPoint();
                            }
                            counterOfPackman++;
                        }
                    } else {
                        counterOfPackman = 0;
                    }
                } else {
                    stop --;
                }
                move();
                findTheBestDirection();
                near = defaultNear;
            }
        }

    }

    private void changeImage() {
        if (slowlyCounter % 600 == 0) {
            initializeImage(color+"GhostLeft");
        } else if (slowlyCounter % 600 == 200) {
            initializeImage(color+"Ghost");
        } else {
            initializeImage(color+"GhostRight");
        }
    }

    @Override
    public void move() throws Exception {
        near = 1;
        if (direction.equals("right") && !isAnyRockNearAtRight()) {
            x += speed;
        }
        if (direction.equals("up") && !isAnyRockNearAtUp()) {
            y -= speed;
        }
        if (direction.equals("left") && !isAnyRockNearAtLeft()) {
            x -= speed;
        }
        if (direction.equals("down") && !isAnyRockNearAtBottom()){
            y += speed;
        }
        near = defaultNear;
    }



    private void findTheBestDirection() throws Exception {
        counter++;
        if (counter > 1000000)
            counter = 0;
        if (x%size != 0 || y%size != 0) return;

        periodOfCorrectingPosition = 1;

        Random random = new Random();

        int randomInt = random.nextInt(10);
        boolean smartSelection = false;
        if (packman.x <= this.x + 4 * size &&
                packman.x >= this.x &&
                packman.y <= this.y + size &&
                packman.y >= this.y - size &&
                randomInt <= (setSecured ? 10: 8)) {
            direction = setSecured ? "left" : "right";
            smartSelection = true;
        } else if (packman.x >= this.x - 5 * size &&
                packman.x <= this.x &&
                packman.y <= this.y + size &&
                packman.y >= this.y - size &&
                randomInt <= (setSecured ? 10: 8)) {
            direction = setSecured ? "right" : "left";
            smartSelection = true;
        } else if (packman.x <= this.x + size &&
                packman.x >= this.x - size &&
                packman.y <= this.y + 4 * size &&
                packman.y >= this.y &&
                randomInt <= (setSecured ? 10: 8)) {
            direction = setSecured ? "up" : "down";
            smartSelection = true;
        } else if (packman.x <= this.x + size &&
                packman.x >= this.x - size &&
                packman.y >= this.y - 5 * size &&
                packman.y <= this.y &&
                randomInt <= (setSecured ? 10: 8)) {
            direction = setSecured ? "down" : "up";
            smartSelection = true;
        }
        if (smartSelection) {
            if (direction.equals("right") && !isAnyRockNearAtRight()) {
                return;
            } else if (direction.equals("left") && !isAnyRockNearAtLeft()) {
                return;
            } else if (direction.equals("up") && !isAnyRockNearAtUp()) {
                return;
            } else if (direction.equals("down") && !isAnyRockNearAtBottom()) {
                return;
            }
        }
        int toChange = random.nextInt(4000);
        near = 5;
        if (!((direction.equals("right")&&!isAnyRockNearAtRight()) ||
                direction.equals("up")&&!isAnyRockNearAtUp() ||
                direction.equals("left")&&!isAnyRockNearAtLeft()||
                direction.equals("down")&&!isAnyRockNearAtBottom())) {
            toChange = 0;
        }
        near = 20;
        if (((direction.equals("right") || direction.equals("left")) && ((!isAnyRockNearAtBottom()) || (!isAnyRockNearAtUp())))
            ||((direction.equals("up") || direction.equals("down")) && ((!isAnyRockNearAtLeft()) || !isAnyRockNearAtRight()))){
            int changing = random.nextInt(100);
            if (changing<= 80) {
                toChange = 0;
            }
        }
        if (toChange != 0) return;
        int randomBinary = random.nextInt(2);
        near = 10;

        if (direction.equals("up") || direction.equals("down")) randomInt += 5 ;
        else randomInt -= 5;
        if (randomInt < 5 ) {
            if (randomBinary == 0) {
                while (true) {
                    if (!isAnyRockNearAtUp()) {
                        direction = "up";
                        break;
                    } else if (!isAnyRockNearAtBottom()) {
                        direction = "down";
                        break;
                    } else if (!isAnyRockNearAtLeft()) {
                        direction = "left";
                        break;
                    } else if (!isAnyRockNearAtRight()) {
                        direction = "right";
                        break;
                    } else if (near >= 2) {
                        near--;
                    } else {
                        near = defaultNear;
                        break;
                    }
                }
            } else {
                while (true) {
                    if (!isAnyRockNearAtBottom()) {
                        direction = "down";
                        break;
                    } else if (!isAnyRockNearAtUp()) {
                        direction = "up";
                        break;
                    } else if (!isAnyRockNearAtRight()) {
                        direction = "right";
                        break;
                    } else if (!isAnyRockNearAtLeft()) {
                        direction = "left";
                        break;
                    } else if (near >= 2) {
                        near--;
                    } else {
                        near = defaultNear;
                        break;
                    }
                }
            }
        } else {
            if (randomBinary == 0) {
                while (true) {
                    if (!isAnyRockNearAtLeft()) {
                        direction = "left";
                        break;
                    } else if (!isAnyRockNearAtRight()) {
                        direction = "right";
                        break;
                    } else if (!isAnyRockNearAtBottom()) {
                        direction = "down";
                        break;
                    } else if (!isAnyRockNearAtUp()) {
                        direction = "up";
                        break;
                    } else if (near >= 2) {
                        near--;
                    } else {
                        near = defaultNear;
                        break;
                    }
                }
            } else {
                while (true) {
                    if (!isAnyRockNearAtRight()) {
                        direction = "right";
                        break;
                    } else if (!isAnyRockNearAtLeft()) {
                        direction = "left";
                        break;
                    } else if (!isAnyRockNearAtUp()) {
                        direction = "up";
                        break;
                    } else if (!isAnyRockNearAtBottom()) {
                        direction = "down";
                        break;
                    } else if (near >= 2) {
                        near--;
                    } else {
                        near = defaultNear;
                        break;
                    }
                }
            }
        }
    }

    private void selectDirection() {
        counter++;
        if (x%size != 0 || y%size != 0) return;

        periodOfCorrectingPosition = 1;

        Random random = new Random();
        int toChange = random.nextInt(20);
        near = 5;
        if (!((direction.equals("right")&&!isAnyRockNearAtRight()) ||
                direction.equals("up")&&!isAnyRockNearAtUp() ||
                direction.equals("left")&&!isAnyRockNearAtLeft()||
                direction.equals("down")&&!isAnyRockNearAtBottom())) {
            toChange = 0;
        }
        if (((direction.equals("right") || direction.equals("left")) && ((!isAnyRockNearAtBottom()) || (!isAnyRockNearAtUp())))
                ||((direction.equals("up") || direction.equals("left")) && ((!isAnyRockNearAtLeft()) || !isAnyRockNearAtRight()))){
            toChange = 0;
        }
        if (toChange != 0) return;
        int randomBinary = random.nextInt(2);
        if (direction.equals("right") || direction.equals("left")) {
            if (randomBinary == 0) {
            }
        }
    }

    public boolean checkIfIsOnThePackman() {
        if ((packman.x - sensitivityToPackman > this.x - size && packman.x + sensitivityToPackman < this.x + size) &&
            (packman.y - sensitivityToPackman > this.y - size && packman.y + sensitivityToPackman < this.y + size)) {
            return true;
        }
        return false;
    }

    public void setPackman(Packman packman) {
        this.packman = packman;
    }

    private boolean isAnyRockNearAtRight() {
        for (Rock rock :
                rocks) {
            if (isTheRockNearAtRight(rock)) {
                return true;
            }
        }
        return false;
    }
    private boolean isTheRockNearAtRight(Rock rock) {
        return  (rock.x > this.x && rock.x < this.x + near + size &&
            rock.y > this.y - size && rock.y < this.y+size);
    }
    private boolean isAnyRockNearAtLeft() {
        for (Rock rock :
                rocks) {
            if (isTheRockNearAtLeft(rock)) {
                return true;
            }
        }
        return false;
    }
    private boolean isTheRockNearAtLeft(Rock rock) {
        return  (rock.x < this.x && rock.x + size - 3 > this.x - near &&
                rock.y > this.y - size && rock.y < this.y+size);
    }
    private boolean isAnyRockNearAtUp() {
        for (Rock rock :
                rocks) {
            if (isTheRockNearAtUp(rock)) {
                return true;
            }
        }
        return false;
    }
    private boolean isTheRockNearAtUp(Rock rock) {
        return (rock.x > this.x - size && rock.x < this.x + size &&
                rock.y + size > this.y - near && rock.y < this.y);
    }
    private boolean isAnyRockNearAtBottom() {
        for (Rock rock :
                rocks) {
            if (isTheRockNearAtBottom(rock)) {
                return true;
            }
        }
        return false;
    }
    private boolean isTheRockNearAtBottom(Rock rock) {
        return (rock.x > this.x - size && rock.x < this.x + size &&
                rock.y >= this.y + size && rock.y < this.y + near + size);
    }

    public void setDefaultY(int defaultY) {
        this.defaultY = defaultY;
    }

    public void setDefaultX(int defaultX) {
        this.defaultX = defaultX;
    }
    @Override
    public void setXY(int raw, int column) {
        this.x = column*size;
        this.y = raw*size;
        this.defaultX = this.x;
        this.defaultY = this.y;
        this.setPosition();
    }
}
