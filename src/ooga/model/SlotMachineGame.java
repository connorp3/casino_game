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
     * @param player
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

    @Override
    public List<Integer> generateOutcome() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(random.nextInt(symbolCount));
        }
        System.out.println("size"+listOfSymbols.size());
        return listOfSymbols;
    }

    @Override
    public void payout(List<Integer> result) {

        if (areAllAligned(result)) {
            currentBet.betWon(allAlignedMultiple);
        }
        else {
            currentBet.betLost();
        }
    }

    @Override
    public int getBetTotal() {
        return currentBet.getAmount();
    }

    @Override
    public void placeBet(int amount, String type) {
        currentBet.addFunds(amount);
    }

    @Override
    public void clearBets() {
        currentBet.restore();
    }

    @Override
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
