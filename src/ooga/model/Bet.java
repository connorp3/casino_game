package ooga.model;

public class Bet {

    int amount;
    Player player;

    public Bet(Player p) {
        amount = 0;
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
        amount = 0;
    }

    public void betLost() {
        amount = 0;
    }

    public void cancel() {
        player.setMyBankRoll(amount);
        amount = 0;
    }

}
