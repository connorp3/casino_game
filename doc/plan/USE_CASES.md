# Use Cases  

Use cases: In another file, USE_CASES.md, write at least 5 use cases per person on your team that describe specific features you expect to complete. Focus on those features you plan to complete during the first two sprints (i.e., the next two weeks — the project's highest priority features). Note, they can be similar to those given in the assignment specification, but should include more details based on your project's goals.
* Player selects slot machine game
  * User clicks button representing slotmachine in menu display
  * GameTable implementation for slot machine is instantiated with proper Game (backend) implementation passed to it
  * New Player object is instantiated with a certain bankroll and passed to the GameTable
  * Bet Buttons and PlayRound buttons are instantiated based on game being played
  * Bet object is instantiated 
* Player bets $20 in slot machine game
  * User clicks $20 bet button
  * Bet information passed to bet object
  * GUI updated to display bet amount
  * Player checks that the bankroll has sufficient funds
    * Throws exception if not
    * Otherwise, reduces bankroll and bankroll display updates
  * Money removed from player bankroll
  * Bankroll display is updated
* Player plays round of slot machine game after betting $20 and loses
  * User clicks PlayRound button
  * Game plays round and returns outcome
  * Outcome displayed by GUI
  * Bet checks the outcome for payout
  * No payout found, so no GUI update
* Player plays round of slot machine game after betting $20 and wins
  * User clicks PlayRound button
  * Game plays round and returns outcome
  * Outcome displayed by GUI
  * Bet checks the outcome for payout
  * Payout amount is returned and added to Player bankroll
  * Update is communicated to GUI and displayed
* Player clears bet after betting money in slot machine game
  * User clicks ClearBet button
  * Bet object updated to hold no current bet
  * GUI updates to display $0 current bet
* Player chooses to play roulette
  * User clicks button representing roulette in menu display
  * GameTable implementation for roulette is instantiated
  * New Player object is instantiated with a certain bankroll and passed to the GameTable
  * Bet buttons and Play buttons are instantiated
* Player bets $10 on “3” in the game of roulette and wins
  * The user clicks on bet and clicks on cell “3” and selects an amount of $10
  * The backend checks that the bankroll is at least the bet amount
    * If not, the player cannot proceed and an exception is returned for insufficient balance. The following steps would not execute
  * The bankroll amount is reduced by the amount of the bet
  * The user clicks on Play button
  * In the backend, the RouletteGame class determines what number the ball should land on. The roulette wheel spins and the ball lands on a number “3”.
* Player bets $10 on “3” in the game of roulette and loses
  * The user clicks on bet and clicks on cell “3” and selects an amount of $10
    * If not, the player cannot proceed and an exception is returned for insufficient balance. The following steps would not execute
  * The bankroll amount is reduced by the amount of the bet
  * The user clicks on Play button
  * In the backend, the RouletteGame class determines what number the ball should land on. The roulette wheel spins and the ball lands on a number.
  * The user loses the bet. The bankroll does not change since it had already been reduced at the time of the bet.
* Player exits the game of roulette
  * Case A: a bet/play is in progress (for example, if the wheel is spinning)
    * The user cannot click on the Exit button, it would be greyed out.
    * If the user clicks, nothing will happen.
  * Case B: the current bet/play is complete and the bankroll changes are complete
    * The user clicks on “Exit” and is taken back to the main menu.
    * The window is updated to reflect that the user is now on the main many.
* Player plays roulette and loses the entire bankroll
  * The user clicks on Bet and clicks on a cell and selects an amount equal to the entire bankroll.
  * The bankroll amount is reduced by the amount of the bet. It is reduced to 0.
  * The user clicks on Play button
  * In the backend, the RouletteGame class determines what number the ball should land on. The roulette wheel spins and the ball lands on a number.
  * The user loses the bet. The bankroll does not change since it had already been reduced at the time of the bet. Since the bankroll is 0, the player cannot play anymore. A “You lose all your money” message is displayed and the user is returned to the main screen.
