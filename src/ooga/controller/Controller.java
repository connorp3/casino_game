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

    Game game;
    Bet currentBet;
    Player currentPlayer;
    GameTable view;
    GameBoard board;

    public Controller() {
    }

    public void startGame(String type) {
        if (type.equals(SLOTS)) {
            game = new SlotMachineGame(DEFAULT_SLOTS_REELS, DEFAULT_SLOTS_SYMBOLS);
            currentBet = new Bet(currentPlayer);
        }
    }

    public void loadPlayer(Player player) {
        currentPlayer = player;
    }

    public void playRound() {

        // Generate a random outcome for the game as a list of integers
        List<Integer> symbols = game.generateRandomOutcome();

        // Send that outcome to the view
        board.showOutcome(symbols);

        // Interpret the symbols
        String result = game.checkOutcome(symbols);

        if (result.equals(ALL_ALIGNED)) { // if it's a win
            betWon(result);
        }
        else { // if it's a loss
            betLost();
        }

        updateScreen();

        // Send the interpretation to the view - TBD
        // board.showMessage(result);
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
        if (currentPlayer.getMyBankRoll() == 0) {
            view.displayGameOver();
        }
    }

    private void updateScreen() {
        view.updateBetTotal(currentBet.getAmount());
        view.updateBankRoll(currentPlayer.getMyBankRoll());
    }

    public void placeBet(int amount, Object type) {
        currentBet.addFunds(amount);
        updateScreen();
    }

    public void clearBets() {
        currentBet.cancel();
        resetCurrentBet();
        updateScreen();
    }

    public void setGameTable(GameTable gameTable, GameBoard game) {
        view = gameTable;
        board = game;
    }
}
