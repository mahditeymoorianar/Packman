package Menus;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MyMenu extends Application{
    public static MyUser user = null;
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
    public void message(String message , GridPane gridPane){
        Object[] children = gridPane.getChildren().toArray();
        Label label = (Label) children[0];
        label.setText(message);
    }
    public static Stage stage = new Stage();

    public boolean isPrimary(MouseEvent mouseEvent) {
        String mouseButton = mouseEvent.getButton().toString();
        if (mouseButton.equals("PRIMARY")) {
            return true;
        }
        return false;

    }
    public boolean isSecondary(MouseEvent mouseEvent) {
        String mouseButton = mouseEvent.getButton().toString();
        if (mouseButton.equals("SECONDARY")) {
            return true;
        }return false;
    }
    public boolean isChoiceKey(KeyEvent keyEvent) {
        String keyName = keyEvent.getCode().toString();
        if (keyName.equals("ENTER") || keyName.equals("SPACE")) {
            return true;
        }
        return false;
    }

}
