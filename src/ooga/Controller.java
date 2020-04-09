package ooga;

import java.util.List;

public class Controller {

    Game game;
    Bet currentBet;
    boolean betActive;
    Player currentPlayer;
    GameTable view;
    GameBoard board;

    public Controller() {

    }

    public void startGame(String type) {
        if (type.equals("SLOTS")) {
            game = new SlotMachineGame(3,3);
        }
    }

    public void loadPlayer(Player player) {
        currentPlayer = player;
    }

    public void endGame() {
        game = null;
    }

    public void playRound() {

        if (betActive) {
            // Generate a random outcome for the game as a list of integers
            List<Integer> symbols = game.generateRandomOutcome();

            // Interpret the symbols
            String result = game.checkOutcome(symbols);

            // calculates the payout multiple
            int payoutMultiple = game.calculatePayoutMultiple(result);

            // update the bet
            if (payoutMultiple == 0) {
                currentBet.betLost();
                if (currentPlayer.getMyBankRoll() == 0) {
                    view.displayGameOver();
                }
            } else currentBet.betWon(payoutMultiple);
            betActive = false;

            // Send that outcome to the view
            board.showOutcome(symbols);

            // Send the interpretation to the view
            // board.showMessage(result);

            // Send the payout update to the view
            view.updateBankRoll(currentPlayer.getMyBankRoll());
        }

    }

    public void placeBet(int amount, Object type) {
        if (amount > 0) {
            currentBet = new Bet(amount, currentPlayer);
            view.updateBetTotal(amount);
            view.updateBankRoll(currentPlayer.getMyBankRoll());
            betActive = true;
        }
    }

    public void clearBets() {
        currentBet = null;
        betActive = false;
        view.updateBetTotal(0);
        // add money back to player
        view.updateBankRoll(currentPlayer.getMyBankRoll());
    }

    public void setGameTable(GameTable gameTable, GameBoard game) {
        view = gameTable;
        board = game;
    }
}
