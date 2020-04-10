package ooga.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BettingTest {


    public static final String SLOTS = "SLOTS";
    Player player1 = new Player(1000, SLOTS);
    Bet bet1 = new Bet(player1);

    @Test
    public void testAmount(){
        assertEquals(0, bet1.getAmount());
        bet1.addFunds(10);
        assertEquals(10, bet1.getAmount());
        assertEquals(990, player1.getMyBankRoll());
    }

    @Test
    public void testClearBet(){
        bet1.cancel();
        assertEquals(0, bet1.getAmount());
        assertEquals(1000, player1.getMyBankRoll());
    }

    @Test
    public void testWin(){
        bet1.addFunds(15);
        assertEquals(985, player1.getMyBankRoll());
        assertEquals(15, bet1.getAmount());
        bet1.betWon(10);
        assertEquals(1135, player1.getMyBankRoll());
        assertEquals(0, bet1.getAmount());
    }

    @Test
    public void testLose(){
        assertEquals(1000, player1.getMyBankRoll());
        assertEquals(0, bet1.getAmount());
        bet1.addFunds(12);
        bet1.betLost();
        assertEquals(988, player1.getMyBankRoll());
        assertEquals(0, bet1.getAmount());
    }

    }
