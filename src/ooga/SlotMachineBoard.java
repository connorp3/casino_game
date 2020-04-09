package ooga;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlotMachineBoard implements GameBoard {
    private HBox myWheels;
    private List<Text> myOutcome;
    private Map<Integer, Node> displaySymbols;
    public SlotMachineBoard() {
        myWheels = new HBox();
        myWheels.setSpacing(20);
        Text wheel1 = new Text("1");
        Text wheel2 = new Text("1");
        Text wheel3 = new Text("1");
        myOutcome = new ArrayList<Text>();
        myOutcome.add(wheel1);
        myOutcome.add(wheel2);
        myOutcome.add(wheel3);

        displaySymbols = new HashMap<Integer, Node>();   //Turn this into a properties file
        for(int x = 0; x<=2; x++) {
            displaySymbols.put(x+1, new Text("1"));
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
        for(int currWheel : outcome) {
            myWheels.getChildren().add(displaySymbols.get(currWheel));
        }


    }

}
