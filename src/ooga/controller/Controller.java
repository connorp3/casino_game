package ooga.controller;

import ooga.model.*;
import ooga.view.GameBoard;
import ooga.view.GameTable;

import java.util.List;
import java.util.ResourceBundle;

public class Controller {

    public static final int DEFAULT_SLOTS_REELS = 3;
    public static final int DEFAULT_SLOTS_SYMBOLS = 3;
    public static final String ALL_ALIGNED = "ALL_ALIGNED";
    public static final String LOSS = "LOSS";
    public static final String HALF_EVENT = "HALF_EVENT";
    public static final String NUMBER = "NUMBER";
    public static final String SLOTS = "SLOTS";
    public static final String ROULETTE = "ROULETTE";
    public static final int NULL_AMOUNT = 0;

    Game game;
    Bet currentBet;
    Player currentPlayer;
    GameTable view;
    GameBoard board;
    String betType;
    String gameType;


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
                - ROULETTE for roulette game
     */
    public void startGame(String type) {
        gameType = type;
        if (type.equals(SLOTS)) {
            game = new SlotMachineGame(DEFAULT_SLOTS_REELS, DEFAULT_SLOTS_SYMBOLS);
        }
        else if (type.equals(ROULETTE)) {
            game = new RouletteGame();
        }
        currentBet = new Bet(currentPlayer);
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
    public void placeBet(int amount, String type) {
        currentBet.addFunds(amount);
        betType = type;
        updateScreen();
    }

    /**
     Clears the existing bets and resets the bet amounts to 0
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
        if (gameType.equals(SLOTS)) {
            String result = game.checkOutcome(symbols);
            distributePayout(result);
        }
        else if (gameType.equals(ROULETTE)) {
            String result = String.valueOf(symbols.get(0));
            if (isRed(result.toLowerCase()) && isRed(betType.toLowerCase())) {
                distributePayout(HALF_EVENT);
            }
            else if (isBlack(result.toLowerCase()) && isBlack(betType.toLowerCase())) {
                distributePayout(HALF_EVENT);
            }
            else if (isEven(result.toLowerCase()) && isEven(betType.toLowerCase())) {
                distributePayout(HALF_EVENT);
            }
            else if (isOdd(result.toLowerCase()) && isOdd(betType.toLowerCase())) {
                distributePayout(HALF_EVENT);
            }
            else if (result.equals(betType.toLowerCase())) {
                distributePayout(NUMBER);
            }
            else {
                distributePayout(LOSS);
            }
        }


        updateScreen();

        // Send the interpretation to the view - TBD
        // board.showMessage(result);
    }

    private boolean isRed(String s) {
        ResourceBundle x = ResourceBundle.getBundle("resources.RouletteGameModes.American");
        return (x.getString(s).equals("red"));
    }

    private boolean isBlack(String s) {
        ResourceBundle x = ResourceBundle.getBundle("resources.RouletteGameModes.American");
        return (x.getString(s).equals("black"));
    }

    private boolean isEven(String s) {
        if (s.equals("red") || s.equals("black") || s.equals("even")||  s.equals("odd")) {
            return false;
        }
        return (Integer.parseInt(s) % 2 == 0);
    }

    private boolean isOdd(String s) {
        if (s.equals("red") || s.equals("black") || s.equals("even")||  s.equals("odd")) {
            return false;
        }
        return (Integer.parseInt(s) % 2 != 0);
    }

    private void distributePayout(String result) {
        if (result.equals(ALL_ALIGNED)) { // if it's a win
            betWon(result);
        }
        else if (result.equals(LOSS)) {
            betLost();
        }
        else if (result.equals(HALF_EVENT)) {
            betWon(HALF_EVENT);
        }
        else if (result.equals(NUMBER)) {
            betWon(NUMBER);
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
