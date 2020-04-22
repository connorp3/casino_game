package ooga.controller;

import ooga.model.*;
import ooga.view.GameBoard;
import ooga.view.GameTable;

import java.util.List;
import java.util.ResourceBundle;

public class Controller implements BetController {

    Game game;
    Player currentPlayer;
    GameTable view;
    GameBoard board;
    boolean roulette;
    boolean slots;

    /**
     Creating the controller that will manage the casino games holistically.
     */
    public Controller() {
    }

    /**
    Defining a game for the Controller
     @param player - Player object
    @param type - String containing the type of game to initiate
                - "SLOTS" for a slot machine game
                - "ROULETTE" for roulette game
     */
    public void startGame(String type, Player player) {

        if (type.equals("SLOTS")) {
            game = new SlotMachineGame(player);
            slots = true;
            roulette = false;
        }
        else if (type.equals("ROULETTE")) {
            game = new RouletteGame(player);
            slots = false;
            roulette = true;
        }
        currentPlayer = player;
    }

    /**
     Placing a bet or adding an amount to an existing bet
     @param amount - int with the amount of the bet
     @param type - String with bet specifics
     */
    @Override
    public void placeBet(int amount, String type) {
        game.placeBet(amount, type);
        updateScreen();
    }

    /**
     Clears the existing bets and resets the bet amounts to 0
     */
    public void clearBets() {
        game.clearBets();
        updateScreen();
    }

    /**
     Connecting the GameTable and GameBoard in order for the controller to communicate with the view
     @param gameTable - GameTable for the game
     @param game - GameBoard for the game
     */
    public void setGameTable(GameTable gameTable, GameBoard game) {
        view = gameTable;
        board = game;
    }

    /**
     Playing a round of a game and updating the view
     */
    public void playRound() {

        List<Integer> outcome = game.generateOutcome();
        board.showOutcome(outcome);

        game.payout(outcome);

        updateScreen();

    }

    private void updateScreen() {
        view.updateBetTotal(game.getBetTotal());
        view.updateBankRoll(currentPlayer.getMyBankRoll());
    }

}
