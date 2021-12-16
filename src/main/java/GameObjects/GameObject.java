package GameObjects;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle{
    protected int x, y;
    public static int size = 40;
    public static int windowWidth = 900, windowHeight = 600;
    public void initializeImage(String nameOfImage) {
        this.setHeight(size);
        this.setWidth(size);
        try {
            ImagePattern shape = new ImagePattern(new Image(getClass().getResource("/images/" + nameOfImage + ".png").toExternalForm()));
            this.setFill(shape);
        } catch (NullPointerException e) {

        }
    }

    public void setXY(int raw, int column) {
        this.x = column*size;
        this.y = raw*size;
        this.setPosition();
    }

    /**
     * Creates an empty instance of Rectangle.
     */
    public GameObject() {

    }

    public int getTheX() {
        return x;
    }

    public int getTheY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setPosition() {
        this.setLayoutX(this.x);
        this.setLayoutY(this.y);
    }

    public static int getSize() {
        return size;
    }

    public static void setWindowHeight(int windowHeight) {
        GameObject.windowHeight = windowHeight;
    }

    public static int getWindowWidth() {
        return windowWidth;
    }
}
