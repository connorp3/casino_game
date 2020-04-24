package ooga.view;


import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.Player;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class GameTableTest extends DukeApplicationTest {
    private GameScene testScene;
    private GridPane gameDisplay;
    private Text bankrollDisplay;
    private Button betButton1;
    private Button betButton5;
    private Button betButton10;
    private Text betTotalDisplay;
    private Button clearBetButton;
    private Button playRoundButton;
    private Button mainMenuButton;
    private GameTable gameTable;
    @Override
    public void start(Stage stage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        Group root = new Group();
        testScene = new GameScene(root, 1000, 800);

        Player myPlayer = new Player(10, null);
        SlotMachineBoard gameBoard = new SlotMachineBoard(ResourceBundle.getBundle("resources.SlotMachineGameModes.default_view"));

        gameTable = new GameTable(testScene, gameBoard, myPlayer, "SLOTS", new Locale("en"));

        stage.setScene(testScene);

        stage.show();

        gameDisplay = lookup("#gameDisplay").query();

        bankrollDisplay = lookup("#bankrollDisplay").query();
        betButton1 = lookup("#$1").query();
        betButton5 = lookup("#$5").query();
        betButton10 = lookup("#$10").query();
        betTotalDisplay = lookup("#betTotalDisplay").query();
        clearBetButton = lookup("#clearBet").query();
        playRoundButton = lookup("#playRound").query();
        mainMenuButton = lookup("#mainMenu").query();
    }

    @Test
    void testBetButton1() {
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        clickOn(betButton1);

        assertEquals("BankRoll: $9", bankrollDisplay.getText());
        assertEquals("Total Bet: $1", betTotalDisplay.getText());

    }

    @Test
    void testBetButton5() {
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        clickOn(betButton5);

        assertEquals("BankRoll: $5", bankrollDisplay.getText());
        assertEquals("Total Bet: $5", betTotalDisplay.getText());
    }

    @Test
    void testBetButton10() {
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        clickOn(betButton10);

        assertEquals("BankRoll: $0", bankrollDisplay.getText());
        assertEquals("Total Bet: $10", betTotalDisplay.getText());
    }
    @Test
    void testPlayRoundButton() {
        clickOn(betButton1);
        assertEquals("Total Bet: $1", betTotalDisplay.getText());

        clickOn(playRoundButton);
        assertEquals("Total Bet: $0", betTotalDisplay.getText());

    }

    @Test
    void testMainMenu() {
        clickOn(mainMenuButton);
        assertTrue(gameDisplay.getChildren().isEmpty());

        lookup("#SLOTS").query();
        lookup("#ROULETTE").query();
    }


    @Test
    void testDisplayGameOver() {
        for(int x = 0; x < 10; x++) {
            clickOn(betButton1);
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
            clickOn(betButton1);
        }
        assertEquals("Total Bet: $5", betTotalDisplay.getText());
        assertEquals("BankRoll: $5", bankrollDisplay.getText());

        clickOn(clearBetButton);
        assertEquals("Total Bet: $0", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
    }

    /*@Test
    void testExceptionLayoutResources1() {
        gameTable.setLayoutResources("resources.GameTableProperties.tests.GameTableLayoutTest1");
        assertThrows(ResourcesException.class, () -> gameTable.displaySceneElements());
    }

    @Test
    void testExceptionLayoutResources2() {
        gameTable.setLayoutResources("resources.GameTableProperties.tests.GameTableLayoutTest2");
        assertThrows(ResourcesException.class, () -> gameTable.displaySceneElements());
    }*/
}