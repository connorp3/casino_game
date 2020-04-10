package ooga.model;

public class Bet {

    public static final int NULL_AMOUNT = 0;
    int amount;
    Player player;

    public Bet(Player p) {
        amount = NULL_AMOUNT;
        player = p;
    }

    public void addFunds(int newAmount) {
        amount = amount + newAmount;
        player.setMyBankRoll(-newAmount);
    }

    public int getAmount() {
        return amount;
    }

    public void betWon(int multiplier) {
        player.setMyBankRoll(amount * multiplier);
        amount = NULL_AMOUNT;
    }

    public void betLost() {
        amount = NULL_AMOUNT;
    }

    public void cancel() {
        player.setMyBankRoll(amount);
        amount = NULL_AMOUNT;
    }

}
