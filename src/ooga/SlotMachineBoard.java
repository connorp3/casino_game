package ooga;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SlotMachineBoard implements GameBoard {
    private HBox myWheels;
    private List<Text> myOutcome;
    public SlotMachineBoard() {
        myWheels = new HBox();
        Text wheel1 = new Text("1");
        Text wheel2 = new Text("1");
        Text wheel3 = new Text("1");
        myOutcome = new ArrayList<Text>();
        myOutcome.add(wheel1);
        myOutcome.add(wheel2);
        myOutcome.add(wheel3);

    }
    @Override
    public Node drawGame() {
        int colInd = 0;
        for(Node wheel : myOutcome) {
            myWheels.getChildren().add(wheel);
            colInd++;
        }
        return myWheels;
    }

    @Override
    public void showOutcome(Object outcome) {
        outcome = (List<Text>) outcome;

    }

    @Override
    public void addBet() {
        //do nothing
    }
}
