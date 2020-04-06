package ooga;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;


public class GameTable {
    BorderPane gameRoot;
    SceneChanger myScene;
    Player myPlayer;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player) {
        myPlayer = player;
        initialize(scene);
        initiateGame(gameBoard, player);
    }

    private void initialize(SceneChanger scene) {
        myScene = scene;
        gameRoot = new BorderPane();
        gameRoot.

        HBox topDisplay = new HBox();
        topDisplay.setSpacing(100);

        Button MainMenuButton = new Button("Quit");
        MainMenuButton.setOnAction(event -> Quit());
        MainMenuButton.setId("Main Menu");
        topDisplay.getChildren().add(MainMenuButton);

        Text bankRollDisplay = new Text("BankRoll: " + myPlayer.getMyBankRoll());
        topDisplay.getChildren().add(bankRollDisplay);
        gameRoot.setTop(topDisplay);

        myScene.setRoot(gameRoot);
    }

    private void Quit() {
        gameRoot.getChildren().clear();
        new Menu(myScene);

    }

    private void initiateGame(GameBoard game, Player player) {
        gameRoot.setCenter(game.drawGame());

    }
}
