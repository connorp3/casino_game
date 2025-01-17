package ooga.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest extends DukeApplicationTest {
    private GameScene testScene;
    private Button mySlotMachineButton;
    private Button myRouletteButton;
    private GridPane gameButtons;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        testScene = new GameScene(root, 500, 500);
        new Menu(testScene);
        stage.setScene(testScene);

        stage.show();

        gameButtons = lookup("#menuRoot").query();
        mySlotMachineButton = lookup("#SLOTS").query();
        myRouletteButton = lookup("#ROULETTE").query();

    }

    @Test
    public void TestSlotMachineButtonPress() {
        clickOn(mySlotMachineButton);
        assertTrue(gameButtons.getChildren().isEmpty());

        lookup("#bankrollDisplay").query();
        lookup("#betTotalDisplay").query();
        lookup("#$1").query();
        lookup("#$5").query();
        lookup("#$10").query();
        lookup("#$20").query();
        lookup("#clearBet").query();
        lookup("#playRound").query();
        lookup("#mainMenu").query();

    }

    @Test
    public void TestRouletteButtonPress() {
        clickOn(myRouletteButton);
        assertTrue(gameButtons.getChildren().isEmpty());

        lookup("#bankrollDisplay").query();
        lookup("#betTotalDisplay").query();
        lookup("#$1").query();
        lookup("#$5").query();
        lookup("#$10").query();
        lookup("#$20").query();
        lookup("#clearBet").query();
        lookup("#playRound").query();
        lookup("#mainMenu").query();

    }
}