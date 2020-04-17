package ooga.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlotMachineTest {


    public static final String SLOTS = "SLOTS";
    public static final String ALL_ALIGNED = "ALL_ALIGNED";
    public static final String LOSS = "LOSS";
    Player player1 = new Player(1000, SLOTS);
    Bet bet1 = new Bet(player1);


    @Test
    public void testBasic() {
        SlotMachineGame g = new SlotMachineGame(player1);
        List<Integer> integerList = g.generateOutcome();
        assertEquals(3, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i.equals(0) || i.equals(1) || i.equals(2));
        }
    }


    }
