package ooga.model;

public class Bet {

    public static final int NULL_AMOUNT = 0;
    int amount;
    Player player;
    String type;

    /**
     Creating a Bet object to contain a bet
     @param p - Player object who places the bet
     */
    public Bet(Player p) {
        amount = NULL_AMOUNT;
        player = p;
        type = "";
    }

    /**
     Adding funds to an existing bet to increase the size of the bet
     @param newAmount - amount to add
     */
    public void addFunds(int newAmount) {
        if (newAmount <= player.getMyBankRoll()) {
            amount = amount + newAmount;
            player.setMyBankRoll(-newAmount);
        }
    }


    /**
     Sets the type of bet that is being played
     Not used for slot machine.
     For example, for roulette, could be "8", "red", "odd", "even" etc...
     @param t - String containing the type of event
     */
    public void setEvent(String t) {
        type = t;
    }

    /**
     Gets the type of bet that is being played
     Not used for slot machine.
     For example, for roulette, could be "8", "red", "odd", "even" etc...
     @return type - String containing the type of event
     */
    public String getEvent() {
        return type;
    }

    /**
     Getting the amount currently stored in the bet
     @return amount - the amount stored in bet
     */
    public int getAmount() {
        return amount;
    }

    /**
     Calculating winnings and adding them to player bankroll
     @param multiplier - integer amount used to multiply the bet amount to calculate winnings
     */
    public void betWon(int multiplier) {
        player.setMyBankRoll(amount * multiplier);
        amount = NULL_AMOUNT;
    }
    /**
     * Lost bet - marking amount as 0
     */
    public void betLost() {
        amount = NULL_AMOUNT;
    }

    /**
     * Cancelling a bet - marking amount as 0 and restoring previous player bankroll
     */
    public void restore() {
        player.setMyBankRoll(amount);
        amount = NULL_AMOUNT;
    }

}
