package ooga.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.Player;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RouletteTest extends DukeApplicationTest {
    private GameScene testScene;
    private GameTable gameTable;
    private GameBoard gameBoard;
    private GridPane gameDisplay;
    private Text bankrollDisplay;
    private Button betButton1;
    private Button betButton5;
    private Button betButton10;
    private Text betTotalDisplay;
    private Button clearBetButton;
    private Button playRoundButton;
    private Button mainMenuButton;
    private ChoiceBox numberChoice;
    private ChoiceBox colorChoice;
    private ChoiceBox parityChoice;

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        testScene = new GameScene(root, 1000, 800);

        Player myPlayer = new Player(10, null);
        gameBoard = new RouletteBoard(ResourceBundle.getBundle("resources.RouletteGameModes.american"));

        gameTable = new GameTable(testScene, gameBoard, myPlayer, "ROULETTE", new Locale("en"));

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
        numberChoice = lookup("#number").query();
        colorChoice = lookup("#color").query();
        parityChoice = lookup("#parity").query();
    }

    @Test
    void testBetOnNumber() {
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());

        select(numberChoice, "0");
        clickOn(betButton1);

        assertEquals("Total Bet: $1", betTotalDisplay.getText());
        assertEquals("BankRoll: $9", bankrollDisplay.getText());
    }
    @Test
    void testBetOnColor() {
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());

        select(colorChoice, "red");
        clickOn(betButton1);

        assertEquals("Total Bet: $1", betTotalDisplay.getText());
        assertEquals("BankRoll: $9", bankrollDisplay.getText());
    }
    @Test
    void testBetOnParity() {
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());

        select(colorChoice, "even");
        clickOn(betButton1);

        assertEquals("Total Bet: $1", betTotalDisplay.getText());
        assertEquals("BankRoll: $9", bankrollDisplay.getText());
    }
    @Test
    void testBetNoSelection() {
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());

        select(colorChoice, "None");
        select(numberChoice, "None");
        select(parityChoice, "None");
        clickOn(betButton1);

        assertEquals("Total Bet: $0", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());
    }
    @Test
    void testBetCombo() {
        assertEquals("Total Bet: $", betTotalDisplay.getText());
        assertEquals("BankRoll: $10", bankrollDisplay.getText());

        select(colorChoice, "red");
        select(numberChoice, "36");
        select(parityChoice, "even");
        clickOn(betButton1);

        assertEquals("Total Bet: $3", betTotalDisplay.getText());
        assertEquals("BankRoll: $7", bankrollDisplay.getText());
    }
}