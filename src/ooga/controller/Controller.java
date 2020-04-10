package ooga.controller;

import ooga.model.Bet;
import ooga.model.Game;
import ooga.model.Player;
import ooga.model.SlotMachineGame;
import ooga.view.GameBoard;
import ooga.view.GameTable;

import java.util.List;

public class Controller {

    public static final int DEFAULT_SLOTS_REELS = 3;
    public static final int DEFAULT_SLOTS_SYMBOLS = 3;
    public static final String ALL_ALIGNED = "ALL_ALIGNED";
    public static final String SLOTS = "SLOTS";
    public static final int NULL_AMOUNT = 0;

    Game game;
    Bet currentBet;
    Player currentPlayer;
    GameTable view;
    GameBoard board;


    /**
    Creating the controller that will manage the casino games holistically
     The controller will not be functional unless you also call the
     startGame(String type), loadPlayer(Player player), and
     setGameTable(GameTable gameTable, GameBoard game) methods.
     */
    public Controller() {
    }

    /**
    Defining a game for the Controller
    @param type - String containing the type of game to initiate
                - SLOTS for a slot machine game
     */
    public void startGame(String type) {
        if (type.equals(SLOTS)) {
            game = new SlotMachineGame(DEFAULT_SLOTS_REELS, DEFAULT_SLOTS_SYMBOLS);
            currentBet = new Bet(currentPlayer);
        }
    }

    /**
     Loading a player to interact with the Controller
     @param player - Player object
     */
    public void loadPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     Placing a bet or adding an amount to an existing bet
     @param amount - int with the amount of the bet
     */
    public void placeBet(int amount, Object type) {
        currentBet.addFunds(amount);
        updateScreen();
    }

    /**
     Clears the existing bet and resets the bet amount to 0
     */
    public void clearBets() {
        currentBet.cancel();
        resetCurrentBet();
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

        // Generate a random outcome for the game as a list of integers
        List<Integer> symbols = game.generateRandomOutcome();

        // Send that outcome to the view
        board.showOutcome(symbols);

        // Distribute payout according to outcome type
        String result = game.checkOutcome(symbols);
        distributePayout(result);

        updateScreen();

        // Send the interpretation to the view - TBD
        // board.showMessage(result);
    }

    private void distributePayout(String result) {
        if (result.equals(ALL_ALIGNED)) { // if it's a win
            betWon(result);
        }
        else { // if it's a loss
            betLost();
        }
    }

    private void betWon(String result) {

        int payoutMultiple = game.calculatePayoutMultiple(result);
        currentBet.betWon(payoutMultiple);
        resetCurrentBet();
    }

    private void resetCurrentBet() {
        currentBet = new Bet(currentPlayer);
    }

    private void betLost() {
        currentBet.betLost();
        resetCurrentBet();
        if (currentPlayer.getMyBankRoll() == NULL_AMOUNT) {
            view.displayGameOver();
        }
    }

    private void updateScreen() {
        view.updateBetTotal(currentBet.getAmount());
        view.updateBankRoll(currentPlayer.getMyBankRoll());
    }


}
