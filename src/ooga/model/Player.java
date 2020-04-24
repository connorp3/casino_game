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

    public void setMyCurrentGame(String currentGame) {
        myCurrentGame = currentGame;
    }

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

        System.out.println(source);

        ResourceBundle bundle = ResourceBundle.getBundle("resources." + PLAYER_CONFIG);
        String filePath = bundle.getString("Path");
        File file = new File(filePath);
        FileWriter f2 = new FileWriter(file, false);
        f2.write(source);
        f2.close();

    }

    public String getName() {
        return myName;
    }

    public int getMyBankRoll() {
        return myBankRoll;
    }

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


