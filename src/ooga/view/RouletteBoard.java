package ooga.view;

import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.controller.BetController;
import java.util.*;

/***
 * A frontend display for the RouletteGame. Displays American roulette by default. Allows user to choose bet outcomes to bet on.
 * @author Connor Penny
 */
public class RouletteBoard implements GameBoard {
    private static final Map<String, Color> wheelColors;
    static {
        Map<String, Color> aMap = new HashMap<>();
        aMap.put("red", Color.RED);
        aMap.put("black", Color.BLACK);
        aMap.put("green", Color.GREEN);
        wheelColors = Collections.unmodifiableMap(aMap);
    }
    private static final String DEFAULT_BET_CHOICE = "None";
    private static final int GAME_DISPLAY_SPACING = 20;
    private static final String DEFAULT_OUTCOME = "36";
    private static final String DELIMITER = ",";
    private ResourceBundle myGameMode;
    private static final ResourceBundle myBetTypes = ResourceBundle.getBundle("resources.RouletteGameModes.betType");
    private Text myOutcome;
    private HBox betOptions;
    private VBox gameDisplay;

    public RouletteBoard(ResourceBundle gameMode) {
        myOutcome = new Text();
        myGameMode = gameMode;
        betOptions = createBetOptions();
        gameDisplay = new VBox(GAME_DISPLAY_SPACING);

        String outcomeColor = gameMode.getString(DEFAULT_OUTCOME);

        myOutcome.setText(DEFAULT_OUTCOME);
        myOutcome.setFill(wheelColors.get(outcomeColor));

        gameDisplay.getChildren().addAll(myOutcome, betOptions);
    }

    @Override
    public Node drawGame() {
        return gameDisplay;
    }

    @Override
    public void showOutcome(List<Integer> outcome) {
        String pocket = Integer.toString(outcome.get(0));
        if(pocket.equals("-1")) {
            myOutcome.setText("00");
        }
        else {myOutcome.setText(pocket);}
        myOutcome.setFill(wheelColors.get(myGameMode.getString(pocket)));
    }

    private HBox createBetOptions() {
        HBox betOptions = new HBox(20);
        for(String key : myBetTypes.keySet()) {
            ChoiceBox<String> betChoice = new ChoiceBox<>();
            betChoice.setId(key);
            betChoice.getItems().add(DEFAULT_BET_CHOICE);
            betChoice.setValue(DEFAULT_BET_CHOICE);
            Label betType = new Label (key);
            betType.setLabelFor(betChoice);
            String[] bets = myBetTypes.getString(key).split(DELIMITER);
            betChoice.getItems().addAll(bets);
            betOptions.getChildren().add(betChoice);
        }
        return betOptions;
    }

    @Override
    public void performBetAction(int amount, BetController myController) {
        for(Node node : betOptions.getChildren()) {
            ChoiceBox<String> betType = (ChoiceBox<String>) node;
            myController.placeBet(amount, betType.getValue());
        }
    }

    @Override
    public List<String> getGameMode() {
        return null;
    }

}
