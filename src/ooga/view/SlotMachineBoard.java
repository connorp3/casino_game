package ooga.view;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.controller.BetController;
import ooga.controller.Controller;
import ooga.view.data.LabelParser;

import java.awt.*;
import java.util.*;
import java.util.List;

/***
 * Frontend display for Slot Machine game. Allows user to set different game modes for variation of game
 * @author Connor Penny
 */
public class SlotMachineBoard implements GameBoard {

    private String WHEEL_ID = "wheel";
    private String WHEELS_NUMBER = "NumReels";
    private String SYMBOLS_NUMBER = "NumSymbols";
    private static final String DEFAULT_WHEEL_VALUE = "0";
    private static final int DEFAULT_SPACING = 20;
    private static final String max_Wheels = "MaxReels";
    private static final String max_Symbols = "MaxSymbols";
    private HBox myWheels;
    private VBox myDisplay;
    private List<Node> myOutcome;
    private ChoiceBox<String> numWheels;
    private ChoiceBox<String> numSymbols;
    private ResourceBundle myGameMode;
    public SlotMachineBoard(ResourceBundle gameMode) {
        myGameMode = gameMode;
        myDisplay = new VBox();
        myDisplay.setSpacing(DEFAULT_SPACING);
        myWheels = new HBox();
        myWheels.setSpacing(DEFAULT_SPACING);

        int numReels = Integer.parseInt(myGameMode.getString(WHEELS_NUMBER));
        myOutcome = new ArrayList<Node>();

        for(int x =1; x<=numReels; x++) {
            LabelParser label = new LabelParser();
            Node wheel = label.parseLabel(myGameMode.getString(DEFAULT_WHEEL_VALUE));
            wheel.setId(WHEEL_ID + x);
            myOutcome.add(wheel);
        }
        myDisplay.getChildren().add(myWheels);
        createGameModeOptions();

    }

    private void createGameModeOptions() {
        numWheels = new ChoiceBox();
        setGameModeOptions(numWheels, Integer.parseInt(myGameMode.getString(max_Wheels)));
        numWheels.setValue(myGameMode.getString(WHEELS_NUMBER));
        numSymbols = new ChoiceBox();
        setGameModeOptions(numSymbols, Integer.parseInt(myGameMode.getString(max_Symbols)));
        numSymbols.setValue(myGameMode.getString(SYMBOLS_NUMBER));
        myDisplay.getChildren().addAll(numSymbols, numWheels);
    }

    @Override
    public List<String> getGameMode() {
        List<String> parameters = new ArrayList();
        parameters.add(numSymbols.getValue());
        parameters.add(numWheels.getValue());
        return parameters;
    }
    private void setGameModeOptions(ChoiceBox<String> options, int max) {
        for(int option = 1; option <= max; option++) {
            options.getItems().add(Integer.toString(option));
        }
    }
    @Override
    public Node drawGame() {
        for(Node wheel : myOutcome) {
            myWheels.getChildren().add(wheel);
        }
        return myDisplay;
    }

    @Override
    public void showOutcome(List<Integer> outcome) {
        myWheels.getChildren().clear();
        int wheelNum = 1;
        for (int currWheel : outcome) {
            LabelParser label = new LabelParser();
            Node wheel = label.parseLabel(myGameMode.getString(Integer.toString(currWheel)));
            wheel.setId(WHEEL_ID + wheelNum);
            myWheels.getChildren().add(wheel);
            wheelNum++;
        }
    }

    @Override
    public void performBetAction(int amount, BetController myController) {
        myController.placeBet(amount, null);
    }


}
