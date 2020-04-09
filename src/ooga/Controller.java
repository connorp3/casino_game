package ooga;

import java.util.List;

public class Controller {

    SlotMachineGame game;
    Bet currentBet;
    boolean betActive;

    public Controller() {
        game = new SlotMachineGame(3,3);
    }

    public void playRound() {

        if (betActive) {
            // generate a random outcome for the game as a list of integers
            List<Integer> symbols = game.spinReels();

            // interprets the symbols
            String result = game.checkOutcome(symbols);

            // calculates the payout multiple
            int payoutMultiple = game.calculatePayoutMultiple(result);

            betActive = false;
        }

    }

    public void placeBet(int amount, Object type, Player player) {
        if (amount > 0) {
            currentBet = new Bet(amount, player);
            betActive = true;
        }
    }

    public void clearBets() {
        currentBet = null;
        betActive = false;
    }

}
