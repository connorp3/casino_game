package ooga;

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

    public void addAmount(int amount) {
        myBankRoll = myBankRoll + amount;
    }

    public void subtractAmount(int amount) {
        if (amount <= myBankRoll) {
            myBankRoll = myBankRoll - amount;
        }
    }

    private boolean checkEnoughFunds(int amount) {
        return myBankRoll + amount >= 0;
    }
}
