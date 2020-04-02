package api.View;

public interface Menu  {

    /***
     * This method will allow for the creation of a button specific to a certain game,
     * and will obtain styling for the button from a style folder
     */
    void CreateGameButton();

    /***
     * This method will be used to set the functionality of a game button to
     * create the new scene with game elements specific to any game
     * by implementing a specific GameTable object and making its scene. It will instantiate a new
     * Player and pass that to the GameTable.
     */
    void StartGame();

}
