package ooga.view.data;


import javafx.scene.Group;
import javafx.stage.Stage;
import ooga.controller.Controller;
import ooga.view.GameScene;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

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
