package ooga.view;

import javafx.scene.Parent;
import javafx.scene.Scene;

/***
 * This is a sublcass of Scene that implements a SceneChanger interface. It allows the developer to limit
 * access to a Scene objects variables
 * @author Connor Penny
 */
public class GameScene extends Scene implements SceneChanger {
    public GameScene(Parent root, double width, double height) {
        super(root, width, height);
    }

}
