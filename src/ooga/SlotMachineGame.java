package ooga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SlotMachineGame {

    public static final float CASINO_MULTIPLE = (float) 0.95;

    int reelCount;
    int symbolCount;
    Bet currentBet;
    Player player;
    int allAlignedMultiple;

//With this GameTable and GameBoard object, you will have access to methods like updateBankRollDisplay, updateTotalBetDisplay, update gameBoard, disableXButton, etc
    //That way, whenever something happens and needs to be displayed in the GUI, you can call these methods and pass the proper information to the GUI
    public SlotMachineGame(Player p, GameTable myGameInfoDisplay, GameBoard gameDisplay) {
        reelCount = 3;
        symbolCount = 3;
        player = p;
        allAlignedMultiple = (int) (Math.pow(symbolCount, reelCount-1) * CASINO_MULTIPLE);
    }

    void placeBet(int amount) {
        if (amount > 0) {
            currentBet = new Bet(amount, player);
        }
    }

    boolean areAllAligned(List<Integer> listOfSymbols) {

        for (Integer sym : listOfSymbols) {
            if (!sym.equals(listOfSymbols.get(0))) {
                return false;
            }
        }
        return true;
    }

    void spinReels() {
        List<Integer> listOfSymbols = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < reelCount; i++) {
            listOfSymbols.add(getRandomSymbol(random));
        }

        System.out.println(listOfSymbols);

        if (areAllAligned(listOfSymbols)) {
            System.out.println("You win");
            currentBet.betWon(allAlignedMultiple);
        }
        else {
            System.out.println("You lose");
            currentBet.betLost();
        }

        System.out.println("Your new balance is: " + player.getMyBankRoll());

    }

    private int getRandomSymbol(Random random) {

        int randomInteger = random.nextInt(symbolCount);
        return randomInteger;
    }

    public static void main (String[] args) {
        //SlotMachineGame test = new SlotMachineGame(new Player(100000, "SlotMachine"));
        //for (int i = 0; i < 100000; i++) {
            //test.placeBet(1);
            //test.spinReels();
        //}

    }


}
