package ooga.model;

import java.util.List;

public interface Game {

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateRandomOutcome();

    /**
     * Checking the outcome to determine what event it corresponds to
     */
    public String checkOutcome(List<Integer> result);

    /**
     * Given an event that took place, calculates the appropriate payout multiple
     */
    public int calculatePayoutMultiple(String outcome);
}
