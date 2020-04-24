package ooga.view;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

/***
 * An interface that limits a scene object to the below public methods. Used when passing Scene object between frontend displays
 * to allow setting the root of the scene and changing style of the scene.
 * @author Connor Penny
 */
public interface SceneChanger {
    void setRoot(Parent node);
    ObservableList<String> getStylesheets();
}
