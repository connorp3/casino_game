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
import ooga.view.data.ResourcesException;

import java.util.*;

/***
 * This creates the general Casino game frontend layout and functionality. Buttons and displays that are common to any casino game
 * are created here. This class communicates with the controller to manipulate the backend
 * @author Connor Penny
 */

//This will need to implement an interface to give restricted access of its public methods to each game
public class GameTable {
    private static final String BANKROLL_ID = "bankrollDisplay";
    private static final String BET_TOTAL_ID = "betTotalDisplay";
    private static final String GAME_OVER_ID = "GameOverMessage";
    private static final String PLAYER_ID = "playerDisplay";
    private static final String RESOURCES_PACKAGE = "resources.GameTableProperties.";
    private static final String GAME_BUTTON_RESOURCES = "GameTableButtons";
    private static final String BET_BUTTON_RESOURCES = "BetButtons";
    private static final String DISPLAY_RESOURCES = "GameTableDisplays";
    private static final String LAYOUT_RESOURCES = "GameTableLayout";
    private static final String ADMIN_RESOURCES = "AdminButtons";
    private static final String GAME_MODE_BUTTON= "Set Game Mode";
    private static final int V_SPACING= 150;
    private static final int H_SPACING= 100;
    private static final int BOX_SPACING=20;
    private static final String DELIMITER = ",";
    private static final String RESOURCES_EXCEPTION= "Improperly Configured GameTable Layout File";
    private static final String RESOURCES_ERROR_DISPLAY = "Internal Error: Please exit the program";
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
    private Text playerDisplay;
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
        gameRoot.setHgap(H_SPACING);
        gameRoot.setVgap(V_SPACING);
        gameRoot.setAlignment(Pos.CENTER);
        gameRoot.setPadding(new Insets(1,1,1,1));
        gameRoot.setId("gameDisplay");
        betButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + BET_BUTTON_RESOURCES);
        gameplayButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + GAME_BUTTON_RESOURCES, myLocale);
        gameDisplayResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + DISPLAY_RESOURCES, myLocale);
        layoutResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + LAYOUT_RESOURCES, myLocale);
        adminButtonResources = ResourceBundle.getBundle(RESOURCES_PACKAGE + ADMIN_RESOURCES, myLocale);

        adminButtons = new HBox(BOX_SPACING);
        betElements = new VBox(BOX_SPACING);
        gamePlayElements = new VBox(BOX_SPACING);

        adminButtons.getChildren().addAll(createGamePlayButtons(this, adminButtonResources));
        gamePlayElements.getChildren().addAll(createGamePlayButtons(myController, gameplayButtonResources));
        gamePlayElements.getChildren().add(makeGameModeButton());

        bankrollDisplay = new Text(gameDisplayResources.getString(BANKROLL_ID) + player.getMyBankRoll());
        bankrollDisplay.setId(BANKROLL_ID);

        betTotalDisplay = new Text(gameDisplayResources.getString(BET_TOTAL_ID));
        betTotalDisplay.setId(BET_TOTAL_ID);

        playerDisplay = new Text(gameDisplayResources.getString(PLAYER_ID) + myPlayer.getName());
        playerDisplay.setId(PLAYER_ID);

        betElements.getChildren().addAll(betTotalDisplay, createBetButtons());

        displaySceneElements();
        myScene.setRoot(gameRoot);
    }

    private void displaySceneElements() {
        for(String element : layoutResources.keySet()) {
            try {
                GamePlayElementsParser parser = new GamePlayElementsParser();
                Node x = parser.getGamePlayElementsAsField(this, element);
                String[] coords = layoutResources.getString(element).split(DELIMITER);
                gameRoot.add(x, Integer.parseInt(coords[0]),Integer.parseInt(coords[1]));
            }
            catch (Exception e) {
                //createAlert(RESOURCES_ERROR_DISPLAY);
                throw new ReflectionException(RESOURCES_EXCEPTION, e);
            }

        }
    }

    private <T> List<Button> createGamePlayButtons(T element, ResourceBundle resources) {
        try {
            List<Button> gamePlayButtons = new ArrayList<>();
            for (String key : resources.keySet()) {
                String[] buttonProperties = resources.getString(key).split(DELIMITER);
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
        catch (Exception e) {
            createAlert(RESOURCES_ERROR_DISPLAY);
            throw new ReflectionException(RESOURCES_EXCEPTION, e);
        }
    }

    private HBox createBetButtons() {
        try {
            HBox betButtons = new HBox(BOX_SPACING);

            for (String bet : betButtonResources.keySet()) {
                Button betButton = new Button(betButtonResources.getString(bet));
                betButton.setId(betButtonResources.getString(bet));
                betButton.setOnAction(e -> myGameBoard.performBetAction(Integer.parseInt(bet), myController));
                betButtons.getChildren().add(betButton);
            }
            return betButtons;
        }
        catch (Exception e) {
            createAlert(RESOURCES_ERROR_DISPLAY);
            throw new ResourcesException(RESOURCES_EXCEPTION, e);
        }
    }

    private Button makeGameModeButton() {
        Button gameModeButton = new Button(GAME_MODE_BUTTON);
        gameModeButton.setOnAction(e -> setGameMode());
        return gameModeButton;
    }

    private void setGameMode() {
        if(myGameBoard.getGameMode() != null) {
            myController.updateGameParameters(myGameBoard.getGameMode());
        }
    }
    /***
     * Returns to the main menu screen and starts a new game
     * This method is internal, but it is public due to the nature of the reflection process implemented
     */
    public void Quit() {
        gameRoot.getChildren().clear();

        if(gameOverWindow != null){
            gameOverWindow.close();
        }
        new Menu(myScene);

    }

    /***
     * Returns to the main menu screen but does not change the money in the player's bankroll
     * This method is internal, but it is public due to the nature of the reflection process implemented
     */
    public void ChangeGame() {
        gameRoot.getChildren().clear();
        new Menu(myScene, myPlayer);
    }

    private void initiateGame(Player player, String game) {
        gameDisplay = myGameBoard.drawGame();
        myController.setGameTable(this, myGameBoard);
        myController.startGame(game, player);

    }

    /***
     * Updates the bankroll based on information passed by Controller
     * @param bankroll Integer new bankroll amount
     */
    public void updateBankRoll(int bankroll) {
        bankrollDisplay.setText(gameDisplayResources.getString(BANKROLL_ID) + bankroll);
    }

    //This should be extracted to its own class

    /***
     * Displays a game over window
     */
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

    /***
     * Update bet total amount displayed to user
     * @param amount bet total amount
     */
    public void updateBetTotal(int amount) {betTotalDisplay.setText(gameDisplayResources.getString(BET_TOTAL_ID) + amount);}

    /***
     * Displays an error message to the user
     * @param errorMessage error message to be displayed to the user
     */
    public void createAlert(String errorMessage) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(errorMessage);
        error.show();
    }

    //protected void setLayoutResources(String path) {
        //layoutResources = ResourceBundle.getBundle(path);
    //}


}
