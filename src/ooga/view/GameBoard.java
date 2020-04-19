package ooga.view;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import ooga.controller.Controller;

import java.util.List;

public interface GameBoard {

    Node drawGame();
    void showOutcome(List<Integer> outcome);
    void performBetAction(int amount, Controller myController);

}
