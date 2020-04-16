package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

import java.util.List;

public interface GameBoard {

    Node drawGame();
    void showOutcome(List<Integer> outcome);
    HBox createBetButtons();

}
