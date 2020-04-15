package ooga.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.view.GameScene;
import ooga.view.Menu;
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
        testScene = new GameScene(root, 500, 500);
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

        lookup("#bankrollDisplay").query();
        lookup("#betTotalDisplay").query();
        lookup("#betButton").query();
        lookup("#clearBet").query();
        lookup("#playRound").query();
        lookup("#MainMenu").query();

    }
    @Test
    public void TestBlackJackButtonPress() {
        clickOn(myBlackJackButton);
        assertTrue(gameButtons.getChildren().isEmpty());

        lookup("#bankrollDisplay").query();
        lookup("#betTotalDisplay").query();
        lookup("#betButton").query();
        lookup("#clearBet").query();
        lookup("#playRound").query();
        lookup("#MainMenu").query();

    }
    @Test
    public void TestRouletteButtonPress() {
        clickOn(myRouletteButton);
        assertTrue(gameButtons.getChildren().isEmpty());

        lookup("#bankrollDisplay").query();
        lookup("#betTotalDisplay").query();
        lookup("#betButton").query();
        lookup("#clearBet").query();
        lookup("#playRound").query();
        lookup("#MainMenu").query();

    }
}