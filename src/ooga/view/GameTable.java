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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    GridPane gameRoot;
    SceneChanger myScene;
    Text bankrollDisplay;
    Text betTotalDisplay;
    Stage gameOverWindow;
    Controller myController;
    GameBoard myGameBoard;
    ResourceBundle buttonResources;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        myController = new Controller();
        myGameBoard = gameBoard;
        initialize(scene, player);
        initiateGame(player);

    }

    private void initialize(SceneChanger scene, Player player) throws NoSuchMethodException {
        myScene = scene;
        gameRoot = new GridPane();
        gameRoot.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        gameRoot.setHgap(100);
        gameRoot.setVgap(150);
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setPadding(new Insets(1,1,1,1));
        gameRoot.setId("gameDisplay");
        buttonResources = ResourceBundle.getBundle("resources.GameTableButtons");

        gameRoot.add(createMainMenuButton(), 0, 0);

        bankrollDisplay = new Text("BankRoll: $" + player.getMyBankRoll());
        bankrollDisplay.setId("bankrollDisplay");
        gameRoot.add(bankrollDisplay, 1, 0);

        VBox bottomLeftDisplay = new VBox(20);
        VBox bottomRightDisplay = new VBox(20);

        betTotalDisplay = new Text("Total Bet: $0");
        betTotalDisplay.setId("betTotalDisplay");

        //betButton.setDisable(myController.isBetZero()); //Need this controller method


        //Button clearBet = new Button("Clear Bet");
        //clearBet.setOnAction(event -> myController.clearBets());
        //clearBet.setId("clearBet");
        //Button playRound = new Button("Play Round");
        //playRound.setOnAction(event -> myController.playRound());
        //playRound.setId("playRound");
        bottomRightDisplay.getChildren().addAll(createGamePlayButtons());

        bottomLeftDisplay.getChildren().addAll(betTotalDisplay, myGameBoard.createBetButtons(myController));
        //bottomRightDisplay.getChildren().addAll(clearBet, playRound);
        gameRoot.add(bottomLeftDisplay, 0, 2);
        gameRoot.add(bottomRightDisplay, 2, 2);

        myScene.setRoot(gameRoot);
    }

    private List<Button> createGamePlayButtons() throws NoSuchMethodException {
        List<Button> gamePlayButtons = new ArrayList<>();
        for (String key : buttonResources.keySet()) {
            String[] buttonProperties = buttonResources.getString(key).split(",");
            Button button = new Button(buttonProperties[0]);
            button.setId(key);
            Method m = myController.getClass().getMethod(buttonProperties[1]);
            button.setOnAction(event -> {
                try {
                    m.invoke(myController);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            gamePlayButtons.add(button);
        }
        return gamePlayButtons;
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

    private void initiateGame(Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //Class gameClass = game.getClass();
        //Class gameInterface = gameClass.getInterfaces()[0];
        //Method m = gameInterface.getMethod("drawGame");
        //Node gameDisplay = (Node) m.invoke(game);
        Node gameDisplay = myGameBoard.drawGame();
        gameRoot.add(gameDisplay, 1, 1);
        myController.setGameTable(this, myGameBoard);
        myController.startGame("SLOTS", player);

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
