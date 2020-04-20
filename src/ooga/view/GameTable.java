package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    private static final String BANKROLL_ID = "bankrollDisplay";
    private static final String BET_TOTAL_ID = "betTotalDisplay";
    private static final String GAME_OVER_ID = "GameOverMessage";
    private static final String MAIN_MENU_ID = "mainMenu";
    private static final String GAME_BUTTON_RESOURCES = "resources.GameTableButtons";
    private static final String BET_BUTTON_RESOURCES = "resources.BetButtons";
    private static final String DISPLAY_RESOURCES = "resources.GameTableDisplays";
    private static final String LAYOUT_RESOURCES = "resources.GameTableLayout";
    private GridPane gameRoot;
    private VBox betElements;
    private VBox gamePlayElements;
    private Node gameDisplay;
    private Button mainMenuButton;
    private SceneChanger myScene;
    private Text bankrollDisplay;
    private Text betTotalDisplay;
    private Stage gameOverWindow;
    private Controller myController;
    private GameBoard myGameBoard;
    private ResourceBundle gameplayButtonResources;
    private ResourceBundle betButtonResources;
    private ResourceBundle gameDisplayResources;
    private ResourceBundle layoutResources;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player, String game) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        myController = new Controller();
        myGameBoard = gameBoard;
        initiateGame(player, game);
        initialize(scene, player);

    }

    private void initialize(SceneChanger scene, Player player) throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        myScene = scene;
        gameRoot = new GridPane();
        gameRoot.setHgap(100);
        gameRoot.setVgap(150);
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setPadding(new Insets(1,1,1,1));
        gameRoot.setId("gameDisplay");
        gameplayButtonResources = ResourceBundle.getBundle(GAME_BUTTON_RESOURCES);
        betButtonResources = ResourceBundle.getBundle(BET_BUTTON_RESOURCES);
        gameDisplayResources = ResourceBundle.getBundle(DISPLAY_RESOURCES);
        layoutResources = ResourceBundle.getBundle(LAYOUT_RESOURCES);

        createMainMenuButton();

        bankrollDisplay = new Text(gameDisplayResources.getString(BANKROLL_ID) + player.getMyBankRoll());
        bankrollDisplay.setId(BANKROLL_ID);

        betTotalDisplay = new Text(gameDisplayResources.getString(BET_TOTAL_ID));
        betTotalDisplay.setId(BET_TOTAL_ID);

        betElements = new VBox(20);
        gamePlayElements = new VBox(20);

        gamePlayElements.getChildren().addAll(createGamePlayButtons());
        betElements.getChildren().addAll(betTotalDisplay, createBetButtons());

        displaySceneElements();
        myScene.setRoot(gameRoot);
    }

    private void displaySceneElements() throws NoSuchFieldException, IllegalAccessException {
        for(String element : layoutResources.keySet()) {
            Field sceneElement = GameTable.class.getDeclaredField(element);
            Object x = sceneElement.get(this);
            String[] coords = layoutResources.getString(element).split(",");
            System.out.println(x == null);
            gameRoot.add((Node) x, Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
        }
    }

    private List<Button> createGamePlayButtons() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        List<Button> gamePlayButtons = new ArrayList<>();
        for (String key : gameplayButtonResources.keySet()) {
            String[] buttonProperties = gameplayButtonResources.getString(key).split(",");
            Button button = new Button(buttonProperties[0]);
            button.setId(key);
            Field f = GameTable.class.getDeclaredField(buttonProperties[2]);
            Method m = f.get(this).getClass().getMethod(buttonProperties[1]);
            button.setOnAction(event -> {
                try {
                    m.invoke(f.get(this));
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

    private HBox createBetButtons() {
        HBox betButtons = new HBox(5);
        for(String bet : betButtonResources.keySet()) {
            Button betButton = new Button(betButtonResources.getString(bet));
            betButton.setId(betButtonResources.getString(bet));
            betButton.setOnAction(e -> myGameBoard.performBetAction(Integer.parseInt(bet), myController));
            betButtons.getChildren().add(betButton);
        }
        return betButtons;
    }

    private void Quit() {
        gameRoot.getChildren().clear();

        if(gameOverWindow != null){
            gameOverWindow.close();
        }
        new Menu(myScene);

    }

    private Button createMainMenuButton() {
        mainMenuButton = new Button("Quit");
        mainMenuButton.setOnAction(event -> Quit());
        mainMenuButton.setId(MAIN_MENU_ID);
        return mainMenuButton;
    }

    private void initiateGame(Player player, String game) {
        gameDisplay = myGameBoard.drawGame();
        myController.setGameTable(this, myGameBoard);
        myController.startGame(game, player);

    }

    public void updateBankRoll(int bankroll) {
        bankrollDisplay.setText(gameDisplayResources.getString(BET_TOTAL_ID) + bankroll);
    }

    public void displayGameOver() {
        gameOverWindow = new Stage();

        VBox layout = new VBox(15);
        layout.setId("GameOverWindow");
        Text gameOverMessage = new Text(gameDisplayResources.getString(GAME_OVER_ID));
        gameOverMessage.setId(GAME_OVER_ID);
        layout.getChildren().addAll(gameOverMessage, createMainMenuButton());

        Scene gameOverOptions = new Scene(layout, 200, 200);
        gameOverWindow.setScene(gameOverOptions);

        gameOverWindow.show();

    }

    public void updateBetTotal(int amount) {betTotalDisplay.setText(gameDisplayResources.getString(BET_TOTAL_ID) + amount);}

}
