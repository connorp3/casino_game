# DESIGN_PLAN.md


## Introduction
This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project 
(i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). 
Discuss the design at a high-level (i.e., without referencing specific classes, data structures, or code).
* We want to create a casino themed software game that supports the easy addition/variation of several casino games such as Black Jack, Slot Machines, Roulette, etc.  
* We would like this program to be most flexible in adding new games to the program, as often casino games are quite similar in structure (Bet, Random Event, Outcome)  
* We would also like it to be somewhat flexible in the layout of the GUI (choosing light vs dark mode or color preferences)  
* We would like our interface/abstract class that progresses any game to be closed (casino game progress in very similar ways  
* We would like many features of the GUI to be open to change based on the game that is being played or the style selections of the user  
* We plan to make the implementation of different rules for each casino game open, but within an inheritance hierarchy  
* High Level Design:
  * We will be creating an MVC-based program
  * There will be a menu with different game options
  * Clicking one instantiates a new view class with a game engine as a parameter, and this game engine will take specific game object parameters  (inheritance hierarchies) for the certain game
  * View will have general formatting with game specific features (possibly abstract class)
  * Progression of game will be inputted into view and passed to game engine through method calls
  * View will take important game information from game objects for display


## Overview  
This section serves as a map of your design for other programmers to gain a general 
understanding of how and why the program was divided up, and how the individual 
parts work together to provide the desired functionality. Describe specific modules 
you intend to create, their purpose with regards to the program's functionality, and 
how they collaborate with each other, focusing specifically on each one's API. 
Include a picture of how the modules are related (these pictures can be hand drawn and 
scanned in, created with a standard drawing program, or screen shots from a UML 
design program). Discuss specific classes, methods, and data structures, but 
not individual lines of code.  

Overview: 
![alt text](doc/plan/pics/crc/overview.JPG "Overview")  

CRC 1: 
![alt text](doc/plan/pics/crc/crc1.JPG "Card 1")  

CRC 2: 
![alt text](doc/plan/pics/crc/crc2.JPG "Card 2")  

CRC 3: 
![alt text](doc/plan/pics/crc/crc3.JPG "Card 3") 

CRC 4: 
![alt text](doc/plan/pics/crc/crc4.JPG "Card 4")  

CRC 5: 
![alt text](doc/plan/pics/crc/crc5.JPG "Card 5") 



## Design Details  
This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). Describe how each module's API handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions.  
* GameTable (View) 
  * GameTable is an abstract class that would produce the general layout of the GUI once a game is selected. The idea is that each game will have a very similar visual layout (Bankroll, betting, clear bet, switch game, cash out), which can be represented in this general class
  * Each game would then have a specific implementation of this class based on what game is being played that displays the proper features such as cards or a slot machine or a roulette board and wheel
  * These specific GameTable implementations would work with their specific Game class (non-View) to perform functionality specific to the game
  * This class would handle dark vs. light mode for any game by knowing to choose a dark vs. light CSS and/or properties file for both general game features and game features specific to that game
  * This module is justified because it attempts to take advantage of similarities within the casino game genre using an inheritance hierarchy while still giving the user the ability to implement other games by extending it
* Menu(View)
  * This module, in its basic form will have buttons that generate the proper GameTable and Game given a button click
  * It will also generate the overall game element (The Player) and be able to save or load a game using info from the the Player with the implementation of SaveGame
  * This is justified as a module because it is necessary in order to manage the game flow between games and save/load games in a general way
* Bet (Model)
  * This module will hold different bets for a given game and will learn of the outcome of a game from the Game module
  * This outcome will be compared to the different bets and a certain payout will then be communicated to the player class
  * This is justified, as this class could hold any number of betting outcomes (perhaps as a sub-module inheritance hierarchy) for a specific game, thus creating an extendible class for new games to be added
* Game (backend)
  * API that provides general methods for doing a round of a specific game
  * Method call in a view class would call this method to play a round of game
  * Justified because it exploits generality of all games in Casino happening in discrete rounds
* RouletteGame (backend)
  * Implements Game
  * Contains the data structures for the game of roulette with the different event types and payouts depending on the outcome.
  * Randomizes outcomes
  * Communicates with the Bet class - the player can place multiple bets in roulette and a Bet object would be created for each bet.
