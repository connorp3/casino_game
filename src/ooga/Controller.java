package ooga;

public interface Controller {

    void placeBet(int amount, Object type);

    void clearBets();

    void playRound();
}
