package usecases;

import api.Backend.Player;

public class ExamplePlayer implements Player {
    int mybankroll;

    public ExamplePlayer(int bankroll) {
        mybankroll = bankroll;
    }
    @Override
    public int getBankroll() {
        return mybankroll;
    }

    @Override
    public void placeBet(int amount) {

    }

    @Override
    public void betWon(int amount) {
        mybankroll += amount;
    }

    @Override
    public void betLost() {

    }
}
