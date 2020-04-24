package ooga.view;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ooga.model.Player;

import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SlotMachineTest extends DukeApplicationTest {
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

    @Override
    public void start(Stage stage) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
        Group root = new Group();
        testScene = new GameScene(root, 1000, 800);

        Player myPlayer = new Player(10, null);
        gameBoard = new SlotMachineBoard(ResourceBundle.getBundle("resources.SlotMachineGameModes.default_view"));

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
    void TestSlotMachineOutcomeUpdate() {
        Text wheel1 = lookup("#wheel1").query();
        Text wheel2 = lookup("#wheel2").query();
        Text wheel3 = lookup("#wheel3").query();

        assertEquals(wheel1.getText(), "0");
        assertEquals(wheel2.getText(), "0");
        assertEquals(wheel3.getText(), "0");

        clickOn(betButton1);
        clickOn(playRoundButton);

        String[] outcomes = new String[] {"0", "1", "2"};
        List<String> outcomesList = Arrays.asList(outcomes);
        assertTrue(outcomesList.contains(wheel1.getText()));
        assertTrue(outcomesList.contains(wheel2.getText()));
        assertTrue(outcomesList.contains(wheel3.getText()));

    }


}