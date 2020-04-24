package ooga.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Player {
    private int myBankRoll;
    private int maxBankRoll;
    private String myCurrentGame;
    private String myName;
    private List<String> playerNames;

    final static String PLAYER_CONFIG = "PlayerConfig";

    /**
     * Creates a new player
     * @param bankroll - bankroll the player starts with - cannot be changed later
     * @param currentGame - type of game the player is playing currently
     */
    public Player(int bankroll, String currentGame) {
        myBankRoll = bankroll;
        maxBankRoll = bankroll;
        myCurrentGame = currentGame;
        Random rand = new Random();
        String rand_int = String.valueOf(rand.nextInt(100000));
        myName = "player_" + rand_int;
        playerNames = new ArrayList<String>();
    }

    /**
     * Sets the game type
     * @param currentGame - type of game the player is playing currently
     */
    public void setMyCurrentGame(String currentGame) {
        myCurrentGame = currentGame;
    }


    /**
     * Based on a player name, loads an existing player that has previously played in the game
     * @param name - name of player tload
     */
    public void setPlayer(String name) throws Exception {
        myName = name;
        String n;
        int money;
        for (String s : playerNames) {
            n = s.split(",")[0];
            money = Integer.parseInt(s.split(",")[1]);
            if (n.equals(name) && money >= 0) {
                myBankRoll = money;
            }
        }
    }

    /**
     * Returns the list of all player names that have previously played a game in the casino
     * @return List<String> - list of player names loaded from the save file
     */
    public List<String> getPlayers() throws Exception {

        ArrayList<String> toReturn = new ArrayList<String>();

        ResourceBundle bundle = ResourceBundle.getBundle("resources." + PLAYER_CONFIG);

        String filePath = bundle.getString("Path");
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null) {
            toReturn.add(st.split(",")[0]);
            playerNames.add(st);
        }

        return toReturn;
    }

    /**
     * Saves the bankroll of player to file
     * If the player already existed, updates its bankroll
     * If the player is new, creates a new entry in the save file.
     */
    public void saveGame() throws Exception {

        String source = "";
        boolean didMine = false;
        for (String s : playerNames) {
            if (!s.split(",")[0].equals(myName)) {
                source = source + s + "\n";
            }
            else {
                source = source + myName + "," + String.valueOf(myBankRoll) + "\n";
                didMine = true;
            }
        }
        if (!didMine) {
            source = source + myName + "," + String.valueOf(myBankRoll) + "\n";
            didMine = true;
        }

        ResourceBundle bundle = ResourceBundle.getBundle("resources." + PLAYER_CONFIG);
        String filePath = bundle.getString("Path");
        File file = new File(filePath);
        FileWriter f2 = new FileWriter(file, false);
        f2.write(source);
        f2.close();

    }

    /**
     * Returns the name of the current player
     * @return String
     */
    public String getName() {
        return myName;
    }

    /**
     * Returns the current player bankroll
     * @return int
     */
    public int getMyBankRoll() {
        return myBankRoll;
    }

    /**
     * Adds or subtracts an amount to player's bankroll
     * Used when winning a bet or in the betting process
     * @param amount - int amount
     */
    public void setMyBankRoll(int amount) {
        if(checkEnoughFunds(amount)) {
            myBankRoll += amount;
        }
        else{
            throw new IllegalStateException();
        }
        if (myBankRoll > maxBankRoll) {
            maxBankRoll = myBankRoll;
        }
    }

    private boolean checkEnoughFunds(int amount) {
        return myBankRoll + amount >= 0;
    }

}


