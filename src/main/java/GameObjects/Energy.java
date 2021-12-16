package GameObjects;

import javafx.scene.control.Label;

public class Energy extends Collectable {
    Packman packman;
    Label label;

    public void setLabel(Label label) {
        this.label = label;
    }

    public Energy(Packman packman) {
        this.packman = packman;
        initializeImage("book");
    }

    public void setPackman(Packman packman) {
        this.packman = packman;
    }

    public void action () {
        packman.setSecured();
        initializeImage("none");
    }
}
