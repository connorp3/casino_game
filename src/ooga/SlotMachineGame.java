package ooga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachineGame {

    int reelCount;
    int symbolCount;
    Bet currentBet;
    Player player;

    public SlotMachineGame(Player p) {
        reelCount = 3;
        symbolCount = 3;
        player = p;
    }

    void placeBet(int amount) {
        if (amount <= player.getMyBankRoll()) {
            currentBet = new Bet(amount);
        }
    }



    List<Integer> spinReels() {
        if (betAmount <= 0) {
            System.out.println("Error: You must place a bet to play.");
        }

        List<Integer> listOfSymbols = new ArrayList<>();


        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(getRandomSymbol());
        }

        if (listOfSymbols.get(0).equals(listOfSymbols.get(1)) && listOfSymbols.get(1).equals(listOfSymbols.get(2))) {

        }

        // when spin is over and result is determined
        betAmount = 0;

        return listOfSymbols;
    }

    private int getRandomSymbol() {
        Random random = new Random();
        int randomInteger = random.nextInt(symbolCount);
        return randomInteger;
    }


}
