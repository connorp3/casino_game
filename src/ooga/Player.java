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

    public void setMyBankRoll(int amount) {
        myBankRoll += amount;
        if (myBankRoll > maxBankRoll) {
            maxBankRoll = myBankRoll;
        }
    }
}
