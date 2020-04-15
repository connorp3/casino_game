package ooga.view;

import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;


public interface SceneChanger {
    void setRoot(Parent node);
    void setFill(Paint value);
    ObservableList<String> getStylesheets();
}
