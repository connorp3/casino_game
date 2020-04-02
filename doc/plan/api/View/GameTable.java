package api.View;

public interface GameTable {

    /***
     *This method will create the buttons and styling for a given game with data
     * read from styling files for the general features of casino games
     * (Private methods will be added to break this up and more APIs may be implemented
     * where necessary)
     */
    void makeGameScene();

    /***
     * This method will generate the buttons that are necessary for every game
     * such as betting buttons, clear bet, and deal/spin
     */
    void createGamePlayButtons();


}
