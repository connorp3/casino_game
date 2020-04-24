package ooga.model;

import jdk.jshell.spi.ExecutionControlProvider;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotMachineTest {


    Player player1 = new Player(1000, "SLOTS");
    Bet bet1 = new Bet(player1);


    @Test
    public void testBasic() throws Exception {
        SlotMachineGame g = new SlotMachineGame(player1);
        List<Integer> integerList = g.generateOutcome();
        assertEquals(3, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i.equals(0) || i.equals(1) || i.equals(2));
        }

    }

    @Test
    public void testUpdateParam() throws Exception {
        SlotMachineGame g = new SlotMachineGame(player1);
        List<String> newParameters = new ArrayList<String>();
        newParameters.add("4");
        newParameters.add("4");
        g.updateGameParameters(newParameters);
        List<Integer> integerList = g.generateOutcome();
        assertEquals(4, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i.equals(0) || i.equals(1) || i.equals(2) || i.equals(3));
        }
    }

    @Test
    public void testPayouts() throws Exception {
        SlotMachineGame g = new SlotMachineGame(player1);
        g.placeBet(10, null);
        assertEquals(990, player1.getMyBankRoll());
        assertEquals(10, g.getBetTotal());
        g.clearBets();
        assertEquals(1000, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());

    }

    @Test
    public void testPayOutMethod() throws Exception {
        SlotMachineGame g = new SlotMachineGame(player1);
        g.placeBet(10, null);
        List<Integer> result = new ArrayList<Integer>();
        result.add(1);
        result.add(1);
        result.add(1);
        g.payout(result);
        assertEquals(1070, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());
        result.clear();
        result.add(1);
        result.add(1);
        result.add(0);
        g.placeBet(10, null);
        assertEquals(10, g.getBetTotal());
        g.payout(result);
        assertEquals(1060, player1.getMyBankRoll());
        assertEquals(0, g.getBetTotal());


    }



    }
