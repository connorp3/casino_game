package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.Player;

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    GridPane gameRoot;
    SceneChanger myScene;
    Text bankrollDisplay;
    Text betTotalDisplay;
    Stage gameOverWindow;
    Controller myController;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player) {
        myController = new Controller();
        initialize(scene, player);
        initiateGame(gameBoard, player);

    }

    private void initialize(SceneChanger scene, Player player) {
        myScene = scene;
        gameRoot = new GridPane();
        gameRoot.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        gameRoot.setHgap(100);
        gameRoot.setVgap(150);
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setPadding(new Insets(1,1,1,1));
        gameRoot.setId("gameDisplay");

        gameRoot.add(createMainMenuButton(), 0, 0);

        bankrollDisplay = new Text("BankRoll: $" + player.getMyBankRoll());
        bankrollDisplay.setId("bankrollDisplay");
        gameRoot.add(bankrollDisplay, 1, 0);

        VBox bottomLeftDisplay = new VBox(20);
        VBox bottomRightDisplay = new VBox(20);

        betTotalDisplay = new Text("Total Bet: $0");
        betTotalDisplay.setId("betTotalDisplay");
        Button betButton = new Button("$1"); //This will probably have to be created in its own class
        betButton.setOnAction(event -> myController.placeBet(1, null));
        betButton.setId("betButton");

        Button clearBet = new Button("Clear Bet");
        clearBet.setOnAction(event -> myController.clearBets());
        clearBet.setId("clearBet");
        Button playRound = new Button("Play Round");
        playRound.setOnAction(event -> myController.playRound());
        playRound.setId("playRound");

        bottomLeftDisplay.getChildren().addAll(betTotalDisplay, betButton);
        bottomRightDisplay.getChildren().addAll(clearBet, playRound);
        gameRoot.add(bottomLeftDisplay, 0, 2);
        gameRoot.add(bottomRightDisplay, 2, 2);

        myScene.setRoot(gameRoot);
    }

    private void Quit() {
        gameRoot.getChildren().clear();

        if(gameOverWindow != null){
            gameOverWindow.close();
        }
        new Menu(myScene);

    }

    private Button createMainMenuButton() {
        Button MainMenuButton = new Button("Quit");
        MainMenuButton.setOnAction(event -> Quit());
        MainMenuButton.setId("MainMenu");
        return MainMenuButton;
    }

    private void initiateGame(GameBoard game, Player player) {
        Node gameDisplay = game.drawGame();
        gameRoot.add(gameDisplay, 1, 1);

        myController.setGameTable(this, game);
        myController.loadPlayer(player);
        myController.startGame("SLOTS");

    }

    public void updateBankRoll(int bankroll) {
        bankrollDisplay.setText("BankRoll: $" + bankroll);
    }

    public void displayGameOver() {
        gameOverWindow = new Stage();

        VBox layout = new VBox(15);
        layout.setId("GameOverWindow");
        Text gameOverMessage = new Text("Game Over: You ran out of funds");
        gameOverMessage.setId("GameOverMessage");
        layout.getChildren().addAll(gameOverMessage, createMainMenuButton());

        Scene gameOverOptions = new Scene(layout, 200, 200);
        gameOverWindow.setScene(gameOverOptions);

        gameOverWindow.show();

    }

    public void updateBetTotal(int amount) {
        betTotalDisplay.setText("Total Bet: $" + amount);
    }
}