* BlackJackGame (backend)
  * Implements Game
  * Contains the data structures and rules for the game of blackjack
  * Knows dealer hand and player hand
  * Can deal cards randomly and shuffle the deck
  * Checks for a bust if the value of the  cards is greater than 21
  * Communicates with Bet, GameTable, and Hand.
* SlotMachineGame (backend)
  * Implements Game
  * Contains the data structures and rules for the game of slot machine
  * Can generate outcomes based on spinning the wheel
  * Communicates with GameTable and Bet
* Player (backend)
  * Keeps track of the money the player has. Is initialized with an amount of money that can only change through playing casino games.
  * Communicates the amount of money to the view 
  * Public methods available to change the amount of money the player has
  * Communicates with GameTable and the Bet class
* Hand (backend)
  * Responsibility is to hold cards of the player
  * Only be used for Blackjack or card games
  * Hold Card objects for the hand of the player, can have multiple hards
  * Communicates that information to the BlackJackGame class
* SaveGame
  * Accesses data classes from model and converts them to proper file format
  * Justified because it can be made general enough to save gametype and bankroll for any given casino game
* LoadGame
  * Accesses game files and passes the information to the menu to instantiate the proper game with the correct bankroll
  * Justified because information passed to the menu can be handled in a general way in the GUI

## Example games  

Describe three example games that differ significantly in detail. Clearly identify how the functional differences in these games is supported by your design. Use these examples to help clarify the abstractions in your design.


* Roulette
  * Popular casino game. In the game, players may choose to place bets on either a single number, various groupings of numbers, the colors red or black, whether the number is odd or even, or if the numbers are high (19–36) or low (1–18). To determine the winning number and color, a croupier spins a wheel in one direction, then spins a ball in the opposite direction around a tilted circular track running around the outer edge of the wheel. The ball eventually loses momentum, passes through an area of deflectors, and falls onto the wheel and into one of 37 colored and numbered pockets on the wheel. The winnings are then paid to the player who has placed a successful bet.
  * The functional differences in the design are supported by the RouletteGame class (backend) and RouletteGameTable (view) which can handle the logic and views respectively.
* Slot machine
  * Its layout features a screen displaying three reels that spin when the game is activated. Uses a random number generator.
  * The functional differences in the design are supported by the SlotMachineGame and SlotMachineGameTable classes to handle the backend and frontend respectively. The odds are different in slot machine games than in the other casino games. The SlotMachineGameTable would support the implementation of the 3 reels that are shown to the user. The class also managed inputs.
  * It is necessary to handle the implementation of the backend in a SlotMachineGame class because the game of slot machines is quite unique and nothing like the other games. We could not determine how to create an inheritance hierarchy to support this.
* Blackjack
  * Black Jack is a popular casino card game in which the player plays against the dealer to see who can get closest to 21 without going over
  * Our GameTable hierarchy would support unique implementation of cards that are displayed to the User
  * Our GameTable hierarchy would also support the progression of the a round of Black Jack which is unique to how black jack runs
  * Our Bet hierarchy would be a general framework that could support the outcomes associated with Black Jack given a certain bet (basically three outcomes with distinct payouts: Win, Loss, or Tie)
  * The implementation of a BlackJack game class would be necessary to handle the specific progression of the round of Black Jack. This round progression is quite unique to Black Jack, and we could not determine at the moment how to create an inheritance hierarchy to support this.

## Design Considerations
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. Include any design decisions discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design.
* We initially wanted to create some sort of general GameEngine class that could be implemented for any given game to have functionality for playing a round of that game. The idea with this is that it would allow the view class to call general methods on any specific GameEngine when certain buttons are clicked. However, we realized while thinking through this that the progression of each game within a round of that game is quite unique. The main commonality between these games involves betting on an outcome from the round, deciding if the player wins given the outcome, and then changing the player’s bankroll. This is what we are trying to capture in our current design with general Player and Bet modules
* We need to address how we are going to implement each specific GUI for the different games, as the display and functionality of these will be quite specific to the game. We have a general idea of the functionality for each GUI, but there are details specific to each GUI that we did not completely
