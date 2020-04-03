package usecases;

/***
 * Player has bet 10 on example game outcome and outcome result must be determined and bankroll
 * updated.
 */
public class UseCase1 {

    public static void main(String[] args) {
        ExamplePlayer player = new ExamplePlayer(100);
        ExampleBet bet = new ExampleBet(10);
        ExampleGame game = new ExampleGame(bet, player);
        game.playRound();
    }
}
