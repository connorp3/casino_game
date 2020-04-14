package ooga.view;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
    private Text myOutcome;

    public RouletteBoard(ResourceBundle gameMode) {
        myOutcome = new Text();
        myGameMode = gameMode;

        String outcomeNum = "36";
        String outcomeColor = gameMode.getString(outcomeNum);

        myOutcome.setText(outcomeNum);
        myOutcome.setFill(wheelColors.get(outcomeColor));
    }
    @Override
    public Node drawGame() {
        return myOutcome;
    }

    @Override
    public void showOutcome(List<Integer> outcome) {
       // myOutcome.setText(outcome.getNum());
       // myOutcome.setFill(wheelColors.get(outcome.getColor()));
    }
}
