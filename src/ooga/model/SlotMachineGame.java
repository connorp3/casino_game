package ooga.model;

import ooga.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachineGame implements Game {

    public static final float CASINO_MULTIPLE = (float) 0.95;
    public static final String ALL_ALIGNED = "ALL_ALIGNED";
    public static final String LOSS = "LOSS";

    int reelCount;
    int symbolCount;
    int allAlignedMultiple;

    /**
     * Creates a new slot machine game
     * @param reels - number of reels to use in the simulation
     * @param symbols - number of symbols to use in the simulation
     */
    public SlotMachineGame(int reels, int symbols) {
        reelCount = reels;
        symbolCount = symbols;
        allAlignedMultiple = (int) (Math.pow(symbolCount, reelCount-1) * CASINO_MULTIPLE);
    }

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateRandomOutcome() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(getRandomSymbol(random));
        }
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

    @Override
    public int getEventCount() {
        return 1;
    }

    private boolean areAllAligned(List<Integer> listOfSymbols) {

        for (Integer sym : listOfSymbols) {
            if (!sym.equals(listOfSymbols.get(0))) {
                return false;
            }
        }
        return true;
    }

    private int getRandomSymbol(Random random) {

        int randomInteger = random.nextInt(symbolCount);
        return randomInteger;
    }


}
