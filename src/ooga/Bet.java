package ooga;

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

    void betWon(int multiplier) {
        player.setMyBankRoll(amount * multiplier);
    }

    void betLost() {
        amount = 0;
    }

    void cancel() {
        amount = 0;
        player.setMyBankRoll(amount);
    }

}
