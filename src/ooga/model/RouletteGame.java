package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class RouletteGame implements Game {

    public final static int DOUBLE_ZERO = -1;
    ResourceBundle gameConfig;
    int numberCount;
    int highestNum;
    int doubleZero;


    /**
     * Creates a new roulette game
     */
    public RouletteGame() {
        gameConfig = ResourceBundle.getBundle("resources.RouletteGameModes.General");
        highestNum = Integer.parseInt(gameConfig.getString("HighestNum"));
        numberCount = Integer.parseInt(gameConfig.getString("NumberCount"));
        doubleZero = Integer.parseInt(gameConfig.getString("DoubleZero"));
    }

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateRandomOutcome() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        listOfSymbols.add(getRandomSymbol(random));
        return listOfSymbols;
    }

    /**
     * Checking the outcome to determine what event it corresponds to
     */
    public String checkOutcome(List<Integer> result) {
        if (areAllAligned(result)) {
            return ALL_ALIGNED;
        }
        else {
            return LOSS;
        }
    }

    /**
     * Given an event that took place, calculates the appropriate payout multiple
     */
    public int calculatePayoutMultiple(String outcome) {
        if (outcome.equals(ALL_ALIGNED)) {
            return allAlignedMultiple;
        }
        else {
            return 0;
        }
    }

    private boolean areAllAligned(List<Integer> listOfSymbols) {

        for (Integer sym : listOfSymbols) {
            if (!sym.equals(listOfSymbols.get(0))) {
                return false;
            }
        }
        return true;
    }

    private boolean isEven(int i) {
        return (i % 2 == 0);
    }

    private int getRandomSymbol(Random random) {

        int randomInteger = random.nextInt(numberCount);
        return randomInteger;
    }


}
