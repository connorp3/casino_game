package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import ooga.controller.BetController;

import java.util.*;

public class SlotMachineBoard implements GameBoard {
    private HBox myWheels;
    private String WHEEL_ID = "wheel";
    private String WHEELS_NUMBER = "NumReels";
    private static final String DEFAULT_WHEEL_VALUE = "0";
    private static final int DEFAULT_SPACING = 20;
    private List<Node> myOutcome;
    private ResourceBundle myGameMode;
    public SlotMachineBoard(ResourceBundle gameMode) {
        myGameMode = gameMode;
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

    }
    @Override
    public Node drawGame() {
        for(Node wheel : myOutcome) {
            myWheels.getChildren().add(wheel);
        }
        return myWheels;
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
