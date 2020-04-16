package ooga.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.controller.Controller;
import ooga.model.BetType;

import java.lang.reflect.Method;
import java.util.*;

public class RouletteBoard implements GameBoard {
    private static final Map<String, Color> wheelColors;
    static {
        Map<String, Color> aMap = new HashMap<>();
        aMap.put("red", Color.RED);
        aMap.put("black", Color.BLACK);
        aMap.put("green", Color.GREEN);
        wheelColors = Collections.unmodifiableMap(aMap);
    }
    private ResourceBundle myGameMode;
    private static final ResourceBundle myBetTypes = ResourceBundle.getBundle("resources.RouletteGameModes.betTypes");
    private Text myOutcome;
    private ChoiceBox<String> betOptions;
    private VBox gameDisplay;

    public RouletteBoard(ResourceBundle gameMode) {
        myOutcome = new Text();
        myGameMode = gameMode;
        betOptions = createBetOptions();
        gameDisplay = new VBox(20);

        String outcomeNum = "36";
        String outcomeColor = gameMode.getString(outcomeNum);

        myOutcome.setText(outcomeNum);
        myOutcome.setFill(wheelColors.get(outcomeColor));

        gameDisplay.getChildren().addAll(myOutcome, betOptions);
    }
    @Override
    public Node drawGame() {
        return gameDisplay;
    }

    @Override
    public void showOutcome(List<Integer> outcome) {
       // myOutcome.setText(outcome.getNum());
       // myOutcome.setFill(wheelColors.get(outcome.getColor()));
        myOutcome.setText("34");
        myOutcome.setFill(Color.RED);
    }

    @Override
    public HBox createBetButtons(Controller myController) {
        List<String> betLabels = new ArrayList();
        betLabels.add("$1");
        betLabels.add("$5");
        betLabels.add("$10");
        betLabels.add("$20");

        HBox betButtons = new HBox(5);
        for(String bet : betLabels) {
            Button betButton = new Button(bet);
            betButton.setId(bet);
            betButton.setOnAction(e -> myController.placeBet(1, betOptions.getValue()), myBetTypes.getString(betOptions.getValue()));
            betButtons.getChildren().add(betButton);
        }
        return betButtons;
    }

    private ChoiceBox<String> createBetOptions() {
        ChoiceBox<String> betOptions = new ChoiceBox();
        betOptions.getItems().addAll(myBetTypes.keySet());
        /*for(BetType bets : EnumSet.allOf(BetType.class)) {

            betOptions.getItems().addAll(bets.getValues());
            betOptions.getChildren().add(betType);
        }
         */
        return betOptions;
    }

}
