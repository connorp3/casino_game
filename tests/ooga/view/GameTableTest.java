package ooga.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.Game;
import ooga.model.Player;
import ooga.view.GameScene;
import ooga.view.GameTable;
import ooga.view.SlotMachineBoard;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class GameTableTest extends DukeApplicationTest {
    private GameScene testScene;
    private GridPane gameDisplay;
    private Text bankrollDisplay;
    private Button betButton;
    private Text betTotalDisplay;
    private Button clearBetButton;
    private Button playRoundButton;
    private Button mainMenuButton;
    private GameTable gameTable;
    @Override
    public void start(Stage stage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Group root = new Group();
        testScene = new GameScene(root, 500, 500);

        Player myPlayer = new Player(10, null);
        SlotMachineBoard gameBoard = new SlotMachineBoard(ResourceBundle.getBundle("src.resources.SlotMachineGameModes.default"));

        gameTable = new GameTable(testScene, gameBoard, myPlayer);

        stage.setScene(testScene);

        stage.show();

        gameDisplay = lookup("#gameDisplay").query();

        bankrollDisplay = lookup("#bankrollDisplay").query();
        betButton = lookup("#betButton").query();
        betTotalDisplay = lookup("#betTotalDisplay").query();
        clearBetButton = lookup("#clearBet").query();
        playRoundButton = lookup("#playRound").query();
        mainMenuButton = lookup("#MainMenu").query();
    }

    @Test
    void testBetButton() {
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
        assertEquals("Total Bet: $0", betTotalDisplay.getText());
        clickOn(betButton);

        assertEquals("BankRoll: $9", bankrollDisplay.getText());
        assertEquals("Total Bet: $1", betTotalDisplay.getText());

    }

    @Test
    void testPlayRoundButton() {
        clickOn(betButton);
        assertEquals("Total Bet: $1", betTotalDisplay.getText());

        clickOn(playRoundButton);
        assertEquals("Total Bet: $0", betTotalDisplay.getText());

    }

    @Test
    void testMainMenu() {
        Button mainMenuButton = lookup("#MainMenu").query();
        clickOn(mainMenuButton);
        assertTrue(gameDisplay.getChildren().isEmpty());

        lookup("#SlotMachine").query();
        lookup("#Roulette").query();
        lookup("#BlackJack").query();
    }


    @Test
    void testDisplayGameOver() {
        for(int x = 0; x < 10; x++) {
            clickOn(betButton);
        }
        clickOn(playRoundButton);
        VBox gameOverWindow = lookup("#GameOverWindow").query();
        assertEquals(2, gameOverWindow.getChildren().size());
        Text gameOverMessage = lookup("#GameOverMessage").query();
        assertEquals("Game Over: You ran out of funds", gameOverMessage.getText());
    }

    @Test
    void testClearBetButton() {
        for(int x = 0; x < 5; x++) {
            clickOn(betButton);
        }
        assertEquals("Total Bet: $5", betTotalDisplay.getText());
        assertEquals("BankRoll: $5", bankrollDisplay.getText());

        clickOn(clearBetButton);
        assertEquals("Total Bet: $0", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
    }
}