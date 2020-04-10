package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import ooga.model.Player;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Menu {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String RESOURCES_FILE = "MenuGames";

    private GridPane menuRoot;
    private SceneChanger myScene;
    private ResourceBundle myResources;
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
        myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + RESOURCES_FILE);
        myScene = scene;
        menuRoot = new GridPane();
        menuRoot.setId("menuRoot");
        menuRoot.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scene.setRoot(menuRoot);
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setHgap(Double.parseDouble(myResources.getString("HorizontalSpacing")));
        menuRoot.setVgap(Double.parseDouble(myResources.getString("VerticalSpacing")));
        makeGameButtons(parseGameResource());
        //access resource bundle
    }


    private void makeGameButtons(List<String> games) {
        int colInd = 0;
        int rowInd = 0;
        for(String game : games) {
            menuRoot.add(makeButton(game), colInd, rowInd);
            if(colInd < 4) { colInd += 1;}
            colInd = 0;
            rowInd += 1;
        }//This will eventually (hopefully) become a loop that adds buttons for each game
    }

    private List<String> parseGameResource() {
        String games = myResources.getString("Games");
        List<String> gamesList = Arrays.asList(games.split(","));
        return gamesList;
    }

    private Button makeButton(String game) { //This will need to access a properties file and determine the proper settings for each game button
        Button GameButton = new Button(game);
        GameButton.setOnAction(event -> setUpGame(game));
        GameButton.setId(game);
        System.out.println(game);
        return GameButton;
    }

    private void setUpGame(String game) { //should take argument that tells it which game it should set up
        menuRoot.getChildren().clear();
        SlotMachineBoard gameBoard = new SlotMachineBoard();
        myPlayer.setMyCurrentGame(game);
        new GameTable(myScene, gameBoard, myPlayer);


    }
}
