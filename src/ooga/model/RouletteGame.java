package ooga.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class RouletteGame implements Game {

    ResourceBundle americanData;
    ResourceBundle betTypeData;
    ResourceBundle defaultData;
    int randomGenBound;
    int doubleZeroValue;
    Bet numberBet;
    Bet colorBet;
    Bet parityBet;

    /**
     * Creates a new roulette game
     * @param player
     */
    public RouletteGame(Player player) throws Exception {
        try {
            americanData = ResourceBundle.getBundle("resources.RouletteGameModes.American");
            betTypeData = ResourceBundle.getBundle("resources.RouletteGameModes.betType");
            defaultData = ResourceBundle.getBundle("resources.RouletteGameModes.default");
            randomGenBound = Integer.parseInt(defaultData.getString("RandomGenBound"));
            doubleZeroValue = Integer.parseInt(defaultData.getString("DoubleZeroValue"));
        }
        catch (Exception e) {
            throw new Exception("Error loading game data files.");
        }

        numberBet = new Bet(player);
        colorBet = new Bet(player);
        parityBet = new Bet(player);
    }

    @Override
    public List<Integer> generateOutcome() {
        List<Integer> rouletteOutcome = new ArrayList<>();
        Random random = new Random();
        rouletteOutcome.add(random.nextInt(randomGenBound));
        return rouletteOutcome;
    }

    @Override
    public void payout(List<Integer> outcome) {

        String outcomeNumber;
        String outcomeParity;
        String outcomeColor;

        if (outcome.get(0) == doubleZeroValue) {
            outcomeNumber = "00";
        }
        else {
            outcomeNumber = outcome.get(0).toString();
        }

        outcomeColor = americanData.getString(outcomeNumber);
        if (outcomeNumber.equals("0") || outcomeNumber.equals("00")) {
            outcomeParity = "none";
        }
        else if (outcome.get(0) % 2 == 0) {
            outcomeParity = "even";
        }
        else {
            outcomeParity = "odd";
        }

        if (numberBet.getEvent().equals(outcomeNumber)) {
            numberBet.betWon(36);
        }
        else if (!numberBet.getEvent().equals(outcomeNumber)) {
            numberBet.betLost();
        }
        if (parityBet.getEvent().equals(outcomeParity)) {
            parityBet.betWon(2);
        }
        else if (!parityBet.getEvent().equals(outcomeParity)) {
            parityBet.betLost();
        }
        if (colorBet.getEvent().equals(outcomeColor)) {
            colorBet.betWon(2);
        }
        else if (!colorBet.getEvent().equals(outcomeColor)) {
            colorBet.betLost();
        }


    }

    @Override
    public int getBetTotal() {
        return numberBet.getAmount() + parityBet.getAmount() + colorBet.getAmount();
    }

    @Override
    public void placeBet(int amount, String type) throws Exception {

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
        else if (!type.equals("None")) {
            throw new Exception("Invalid game parameter.");
        }
    }

    @Override
    public void clearBets() {
        numberBet.restore();
        parityBet.restore();
        colorBet.restore();
    }

    @Override
    public void updateGameParameters(List<String> list) throws Exception {
        /*String gameType = list.get(0);
        if (gameType.toLowerCase().equals("american")) {
            randomGenBound = 38;
        }
        else if (gameType.toLowerCase().equals("european") || gameType.toLowerCase().equals("french")) {
            randomGenBound = 37;
        }
        else {
            throw new Exception("Invalid game parameter.");
        }*/
    }


}
