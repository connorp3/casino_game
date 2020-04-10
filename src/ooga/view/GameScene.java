package ooga.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

public class GameScene extends Scene implements SceneChanger {
    public GameScene(Parent root, double width, double height, Paint fill) {
        super(root, width, height, fill);
    }
}
