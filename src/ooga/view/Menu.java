package ooga.view;

import javafx.geometry.Pos;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import ooga.model.Player;
import ooga.view.data.MenuGameParser;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Menu {
    private static final String RESOURCES = "resources/MenuProperties/";
    private static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    private static final String GAME_RESOURCES_FILE = "MenuGames";
    private static final String STYLING_RESOURCES_FILE = "Styles";
    private static final Locale LANGUAGE_LOCALE = new Locale("en");
    private static final String INTERNAL_ERROR = "Internal Error: Please exit the program";


    private GridPane menuRoot;
    private SceneChanger myScene;
    private ResourceBundle myResources;
    private ResourceBundle myStyles;
    private Player myPlayer;

    public Menu(SceneChanger scene) {
        myPlayer = new Player(1000, null);
        initialize(scene);
    }

    public Menu(SceneChanger scene, Player player) {
        myPlayer = player;
        initialize(scene);
    }

    private void initialize(SceneChanger scene) {
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + GAME_RESOURCES_FILE);
        myStyles = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + STYLING_RESOURCES_FILE);
        myScene = scene;
        menuRoot = new GridPane();
        menuRoot.setId("menuRoot");
        ChoiceBox styleChoice = createStyleDropdown();
        styleChoice.setValue("default");
        menuRoot.add(createStyleDropdown(), 0, 0);
        scene.setRoot(menuRoot);
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setHgap(Double.parseDouble(myResources.getString("HorizontalSpacing")));
        menuRoot.setVgap(Double.parseDouble(myResources.getString("VerticalSpacing")));
        makeGameButtons(getGameList());
        //access resource bundle
    }


    private void makeGameButtons(List<String> games) {
        int colInd = 0;
        int rowInd = 1;
        MenuGameParser parser = new MenuGameParser(DEFAULT_RESOURCE_PACKAGE, LANGUAGE_LOCALE);
        for(String game : games) {
            Button gameButton = parser.makeButton(game);
            gameButton.setOnAction(e -> setUpGame(parser));
            menuRoot.add(gameButton, colInd, rowInd);
            if(colInd < 4) { colInd += 1;}
            colInd = 0;
            rowInd += 1;
        }
    }

    private List<String> getGameList() {
        String games = myResources.getString("Games");
        List<String> gamesList = Arrays.asList(games.split(","));
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
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(INTERNAL_ERROR);
            a.show();
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
}
