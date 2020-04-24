package ooga.view.data;

import javafx.scene.control.Button;
import ooga.controller.Controller;
import ooga.view.GameBoard;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.ResourceBundle;

public class MenuGameParser {
    private static String myResourcePath;
    private static Locale myLanguage;
    private static final String LABEL = "ButtonLabel";
    private static final String TITLE = "GameTitle";
    private static final String GAME_CLASS = "GameClass";
    private static final String DEFAULT_GAME_MODE = "DefaultGameMode";
    private static final String REFLECTION_EXCEPTION_MESSAGE = "Improperly Configured Game Reflection File";
    private static final String RESOURCES_EXCEPTION_MESSAGE = "Game Reflection File/Elements Invalid";
    private ResourceBundle gameResources;

    public MenuGameParser(String resourcePath, Locale language) {
        myResourcePath = resourcePath;
        myLanguage = language;

    }

    public Button makeButton(String game) {
        try {
            gameResources = ResourceBundle.getBundle(myResourcePath + game, myLanguage);//This will need to access a properties file and determine the proper settings for each game button
            Button GameButton = new Button(gameResources.getString(LABEL));
            GameButton.setId(gameResources.getString(TITLE));
            return GameButton;
        }
        catch(Exception e) {
            throw new ResourcesException(RESOURCES_EXCEPTION_MESSAGE, e);
        }
    }

    public GameBoard parseButtonAction() {
        try {
            String game = gameResources.getString(GAME_CLASS);
            Class gameClass = Class.forName(game);
            Constructor gameConstructor = gameClass.getConstructor(ResourceBundle.class);
            ResourceBundle gameMode = ResourceBundle.getBundle(gameResources.getString(DEFAULT_GAME_MODE));
            GameBoard gameBoard = (GameBoard) gameConstructor.newInstance((gameMode));
            return gameBoard;
        }
        catch(Exception e) {
            throw new ReflectionException(REFLECTION_EXCEPTION_MESSAGE, e);
        }
    }

    public String parseGameName() {

        try {
            return gameResources.getString(TITLE);
        } catch(Exception e) {
            throw new ResourcesException(RESOURCES_EXCEPTION_MESSAGE);
        }
    }




}
