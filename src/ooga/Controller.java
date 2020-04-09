package ooga;

import java.util.List;

public class Controller {

    Game game;
    Bet currentBet;
    boolean betActive;
    Player currentPlayer;

    public Controller() {

    }

    public void startGame(String type) {
        if (type.equals("SLOTS")) {
            game = new SlotMachineGame(3,3);
        }
    }

    public void updatePlayer(Player player) {
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
            betActive = false;

            // update the bet
            if (payoutMultiple == 0) {
                currentBet.betLost();
            }
            else {
                currentBet.betWon(payoutMultiple);
            }

            // Send that outcome to the view

            // Send the interpretation to the view

            // Send the payout to the view
        }

    }

    public void placeBet(int amount, Object type) {
        if (amount > 0) {
            currentBet = new Bet(amount, currentPlayer);
            betActive = true;
        }
    }

    public void clearBets() {
        currentBet = null;
        betActive = false;
    }

}
