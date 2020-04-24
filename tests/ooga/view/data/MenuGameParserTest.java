package ooga.view.data;


import javafx.scene.Group;
import javafx.stage.Stage;
import ooga.view.GameScene;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MenuGameParserTest extends DukeApplicationTest {
    private GameScene testScene;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        testScene = new GameScene(root, 500, 500);
        stage.setScene(testScene);

        stage.show();

    }

    @Test
    public void TestNewButtonLabels() {
        MenuGameParser menuGameParser = new MenuGameParser("resources.MenuProperties.tests.", new Locale("en"));
        assertEquals(menuGameParser.makeButton("SlotMachineReflectionTest1").getText(), "EFGH");
        assertEquals(menuGameParser.makeButton("RouletteReflectionTest1").getText(), "ABCD");

    }

    @Test
    public void TestReflectionException() {
        MenuGameParser menuGameParser = new MenuGameParser("resources.MenuProperties.tests.", new Locale("en"));
        menuGameParser.makeButton("SlotMachineReflectionTest2");
        assertThrows(ReflectionException.class, () -> menuGameParser.parseButtonAction());
    }

    @Test
    public void TestReflectionException2() {
        MenuGameParser menuGameParser = new MenuGameParser("resources.MenuProperties.tests.", new Locale("en"));
        menuGameParser.makeButton("RouletteReflectionTest2");
        assertThrows(ReflectionException.class, () -> menuGameParser.parseButtonAction());
    }

    @Test
    public void TestResourceException() {
        MenuGameParser menuGameParser = new MenuGameParser("resources.MenuProperties.tests.", new Locale("en"));
        assertThrows(ResourcesException.class, () -> menuGameParser.makeButton("RouletteReflectionTest3"));
    }
}

