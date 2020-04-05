package ooga;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


public class GameTable {
    GridPane gameRoot;
    SceneChanger myScene;

    public GameTable(SceneChanger scene, String game) {
        myScene = scene;
        gameRoot = new GridPane();
        Button MainMenuButton = new Button("Main Menu");
        MainMenuButton.setOnAction(event -> returnToMainMenu());
        MainMenuButton.setId("Main Menu");
        gameRoot.add(MainMenuButton,0,0);
        myScene.setRoot(gameRoot);
        initiateGame(game);
    }

    private void returnToMainMenu() {
        gameRoot.getChildren().clear();
        new Menu(myScene);

    }

    private void initiateGame(String game) {
        //I think this would be where an interface would be used to create
    }
}
