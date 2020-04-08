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
        if (amount > 0) {
            currentBet = new Bet(amount, 27, player);
        }
    }

    void spinReels() {
        List<Integer> listOfSymbols = new ArrayList<>();

        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(getRandomSymbol());
        }

        System.out.println(listOfSymbols);

        if (listOfSymbols.get(0).equals(listOfSymbols.get(1)) && listOfSymbols.get(1).equals(listOfSymbols.get(2))) {
            System.out.println("You win");
            currentBet.betWon();
        }
        else {
            System.out.println("You lose");
            currentBet.betLost();
        }

        System.out.println("Your new balance is: " + player.getMyBankRoll());

    }

    private int getRandomSymbol() {
        Random random = new Random();
        int randomInteger = random.nextInt(symbolCount);
        return randomInteger;
    }

    public static void main (String[] args) {
        SlotMachineGame test = new SlotMachineGame(new Player(100, "SlotMachine"));
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();
        test.placeBet(10);
        test.spinReels();

    }


}
