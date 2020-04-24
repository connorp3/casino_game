package ooga.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouletteTest {


    Player player1 = new Player(1000, "SLOTS");
    Bet bet1 = new Bet(player1);


    @Test
    public void testBasic() throws Exception {
        RouletteGame g = new RouletteGame(player1);
        List<Integer> integerList = g.generateOutcome();
        assertEquals(1, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i >= 0 & i <= 37);
        }

    }

    @Test
    public void testPayouts() throws Exception {
        RouletteGame g = new RouletteGame(player1);
        g.placeBet(10, "red");
        assertEquals(990, player1.getMyBankRoll());
        assertEquals(10, g.getBetTotal());
        g.clearBets();
        assertEquals(1000, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());

    }

    @Test
    public void testPayOutMethod() throws Exception {
        RouletteGame g = new RouletteGame(player1);
        g.placeBet(10, "red");
        List<Integer> result = new ArrayList<Integer>();
        result.add(36);
        g.payout(result);
        assertEquals(1010, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());

        result.clear();
        result.add(35);
        g.placeBet(10, "red");
        assertEquals(10, g.getBetTotal());
        g.payout(result);
        assertEquals(1000, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());

        result.clear();
        result.add(35);
        g.placeBet(10, "35");
        assertEquals(10, g.getBetTotal());
        g.payout(result);
        assertEquals(1350, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());


    }


    }
