package usecases;

import api.Backend.Bet;
import api.Backend.Game;
import api.Backend.Player;

public class ExampleGame implements Game {
    Bet myBet;
    Player myPlayer;
    public ExampleGame(Bet bet, Player player) {
        myBet = bet;
        myPlayer = player;
    }

    @Override
    public void playRound() {
        boolean outcome = true;
        int winnings = myBet.generatePayout(outcome);
        myPlayer.betWon(winnings);

    }

}
