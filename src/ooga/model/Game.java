package ooga.model;

import java.util.List;

public interface Game {

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateOutcome();

    /**
     * Given an event that took place, calculates the appropriate payout multiple
     */
    public void payout(List<Integer> outcome);

    /**
     * Returns the total amount of money bet at any time by the player
     */
    public int getBetTotal();

    public void placeBet(int amount, String type);

    public void clearBets();
}
