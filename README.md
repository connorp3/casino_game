final
====

This project implements a player for multiple related games.

Names:


### Timeline

Start Date: 4/1/20

Finish Date: 4/24/20

Hours Spent:

Connor:

- Plan: 14 hours
- 4/4/20: 2 hours (Writing basic JavaFX code to make scene and some buttons)
- 4/4/20: 2.5 hours (Learning new JavaFX skills to debug code for desired functionality)
- 4/5/20: 2.5 hours (Creating a loop to generate buttons in Menu using properties file)
- 4/5/20: 1 hour (Plan presentation)
- 4/6/20: 2.5 hours (Added Player and GameBoard classes (GameBoard extremely hard-coded currently))
- 4/8/20: 3 hours (Planning interaction between frontend/backend, team meeting, basic implementation of frontend buttons/display)
- 4/9/20: 3.5 hours (Integrating frontend and backend, debugging after integration, writing tests)
- 4/11/20: 0.5 hours (Investigating reflection tools relevant to frontend)
- 4/12/20: 2 hours (Office hours to discuss using reflection, Sprint 1 presentation)
- 4/13/20: 1.5 hours (Implementing reflection for the Menu, creating data files to do so)
- 4/14/20: 2.5 hours (Creating RouletteBoard for the frontend display, attempting to apply reflection to GameTable)
- 4/15/20: 2 hours (Implementing dark mode using CSS files)
- 4/16/20: 3.5 hours (Working through Roulette betting implementation, team meeting, writing bet button tests)
- 4/18/20: 45 min (Shifting bet button implementation to GameTable class)
- 4/19/20: 2 hours (Sprint 2 Presentation, Shifting GameTable elements to data files)
- 4/20/20: 2 hours (Shifting GameTable elements to data files)
- 4/21/20: 3 hours (Refactoring GameTable even further using reflection, making switching games possible, Handling exceptions)
- 4/22/20: 3 hours (Make Spanish language an option, Make parser for images, Remove magic values, Rerun tests)
- 4/23/20: 4 hours (Abstracting away reflection parsing for View classes, adding gameMode choicebox, setting up frontend for load and save game)
- 4/24/20: 2 hours (Eliminating magic values, writing tests for exceptions and creating test data files, making sure tests work)
### Primary Roles

Connor: Design, implementation, and testing of frontend

### Resources Used

Stack Overflow
Oracle
TA's
Java Tutorials

### Running the Program

Main class: Main

Data files needed:

    - MenuProperties
        - MenuGames
        - RouletteReflection
        - SlotMachineReflection
    - GameTableProperties
        - AdminButtons
        - BetButtons
        - GameTableButtons
        - GameTableDisplays
        - GameTableLayout
    - RouletteGameModes
        - american
        - betType
        - default
    - SlotMachineGameModes
        - default
        - default_view

Features implemented:

- Two example games: Roulette and Slots
- One variation: Different numbers of wheels and symbols in slot machines
- Dark Mode
- Load and Save Game


### Notes/Assumptions

Assumptions or Simplifications:

- Because Casino games almost always occur in discrete rounds, we assumed that a player would simply need to save their money
at any given time in order to save the progress of the game. Then they can reenter the game and load their money where they left off,
and simply enter the game again and choose the game or variation they wish to play.

Interesting data files:

- RouletteReflection and SlotReflection use reflection to properly select game classes
- GameTableProperties data files use reflection to create buttons for game display


Known Bugs:

Extra credit:
- NA


### Impressions

Connor: I spent a very long time working on this project, and I feel like I learned a lot about JavaFX, Reflection, and Data Driven 
Design because of it. Neither me nor Alex were very skilled at JavaFX, so I took this responsibility to challenge myself. I did a lot
of research and learning by doing to implement the frontend, and I think I ended up with a highly data driven design (although not 
as data driven as it could have been). It would have been nice to not have this due over reading period, though, as I feel like I had to
sacrifice time to study for my finals.


