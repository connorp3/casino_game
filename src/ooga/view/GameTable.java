package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.model.Player;
import ooga.view.data.GamePlayElementsParser;
import ooga.view.data.ReflectionException;
import java.util.*;

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    private static final String BANKROLL_ID = "bankrollDisplay";
    private static final String BET_TOTAL_ID = "betTotalDisplay";
    private static final String GAME_OVER_ID = "GameOverMessage";
    private static final String RESOURCES_PACKAGE = "resources.GameTableProperties.";
    private static final String GAME_BUTTON_RESOURCES = "GameTableButtons";
    private static final String BET_BUTTON_RESOURCES = "BetButtons";
    private static final String DISPLAY_RESOURCES = "GameTableDisplays";
    private static final String LAYOUT_RESOURCES = "GameTableLayout";
    private static final String ADMIN_RESOURCES = "AdminButtons";
    private static final String GAME_MODE_BUTTON= "Set Game Mode";
    private Locale myLocale;
    private GridPane gameRoot;
    private VBox gamePlayElements;
    private VBox betElements;
    private HBox adminButtons;
    private Node gameDisplay;
    private Button mainMenuButton;
    private Button changeGameButton;
    private SceneChanger myScene;
    private Text bankrollDisplay;
    private Text betTotalDisplay;
    private Stage gameOverWindow;
    private Controller myController;
    private Player myPlayer;
    private GameBoard myGameBoard;
    private static ResourceBundle gameplayButtonResources;
    private static ResourceBundle betButtonResources;
    private static ResourceBundle gameDisplayResources;
    private static ResourceBundle layoutResources;
    private static ResourceBundle adminButtonResources;


    public GameTable(SceneChanger scene, GameBoard gameBoard, Player player, String game, Locale locale) {
        myLocale = locale;
        myController = new Controller();
        myPlayer = player;
        myGameBoard = gameBoard;
        initiateGame(player, game);
        initialize(scene, player);

    }

    private void initialize(SceneChanger scene, Player player) {
        myScene = scene;
        gameRoot = new GridPane();
        gameRoot.setHgap(100);
        gameRoot.setVgap(150);
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setPadding(new Insets(1,1,1,1));
        gameRoot.setId("gameDisplay");
        betButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + BET_BUTTON_RESOURCES);
        gameplayButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + GAME_BUTTON_RESOURCES, myLocale);
        gameDisplayResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + DISPLAY_RESOURCES, myLocale);
        layoutResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + LAYOUT_RESOURCES, myLocale);
        adminButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + ADMIN_RESOURCES, myLocale);

        adminButtons = new HBox(20);
        betElements = new VBox(20);
        gamePlayElements = new VBox(20);

        adminButtons.getChildren().addAll(createGamePlayButtons(this, adminButtonResources));
        gamePlayElements.getChildren().addAll(createGamePlayButtons(myController, gameplayButtonResources));
        gamePlayElements.getChildren().add(makeGameModeButton());

        bankrollDisplay = new Text(gameDisplayResources.getString(BANKROLL_ID) + player.getMyBankRoll());
        bankrollDisplay.setId(BANKROLL_ID);

        betTotalDisplay = new Text(gameDisplayResources.getString(BET_TOTAL_ID));
        betTotalDisplay.setId(BET_TOTAL_ID);

        betElements.getChildren().addAll(betTotalDisplay, createBetButtons());

        displaySceneElements();
        myScene.setRoot(gameRoot);
    }

    private void displaySceneElements() {
        for(String element : layoutResources.keySet()) {
            try {
                GamePlayElementsParser parser = new GamePlayElementsParser();
                Node x = parser.getGamePlayElementsAsField(this, element);
                String[] coords = layoutResources.getString(element).split(",");
                gameRoot.add(x, Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
            }
            catch (Exception e) {
                throw new ReflectionException("Improperly Configured GameTable Layout File", e);
            }

        }
    }

    static <T> List<Button> createGamePlayButtons(T element, ResourceBundle resources) {
        List<Button> gamePlayButtons = new ArrayList<>();
        for (String key : resources.keySet()) {
            String[] buttonProperties = resources.getString(key).split(",");
            String label = buttonProperties[0];
            String buttonMethod = buttonProperties[1];
            Button button = new Button(label);
            button.setId(key);
            button.setOnAction(event -> {
                GamePlayElementsParser parser = new GamePlayElementsParser();
                parser.setGamePlayButtonAction(element, buttonMethod);
                }
            );
            if (buttonProperties.length == 3) {
                String buttonField = buttonProperties[2];
                GamePlayElementsParser parser = new GamePlayElementsParser();
                parser.setGamePlayButtonToField(element, button, buttonField);
            }
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

    private Button makeGameModeButton() {
        Button gameModeButton = new Button(GAME_MODE_BUTTON);
        gameModeButton.setOnAction(e -> setGameMode());
        return gameModeButton;
    }

    private void setGameMode() {
        if(myGameBoard.getGameMode() != null) {
            //myController.setParameters(myGameBoard.getGameMode());
        }
    }

    public void Quit() {
        gameRoot.getChildren().clear();

        if(gameOverWindow != null){
            gameOverWindow.close();
        }
        new Menu(myScene);

    }

    public void ChangeGame() {
        gameRoot.getChildren().clear();
        new Menu(myScene, myPlayer);
    }

    private void initiateGame(Player player, String game) {
        gameDisplay = myGameBoard.drawGame();
        myController.setGameTable(this, myGameBoard);
        myController.startGame(game, player);

    }

    public void updateBankRoll(int bankroll) {
        bankrollDisplay.setText(gameDisplayResources.getString(BANKROLL_ID) + bankroll);
    }

    public void displayGameOver() {
        gameOverWindow = new Stage();

        VBox layout = new VBox(15);
        layout.setId("GameOverWindow");
        Text gameOverMessage = new Text(gameDisplayResources.getString(GAME_OVER_ID));
        gameOverMessage.setId(GAME_OVER_ID);
        layout.getChildren().addAll(gameOverMessage, mainMenuButton);

        Scene gameOverOptions = new Scene(layout, 200, 200);
        gameOverWindow.setScene(gameOverOptions);

        gameOverWindow.show();

    }

    public void updateBetTotal(int amount) {betTotalDisplay.setText(gameDisplayResources.getString(BET_TOTAL_ID) + amount);}

    public void createAlert(String errorMessage) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(errorMessage);
    }

}
