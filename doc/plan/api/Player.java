package api;

public interface Player  {

    /*
    This method returns the integer representing the amount in the bankroll.
    It will be used by the view to display the amount of money the player has in
    the bankroll
    @return - integer representing the amount in the bankroll
    */
    int getBankroll();
    
    /*
    This method reduces the player bankroll by the amount of a bet.
    Throws an exception if the player does not have enough money to place that bet.
    Called by the Bet class.
    @param amount - int containing the value to be subtracted from balance.
    */
    void placeBet(int amount);
    
    /*
    Adds an amount of money to bankroll is case a bet was won. Amount supplied
    by a Bet object.
    @param amount - int contaning the value to be added to the player
    representing the amount in the bankroll
    Registers a won bet for statistics purposes.
    */
    void betWon(int amount);
    
    /*
    Registers a lost bet for statistics purposes.
    Removes the bet pending status on player.
    */
    void betLost();

}
