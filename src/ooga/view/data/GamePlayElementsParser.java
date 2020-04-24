package ooga.view.data;

import javafx.scene.Node;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***
 * This class is used to apply reflection to the elements of GameTable such as the different gameplay buttons and game administrative buttons.
 * This class abstracts away any reflection necessary in GameTable to throw ReflectionExceptions and improve readability.
 * @author Connor Penny
 */

public class GamePlayElementsParser {
    private static final String REFLECTION_EXCEPTION_MESSAGE = "Improperly Configured GameTable Properties File";
    public GamePlayElementsParser() {
    }

    /***
     * Invokes the proper method on the proper object given what it is passed
     * This is used when setting a button's action and parameters are read from a properties file
     * Throws reflection exception
     * @param element The object the method must be called on
     * @param buttonMethod String form of method to call on object
     * @param <T> Generic to allow any object to be passed to it
     */
    public <T> void setGamePlayButtonAction(T element, String buttonMethod) {
        try {
            Method m = element.getClass().getMethod(buttonMethod);
            m.invoke(element);
        } catch (Exception e) {
            throw new ReflectionException(REFLECTION_EXCEPTION_MESSAGE, e);
        }
    }

    /***
     * This method sets an instance variable of an object of a class to a field
     * Used by GameTable to create its button instance variables driven by data
     * Throws reflection exception
     * @param element the object containing the instance variable being set
     * @param button The node the instance variable is being set to
     * @param buttonInstanceVariable The string of the instance variable being set
     * @param <T> Generic to allow any object to have instance variable set
     */
    public <T> void setGamePlayButtonToField(T element, Node button, String buttonInstanceVariable) {
        try {
            Field f = element.getClass().getDeclaredField(buttonInstanceVariable);
            f.setAccessible(true);
            f.set(element, button);
            f.setAccessible(false);
        } catch (Exception e) {
            throw new ReflectionException(REFLECTION_EXCEPTION_MESSAGE, e);
            }
    }

    /***
     * Returns the proper instance variable of a given object
     * Used when displaying the elements of GameTable in a certain layout driven by data
     * @param element the object containing the instance variable being set
     * @param field the string of the instance variable being accessed and returned
     * @param <T> a generic to allow any object to be passed in
     * @return the instance variable accessed from the given object
     */
    public <T> Node getGamePlayElementsAsField(T element, String field) {
        try {
            Field sceneElement = element.getClass().getDeclaredField(field);
            sceneElement.setAccessible(true);
            Object x = sceneElement.get(element);
            sceneElement.setAccessible(false);
            return (Node) x;
        }
        catch(Exception e) {
            throw new ReflectionException(REFLECTION_EXCEPTION_MESSAGE, e);
        }
    }
}
