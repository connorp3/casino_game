package ooga.view.data;

import javafx.scene.Node;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class GamePlayElementsParser {
    private static final String REFLECTION_EXCEPTION_MESSAGE = "Improperly Configured GameTable Properties File";
    public GamePlayElementsParser() {
    }

    public <T> void setGamePlayButtonAction(T element, String buttonMethod) {
        try {
            Method m = element.getClass().getMethod(buttonMethod);
            m.invoke(element);
        } catch (Exception e) {
            throw new ReflectionException(REFLECTION_EXCEPTION_MESSAGE, e);
        }
    }

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
