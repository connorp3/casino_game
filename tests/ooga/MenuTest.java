package ooga;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest extends DukeApplicationTest {
    private GameScene testScene;
    private Button mySlotMachineButton;
    private Button myRouletteButton;
    private Button myBlackJackButton;
    private GridPane gameButtons;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        testScene = new GameScene(root, 500, 500, Color.GREEN);
        new Menu(testScene);
        stage.setScene(testScene);

        stage.show();

        gameButtons = lookup("#menuRoot").query();
        mySlotMachineButton = lookup("#SlotMachine").query();
        myRouletteButton = lookup("#Roulette").query();
        myBlackJackButton = lookup("#BlackJack").query();

    }

    @Test
    public void TestSlotMachineButtonPress() {
        clickOn(mySlotMachineButton);
        assertTrue(gameButtons.getChildren().isEmpty());

    }
}