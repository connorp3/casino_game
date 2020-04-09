package ooga.model;

public class Player {
    private int myBankRoll;
    private int maxBankRoll;
    private String myCurrentGame;

    public Player(int bankroll, String currentGame) {
        myBankRoll = bankroll;
        maxBankRoll = bankroll;
        myCurrentGame = currentGame;
    }

    public void setMyCurrentGame(String currentGame) {
        myCurrentGame = currentGame;
    }

    public int getMyBankRoll() {
        return myBankRoll;
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
