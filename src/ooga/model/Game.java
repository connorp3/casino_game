package ooga.model;

import java.util.List;

public interface Game {

    /**
     * Generates a random outcome for the game as a list of Integers
     * For roulette, the list will only contain one Integer
     * For Slots, can contain a variable number of Integers
     */
    public List<Integer> generateOutcome();

    /**
     * Given an event/outcome that took place, calculates the appropriate payout multiple
     * based on game probabilities
     */
    public void payout(List<Integer> outcome);

    /**
     * Returns the total amount of money bet at any time by the player
     * @return int - amount of money
     */
    public int getBetTotal();

    /**
     * Places a bet in a game
     * @param amount - amount of money
     * @param type - type of bet. For slots, it is not used.
     *             For slots, it could be "8", "red", "even" etc...
     */
    public void placeBet(int amount, String type) throws Exception;

    /**
     * Clears all the bets.
     * If the player had bet an amount of money without playing a round,
     * the money will be restored.
     */
    public void clearBets();


    /**
     * Updates game parameters while the game is loaded
     *      @param list - List<String> with the parameters - Game can interpret those when they are in the right format
     *                     For Slots: takes a list of 2 strings, the first containing the new number of reels and
     *      the second containing the new number of symbols.
     *                     For Roulette: feature not implemented
     */
    public void updateGameParameters(List<String> list) throws Exception;
}
