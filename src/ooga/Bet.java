package ooga;

public class Bet {

    int amount;
    boolean betActive;
    int multiplier;
    Player player;

    public Bet(int betAmount, int mult, Player p) {
        amount = betAmount;
        multiplier = mult;
        player = p;
        player.subtractAmount(betAmount);
        betActive = true;
    }

    void betWon() {
        if (betActive) {
            player.addAmount(amount * multiplier);
            betActive = false;
        }
    }

    void betLost() {
        betActive = false;
    }


}
