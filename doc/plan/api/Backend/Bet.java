package api.Backend;

public interface Bet {

    
    /*
    Object outcome.
    Generates payout by calling the Player class.
    */
    int generatePayout(boolean outcome);

    void addBet(int amount);

}
