package ooga;

public class Bet {

    int amount;
    boolean betActive;
    Player player;

    public Bet(int betAmount, Player p) {
        amount = betAmount;
        player = p;
        player.setMyBankRoll(-betAmount);
        betActive = true;
    }

    void betWon(int multiplier) {
        if (betActive) {
            player.setMyBankRoll(amount * multiplier);
            betActive = false;
        }
        else {
            System.out.println("You need to place a bet in order to play");
        }
    }

    void betLost() {
        betActive = false;
    }


}
