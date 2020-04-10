package ooga.view;

import javafx.scene.Node;

import java.util.List;

public interface GameBoard {

    Node drawGame();
    void showOutcome(List<Integer> outcome);

}
