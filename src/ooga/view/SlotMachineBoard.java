package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.*;

public class SlotMachineBoard implements GameBoard {
    private HBox myWheels;
    private List<Text> myOutcome;
    private ResourceBundle myGameMode;
    public SlotMachineBoard(ResourceBundle gameMode) {
        myGameMode = gameMode;
        myWheels = new HBox();
        myWheels.setSpacing(20);

        int numReels = Integer.parseInt(myGameMode.getString("NumReels"));
        myOutcome = new ArrayList<Text>();

        for(int x =1; x<=numReels; x++) {
            myOutcome.add(new Text(myGameMode.getString("0")));
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
        for(int currWheel : outcome) {
            myWheels.getChildren().add(new Text(myGameMode.getString(Integer.toString(currWheel))));
        }


    }

}
