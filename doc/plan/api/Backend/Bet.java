package api.Backend;

public interface Bet {

    
    /*
    Object outcome.
    Generates payout by calling the Player class.
    */
    void generatePayout();

    void addBet(int amount);

}
