####Project Contributors

Connor Penny
Alex Angel

Connor Penny: Design, implementation, and testing of frontend  
Alex Angel: Model, Controller, testing of backend, design

####What are the project's design goals, specifically what kinds of new features did you want to make easy to add


We wanted to create a casino themed software game that supports the easy addition/variation of several casino games such as Slot Machines and Roulette. 


Given the extremely similar structure of most casino games (i.e Player bets on outcome, outcome is generated, player accrues payout), we wished to make a general framework that supported this particular game structure, and was only open to modification in the variation of the specific outcome and payout generated for the user. This is the essence of the difference between all casino games.


The general frontend display for any given game also supports easy additions of gameplay buttons that call Controller methods simply by adding information to a data file (this is done through reflection), and this makes the display quite generalizable to any implemented controller


It is also possible to change language settings in the frontend through data files, but this is not currently displayed as a user choice


Specific casino games also have variations within their own rules (e.g. Slot Machines can have different payout multiples, numbers of wheels that spin, and number of possible symbols displayed), so we wanted to make these features modifiable simply by changing parameters in a data file. Our roulette game also supports variations of roulette such as American, French, and European roulette.


The design allows for the use of different CSS style files for the colors of the GUI, which is what we had in mind when we started the project. We have a default mode and a dark mode that are made possible by this design.


There is also the potential to modify the theme choices within a specific game simply by editing data files (Symbols displayed for slot machine), and this feature could eventually be user chosen, although that is not implemented currently.

####Describe the high-level design of your project, focusing on the purpose and interaction of the core classes


The project is centered around three main parts: the model, the view and the controller.
The model contains the implementations for the various game rules and logic, as well as the Player and Bet classes. The model can save and load the player usernames and bankrolls list by interacting with some files directly. The Controller is a universal class that works for all game types and serves as a link between the front end and the backend. It performs minimal data processing, delegating to the view and model whenever possible. It forwards requests from the view to the model when the user interacts with the GUI and sends the data processed by the view back to the model (like the specific outcome for a game and the new player bankroll after each round).


The frontend has two universal classes that display information and support user interactions common to all casino games. The Menu class is the display seen upon first launching the program. This GUI is where the user is allowed to choose from the games that have been implemented as data files. Upon the creation of a new game in a data file in the proper format, this screen calls upon a MenuGameParser class to read the data and properly build the necessary classes through reflection so that the game can be displayed. This menu also allows the user to choose styles for the game (darkmode) and calls upon Player to load any saved game. 


The GameTable class is created by Menu and creates and displays the general casino game layout that is common among any specific game. It creates and displays all buttons in a data driven way using reflection (It calls upon a parser class to perform the reflection), and so it supports easy modification. Any button needed for gameplay in this class communicates with Controller upon a user clicking it, and tells Controller to perform the necessary actions by communicating with a backend Game interface implementation.


The GameBoard interface is implemented for each game and displays the proper outcome and betting options to the user based on the game being played. This has limited access to the Controller through an interface to communicate bets to the backend. This GameBoard implementation is instantiated with the proper parameters using reflection when a specific game button is clicked in the Menu class.


####What assumptions or decisions were made to simplify your project's design, especially those that affected adding required features


We assumed that the Controller would be a universal class that would not need modifying to accommodate new games. We were able to make this work with our variations of roulette and slot machines, and we think we designed it broadly enough that it could also support other games common to casinos such as keno and horse racing without modifying it.


We assumed that a user would only need to save the amount of the bankroll any time they wanted to save a game because rounds of casino games are short, some being nearly instantaneous. Their money is really their only data on their progress in a casino game, and they are able to return to any game once they have loaded their money, simply by clicking the proper game button and choosing their variations.


We assumed that the user could not choose a username. The username is randomly generated for a new player with the format “player_” followed by five randomly generated digits. This gives the small possibility of a user accidentally overwriting an existing saved game, but this remains an extremely unlikely event for small numbers of players using the software. The reason we implemented it this way was due to the implementation of the GameTable class, which supported easy addition of gameplay buttons in a data driven way that called Controller methods with no parameters. Thus, adding a dialogue box to choose a player name and passing that player name to the controller would have required complex modifications to this process of implementing gameplay buttons that we didn’t have time for.

####Describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline.



Adding games to the project would require creating new implementations of the Game interface in the model package. The controller might need to be modified in some cases but can already accommodate many games out of the box without modifications.


Currently, this software is implemented to the basic specifications of the project (e.g. implementing two example games and one mod). However, for the frontend, with the implementation of new display styles in GameBoard class, new casino games can be created and they can be easily added to data files which will be read by our classes to properly build the game without modification to the general framework of classes or changes to our APIs. Any new game created by the users would need simply to have the properly implemented reflection file in the MenuProperly folder. Once this is implemented, the game will display and run without java code modifications.


New features can be added by expanding specific classes and the data files they read. For example, slot machine games can be loaded with any images the developer chooses simply by changing the data in the default_view class in SlotMachineGameModes folder.


For example, to add a new color scheme as a feature, the user would need to create a new CSS file and update a properties file with its path.

