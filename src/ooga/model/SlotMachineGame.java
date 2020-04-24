package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class SlotMachineGame implements Game {

    int reelCount;
    int symbolCount;
    double casinoMultiple;
    int allAlignedMultiple;
    ResourceBundle defaultData;
    Bet currentBet;

    /**
     * Creates a new slot machine game
     */
    public SlotMachineGame(Player player) throws Exception {
        try {
            defaultData = ResourceBundle.getBundle("resources.SlotMachineGameModes.default");
            reelCount = Integer.parseInt(defaultData.getString("NumReels"));
            symbolCount = Integer.parseInt(defaultData.getString("NumSymbols"));
            casinoMultiple = Double.parseDouble(defaultData.getString("CasinoMultiple"));
        }
        catch (Exception e) {
            throw new Exception("Error loading game data.");
        }

        allAlignedMultiple = (int) (Math.pow(symbolCount, reelCount-1) * casinoMultiple);
        currentBet = new Bet(player);
    }

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateOutcome() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(random.nextInt(symbolCount));
        }
        System.out.println("size"+listOfSymbols.size());
        return listOfSymbols;
    }

    public void payout(List<Integer> result) {

        if (areAllAligned(result)) {
            currentBet.betWon(allAlignedMultiple);
        }
        else {
            currentBet.betLost();
        }
    }

    /**
     * Return total amount of current pending bets
     */
    public int getBetTotal() {
        return currentBet.getAmount();
    }

    /**
     * Place a bet
     */
    public void placeBet(int amount, String type) {
        currentBet.addFunds(amount);
    }

    /**
     * Clear bets and return amount to player
     */
    public void clearBets() {
        currentBet.restore();
    }

    public void updateGameParameters(List<String> list) throws Exception {

        try {
            reelCount = Integer.parseInt(list.get(0));
            symbolCount = Integer.parseInt(list.get(1));
        }
        catch (Exception e) {
            throw new Exception("Incorrect game parameters.");
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

}
