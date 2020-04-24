package ooga.view.data;


import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.GameScene;
import ooga.view.Menu;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import javafx.scene.control.Button;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class GamePlayElementsParserTest extends DukeApplicationTest {
    private GameScene testScene;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        testScene = new GameScene(root, 500, 500);
        stage.setScene(testScene);

        stage.show();

    }

    @Test
    public void testSetGamePlayButtonActionException() {
    GamePlayElementsParser parser = new GamePlayElementsParser();
    assertThrows(ReflectionException.class, () -> parser.setGamePlayButtonAction(new Controller(), "notAMethod"));
    }
}
