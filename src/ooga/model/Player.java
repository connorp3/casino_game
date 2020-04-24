package ooga.model;

import java.util.Random;

public class Player {
    private int myBankRoll;
    private int maxBankRoll;
    private String myCurrentGame;
    private String myName;

    /**
     * Creates a new player
     * @param bankroll - bankroll the player starts with - cannot be changed later
     * @param currentGame - type of game the player is playing currently
     */
    public Player(int bankroll, String currentGame) {
        myBankRoll = bankroll;
        maxBankRoll = bankroll;
        myCurrentGame = currentGame;
        Random rand = new Random();
        String rand_int = String.valueOf(rand.nextInt(1000));
        myName = "player_" + rand_int;
    }

    public void setMyCurrentGame(String currentGame) {
        myCurrentGame = currentGame;
    }

    public void setName(String name) {
        myName = name;
    }

    public String getName() {
        return myName;
    }

    public int getMyBankRoll() {
        return myBankRoll;
    }

    public void overrideBankroll(int amount) {
        myBankRoll = amount;
        if (myBankRoll > maxBankRoll) {
            maxBankRoll = myBankRoll;
        }
    }
    public void setMyBankRoll(int amount) {
        if(checkEnoughFunds(amount)) {
            myBankRoll += amount;
        }
        else{
            throw new IllegalStateException();
        }
        if (myBankRoll > maxBankRoll) {
            maxBankRoll = myBankRoll;
        }
    }

    private boolean checkEnoughFunds(int amount) {
        return myBankRoll + amount >= 0;
    }
}
