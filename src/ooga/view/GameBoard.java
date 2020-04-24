package ooga.view;

import javafx.scene.Node;
import ooga.controller.BetController;

import java.util.List;

/***
 * An API for the frontend display of any game. Must be implemented to create a new game
 * @author Connor Penny
 */
public interface GameBoard {

    /***
     * Creates the initial display of the game for the user to see before they start betting
     * @return a node representing the game display
     */
    Node drawGame();

    /***
     * Updates the display of the game ofr the user after they play a round
     * @param outcome A list of integers that will be interpreted by the frontend to display the proper outcome
     */
    void showOutcome(List<Integer> outcome);

    /***
     * Passes the proper bet action to the controller based on bet settings set by the user
     * Uses interface of controller to limit dependencies
     * @param amount amount of the bet
     * @param myController interface of controller only allowing access to placeBet
     */
    void performBetAction(int amount, BetController myController);

    /***
     * Returns the game mode selections by the user to be parsed by the backend
     * @return a list of the game mode options the user selects
     */
    List<String> getGameMode();
}
