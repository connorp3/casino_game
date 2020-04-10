package ooga;

import javafx.scene.Parent;
import javafx.scene.paint.Paint;


public interface SceneChanger {
    void setRoot(Parent node);
    void setFill(Paint value);
}
