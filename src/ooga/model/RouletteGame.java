package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class RouletteGame implements Game {


    public static final int EVENT_COUNT = 42;

    /**
     * Creates a new roulette game
     */
    public RouletteGame() {

    }

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateRandomOutcome() {
        List<Integer> rouletteOutcome = new ArrayList<>();
        Random random = new Random();
        rouletteOutcome.add(getRandomSymbol(random));
        return rouletteOutcome;
    }
    
    /**
     * Checking the outcome to determine what event it corresponds to
     */
    public String checkOutcome(List<Integer> result) {
        int num = result.get(0);
        return "";

    }

    /**
     * Given an event that took place, calculates the appropriate payout multiple
     */
    public int calculatePayoutMultiple(String outcome) {
        if (outcome.equals("HALF")) {
            return 2;
        }
        else if (outcome.equals("NUMBER")) {
            return 36;
        }
        else {
            return 0;
        }
    }

    /**
     * Returns the number of symbols to the controller to keep track of all the possible bets
     */
    public int getEventCount() {
        return EVENT_COUNT;
    }

    private int getRandomSymbol(Random random) {

        int randomInteger = random.nextInt(38);
        return randomInteger;
    }



}
