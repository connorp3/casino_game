package ooga.model;

import ooga.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachineGame implements Game {

    public static final float CASINO_MULTIPLE = (float) 0.95;

    int reelCount;
    int symbolCount;
    int allAlignedMultiple;

    public SlotMachineGame(int reels, int symbols) {
        reelCount = reels;
        symbolCount = symbols;
        allAlignedMultiple = (int) (Math.pow(symbolCount, reelCount-1) * CASINO_MULTIPLE);
    }

    // generate a random outcome for the game as a list of integers
    public List<Integer> generateRandomOutcome() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(getRandomSymbol(random));
        }
        return listOfSymbols;
    }

    // check the outcome of the round for a winning/losing event
    public String checkOutcome(List<Integer> result) {
        if (areAllAligned(result)) {
            return "ALL_ALIGNED";
        }
        else {
            return "LOSS";
        }
    }

    // calculates the payout multiple
    public int calculatePayoutMultiple(String outcome) {
        if (outcome.equals("ALL_ALIGNED")) {
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

    private int getRandomSymbol(Random random) {

        int randomInteger = random.nextInt(symbolCount);
        return randomInteger;
    }


}
