package ooga.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import ooga.model.Player;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class Menu {
    private static final String RESOURCES = "resources/";
    public static final String DEFAULT_RESOURCE_PACKAGE = RESOURCES.replace("/", ".");
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + RESOURCES;
    public static final String GAME_RESOURCES_FILE = "MenuGames";
    public static final String STYLING_RESOURCES_FILE = "Styles";

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
        int rowInd = 1;
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

    private Button makeButton(String game) {
        ResourceBundle gameResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + game);//This will need to access a properties file and determine the proper settings for each game button
        Button GameButton = new Button(gameResources.getString("ButtonLabel"));
        GameButton.setOnAction(event -> {
            try {
                setUpGame(gameResources);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        GameButton.setId(gameResources.getString("GameTitle"));
        return GameButton;
    }

    private void setUpGame(ResourceBundle gameResources) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException { //should take argument that tells it which game it should set up
        menuRoot.getChildren().clear();

        String game = gameResources.getString("GameClass");
        Class gameClass = Class.forName(game);
        Constructor gameConstructor = gameClass.getConstructor(ResourceBundle.class);
        ResourceBundle gameMode = ResourceBundle.getBundle(gameResources.getString("DefaultGameMode"));
        GameBoard gameBoard = (GameBoard) gameConstructor.newInstance((gameMode));

        String gameTitle = gameResources.getString("GameTitle");
        myPlayer.setMyCurrentGame(gameTitle);
        new GameTable(myScene, gameBoard, myPlayer, gameTitle);
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
