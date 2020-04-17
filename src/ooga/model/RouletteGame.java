package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class RouletteGame implements Game {

    ResourceBundle americanData;
    ResourceBundle betTypeData;
    Bet numberBet;
    Bet colorBet;
    Bet parityBet;

    /**
     * Creates a new roulette game
     * @param player
     */
    public RouletteGame(Player player) {
        americanData.getBundle("resources.RouletteGameModes.American");
        betTypeData.getBundle("resources.RouletteGameModes.betType");
    }

    /**
     * Generating a random outcome for the game
     */
    public List<Integer> generateOutcome() {
        List<Integer> rouletteOutcome = new ArrayList<>();
        Random random = new Random();
        rouletteOutcome.add(random.nextInt(38));
        return rouletteOutcome;
    }

    @Override
    public void payout(List<Integer> outcome) {

    }

    @Override
    public int getBetTotal() {
        return 0;
    }

    @Override
    public void placeBet(int amount, String type) {

        if (betTypeData.getString("color").contains(type)) {
            colorBet.addFunds(amount);
            colorBet.setEvent(type);
        }
        else if (betTypeData.getString("parity").contains(type)) {
            parityBet.addFunds(amount);
            parityBet.setEvent(type);
        }
        else if (betTypeData.getString("number").contains(type)) {
            numberBet.addFunds(amount);
            numberBet.setEvent(type);
        }
    }

    @Override
    public void clearBets() {

    }


}
