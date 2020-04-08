package ooga;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    GridPane gameRoot;
    SceneChanger myScene;
    Player myPlayer;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player) {
        myPlayer = player;
        initialize(scene);
        initiateGame(gameBoard, player);
    }

    private void initialize(SceneChanger scene) {
        myScene = scene;
        gameRoot = new GridPane();
        gameRoot.setHgap(100);
        gameRoot.setVgap(100);
        gameRoot.setAlignment(Pos.TOP_CENTER);

        HBox topDisplay = new HBox();
        topDisplay.setSpacing(100);

        Button MainMenuButton = new Button("Quit");
        MainMenuButton.setOnAction(event -> Quit());
        MainMenuButton.setId("Main Menu");
        topDisplay.getChildren().add(MainMenuButton);

        Text bankRollDisplay = new Text("BankRoll: " + myPlayer.getMyBankRoll());
        topDisplay.getChildren().add(bankRollDisplay);
        gameRoot.add(topDisplay, 0, 0);

        myScene.setRoot(gameRoot);
    }

    private void Quit() {
        gameRoot.getChildren().clear();
        new Menu(myScene);

    }

    private void initiateGame(GameBoard game, Player player) {
        Node gameDisplay = game.drawGame();
        gameRoot.add(gameDisplay, 0, 1);
        SlotMachineGame x = new SlotMachineGame(myPlayer, this, game);
    }
}
