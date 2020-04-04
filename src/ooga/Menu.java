package ooga;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class Menu {

    private Group menuRoot;
    private Group myButtons;

    public Menu(Group root) {
        menuRoot = root;
        makeGameButtons();
        //access resource bundle
    }

    private void makeGameButtons() {
        myButtons = new Group();
        Button button = new Button();
        myButtons.getChildren().add(makeButton());  //This will eventually (hopefully) become a loop that adds buttons for each game
        menuRoot.getChildren().add(myButtons);
    }

    private Button makeButton() { //This will need to access a properties file and determine the proper settings for each game button
        Button SlotMachineButton = new Button("Slot Machine");
        SlotMachineButton.setOnAction(event -> setUpGame());
        SlotMachineButton.setId("SlotMachineButton");
        return SlotMachineButton;
    }

    private void setUpGame() { //should take argument that tells it which game it should set up
        menuRoot.getChildren().remove(myButtons);
        Group gamePlayRoot = new Group();
        new GameTable(gamePlayRoot);
        menuRoot.getChildren().add(gamePlayRoot);


    }
}
