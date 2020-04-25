package ooga.view.data;

import javafx.scene.control.Button;
import ooga.view.GameBoard;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * This class is used to parse properties files relevant to the Menu class.
 * This class abstracts away data reading and reflection in Menu to throw ReflectionExceptions and ResourceExceptions and improve readability.
 * @author Connor Penny
 */

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

    /***
     * Make a button for a given game
     * @param game the game file for the proper game button to be made
     * @return a game button
     */
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

    /***
     * Uses reflection to create the proper frontend classes and pass the proper data files for a specific game that will be run
     * @return the GameBoard frontend object for the proper game
     */
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

    /***
     * Accesses a the title of the game for a given game resource file
     * Title is passed to backend to set up proper backend game classes
     * @return String game title from resource file
     */
    public String parseGameName() {

        try {
            return gameResources.getString(TITLE);
        } catch(Exception e) {
            throw new ResourcesException(RESOURCES_EXCEPTION_MESSAGE);
        }
    }




}
