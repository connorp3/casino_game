package ooga.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {


    Player player1 = new Player(1000, "SLOTS");
    Bet bet1 = new Bet(player1);


    @Test
    public void testBasic() throws Exception {
        SlotMachineGame g = new SlotMachineGame(player1);
        player1.setMyCurrentGame("roulette");
        List<String> players = player1.getPlayers();
        System.out.println(players.get(0));
        int initMoney = 5;
        player1.setPlayer(players.get(0));
        player1.setMyBankRoll(5 - player1.getMyBankRoll());

        assertEquals(players.get(0), player1.getName());
        assertEquals(initMoney, player1.getMyBankRoll());
        List<Integer> results = new ArrayList<Integer>();
        results.add(1);
        results.add(1);
        results.add(0);
        g.placeBet(5, null);
        g.payout(results);
        assertEquals(0, player1.getMyBankRoll());
        player1.saveGame();
        player1.setPlayer("test2");
        assertEquals(8, player1.getMyBankRoll());
        player1.setPlayer("test1");
        assertEquals(0, player1.getMyBankRoll());




    }




    }
