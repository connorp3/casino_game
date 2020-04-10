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
        SlotMachineGame g = new SlotMachineGame(3, 3);
        List<Integer> integerList = g.generateRandomOutcome();
        assertEquals(3, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i.equals(0) || i.equals(1) || i.equals(2));
        }

        g = new SlotMachineGame(4, 5);
        integerList = g.generateRandomOutcome();
        assertEquals(4, integerList.size());
        for (Integer i : integerList) {
            assertEquals(true, i.equals(0) || i.equals(1) || i.equals(2) || i.equals(3) || i.equals(4));
        }
    }

    @Test
    public void checkOutcomeTest() {
        SlotMachineGame g = new SlotMachineGame(4, 3);
        List<Integer> listAligned1 = Arrays.asList(1, 1, 1, 1);
        String outcome1 = g.checkOutcome(listAligned1);
        assertEquals(ALL_ALIGNED, outcome1);
        List<Integer> notAligned = Arrays.asList(1, 0, 1, 1);
        String outcome2 = g.checkOutcome(notAligned);
        assertEquals(LOSS, outcome2);
    }

    @Test
    public void checkMultiple() {
        SlotMachineGame g = new SlotMachineGame(3, 3);
        List<Integer> listAligned1 = Arrays.asList(1, 1, 1);
        String outcome1 = g.checkOutcome(listAligned1);
        assertEquals(ALL_ALIGNED, outcome1);

        assertEquals(8, g.calculatePayoutMultiple(outcome1));


        List<Integer> notAligned = Arrays.asList(1, 0, 1);
        String outcome2 = g.checkOutcome(notAligned);
        assertEquals(LOSS, outcome2);
        assertEquals(0, g.calculatePayoutMultiple(outcome2));
    }


    }
