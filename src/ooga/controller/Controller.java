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

        if (type.toLowerCase().equals("slots")) {
            try {
                game = new SlotMachineGame(player);
            }
            catch (Exception e) {
                view.createAlert(e.getMessage());
            }

            slots = true;
            roulette = false;
        }
        else if (type.toLowerCase().equals("roulette")) {
            try {
                game = new RouletteGame(player);
            }
            catch (Exception e) {
                view.createAlert(e.getMessage());
            }

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
        try {
            game.placeBet(amount, type);
        }
        catch (Exception e) {
            view.createAlert(e.getMessage());
        }

        updateScreen();
    }

    /**
     Updates game parameters
     @param list - List<String> with the parameters - Game can interpret those when they are in the right format
                    For Slots: takes a list of 2 strings, the first containing the new number of reels and
     the second containing the new number of symbols.
                    For Roulette: feature not implemented
     */
    public void updateGameParameters(List<String> list) {
        try {
            game.updateGameParameters(list);
        }
        catch (Exception e) {
            view.createAlert(e.getMessage());
        }

    }

    /**
     Saves the game to file in a data-driven way to save the player bankroll.
     Based on PlayerConfig.properties
     Calls the method of the same name in Player to execute this function.
     */
    public void saveGame() {
        try {
            currentPlayer.saveGame();

        } catch (Exception e) {
            view.createAlert("Error saving game.");
        }

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

        try {
            game.payout(outcome);
        }
        catch (Exception e) {
            view.createAlert("Error playing round of game.");
        }


        updateScreen();

        saveGame();

        if (currentPlayer.getMyBankRoll() == 0) {
            view.displayGameOver();
        }

    }

    private void updateScreen() {
        view.updateBetTotal(game.getBetTotal());
        view.updateBankRoll(currentPlayer.getMyBankRoll());
    }

}
