package ooga.view;

import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import ooga.model.Player;
import ooga.view.data.MenuGameParser;
import ooga.view.data.ResourcesException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * A main menu screen that the user sees upon launching the program. Displays the proper game buttons for the user to choose from, styling choices, and
 * a load game choices.
 * @author Connor Penny
 */

public class Menu {
    private static final String RESOURCES = "resources/MenuProperties/";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    private static final String DEFAULT_STYLE = "default";
    private static final String GAME_RESOURCES_FILE = "MenuGames";
    private static final String STARTING_AMOUNT = "StartingAmount";
    private static final String MENU_ID = "menuRoot";
    private static final String STYLING_RESOURCES_FILE = "Styles";
    private static final Locale LANGUAGE_LOCALE = new Locale("en");
    private static final String INTERNAL_ERROR = "Internal Error: Please exit the program";
    private static final String GAMES_LIST = "Games";
    private static final String H_SPACING = "HorizontalSpacing";
    private static final String V_SPACING = "VerticalSpacing";
    private static final String DELIMITER = ",";


    private GridPane menuRoot;
    private SceneChanger myScene;
    private ResourceBundle myResources;
    private ResourceBundle myStyles;
    private ChoiceBox<String> loadGame;
    private Player myPlayer;

    /***
     * Creates a new menu with a new player at a new starting amount, effectively restarting the game
     * @param scene The scene to add elements to
     */
    public Menu(SceneChanger scene) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + GAME_RESOURCES_FILE);
        myPlayer = new Player(Integer.parseInt(myResources.getString(STARTING_AMOUNT)), null);
        initialize(scene);
    }

    /***
     * Creates a new menu with an existing player
     * @param scene The scene to add elements to
     * @param player The player with data about bankroll amount
     */
    public Menu(SceneChanger scene, Player player) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + GAME_RESOURCES_FILE);
        myPlayer = player;
        initialize(scene);
        loadGame.setValue(null);
        loadGame.setVisible(false);

    }

    private void initialize(SceneChanger scene) {
        myStyles = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + STYLING_RESOURCES_FILE);
        myScene = scene;
        menuRoot = new GridPane();
        menuRoot.setId(MENU_ID);
        ChoiceBox styleChoice = createStyleDropdown();
        styleChoice.setValue(DEFAULT_STYLE);
        createLoadGameDropdown();
        menuRoot.add(loadGame, 0, 0);
        menuRoot.add(createStyleDropdown(), 0, 1);
        scene.setRoot(menuRoot);
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setHgap(Double.parseDouble(myResources.getString(H_SPACING)));
        menuRoot.setVgap(Double.parseDouble(myResources.getString(V_SPACING)));
        makeGameButtons(getGameList());
    }


    private void makeGameButtons(List<String> games) {
        int colInd = 0;
        int rowInd = 2;
        for(String game : games) {
            try{
                MenuGameParser parser = new MenuGameParser(DEFAULT_RESOURCE_PACKAGE, LANGUAGE_LOCALE);
                Button gameButton = parser.makeButton(game);
                gameButton.setOnAction(e -> setUpGame(parser));
                menuRoot.add(gameButton, colInd, rowInd);
            } catch (Exception e) {
                createAlert();
                throw e;
            }


            if(colInd < 4) { colInd += 1;}
            else {
                colInd = 0;
                rowInd += 1;
            }
        }
    }

    private List<String> getGameList() {
        String games = myResources.getString(GAMES_LIST);
        List<String> gamesList = Arrays.asList(games.split(DELIMITER));
        return gamesList;
    }



    private void setUpGame(MenuGameParser parser) { //should take argument that tells it which game it should set up
        menuRoot.getChildren().clear();
        try {
            GameBoard gameBoard = parser.parseButtonAction();
            String gameTitle = parser.parseGameName();
            myPlayer.setMyCurrentGame(gameTitle);
            new GameTable(myScene, gameBoard, myPlayer, gameTitle, LANGUAGE_LOCALE);
        }

        catch (Exception e){
            createAlert();
            throw e;
        }

    }

    private ChoiceBox<String> createStyleDropdown() {
        ChoiceBox<String> styles = new ChoiceBox();
        styles.getItems().addAll(myStyles.keySet());
        styles.setOnAction(e -> applyNewStyle(styles.getValue()));
        return styles;
    }

    private void applyNewStyle(String style) {
        myScene.getStylesheets().clear();
        myScene.getStylesheets().add(myStyles.getString(style));

    }

    private ChoiceBox<String> createLoadGameDropdown() {
        try {
            loadGame = new ChoiceBox<>();
            loadGame.getItems().addAll(myPlayer.getPlayers());
            loadGame.setOnAction(e -> {
                try {
                    myPlayer.setPlayer(loadGame.getValue());
                } catch (Exception ex) {
                    createAlert();
                }
            });
            return loadGame;
        } catch(Exception e) {
            createAlert();
            throw new ResourcesException("Invalid Resource Configuration", e);

        }
    }

    private void createAlert() {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setContentText(INTERNAL_ERROR);
        a.show();
    }
}
