package ooga.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import ooga.controller.Controller;
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
    private static final ResourceBundle myBetTypes = ResourceBundle.getBundle("resources.RouletteGameModes.betType");
    private Text myOutcome;
    private HBox betOptions;
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
        String pocket = Integer.toString(outcome.get(0));
        myOutcome.setText(pocket);
        myOutcome.setFill(wheelColors.get(myGameMode.getString(pocket)));
    }

    private HBox createBetOptions() {
        HBox betOptions = new HBox(20);
        for(String key : myBetTypes.keySet()) {
            ChoiceBox<String> betChoice = new ChoiceBox<>();
            betChoice.getItems().add("None");
            betChoice.setValue("None");
            String[] bets = myBetTypes.getString(key).split(",");
            betChoice.getItems().addAll(bets);
            betOptions.getChildren().add(betChoice);
        }
        return betOptions;
    }

    @Override
    public void getBetChoices(int amount, Controller myController) {
        for(Node node : betOptions.getChildren()) {
            ChoiceBox<String> betType = (ChoiceBox<String>) node;
            if(!betType.getValue().equals("None"))
            myController.placeBet(amount, betType.getValue());
        }
    }

}
