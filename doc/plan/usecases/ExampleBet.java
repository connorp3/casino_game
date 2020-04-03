package usecases;

import api.Backend.Bet;

// after playing round of game, user wins an outcome
class ExampleBet implements Bet {
    int bet;
    int payout1 = 3;
    int payout2 = 2;

    public ExampleBet(int bet) {
        bet = bet;
    }
    @Override
    public int generatePayout(boolean outcome) {
        if(outcome) {
            return bet*payout2;
        }
        else {
            return 0;
        }

    }

    @Override
    public void addBet(int amount) {
        bet += amount;
    }
}
