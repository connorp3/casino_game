package ooga;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameTable {
    GridPane gameRoot;
    SceneChanger myScene;

    public GameTable(SceneChanger scene) {
        myScene = scene;
        gameRoot = new GridPane();
        Button MainMenuButton = new Button("Main Menu");
        MainMenuButton.setOnAction(event -> returnToMainMenu());
        MainMenuButton.setId("Main Menu");
        gameRoot.add(MainMenuButton,0,0);
        myScene.setRoot(gameRoot);
    }

    private void returnToMainMenu() {
        gameRoot.getChildren().clear();
        GridPane menuRoot = new GridPane();
        new Menu(myScene);

    }
}
