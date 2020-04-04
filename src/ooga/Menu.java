package ooga;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Menu {

    private GridPane menuRoot;
    private SceneChanger myScene;

    public Menu(SceneChanger scene) {
        myScene = scene;
        menuRoot = new GridPane();
        scene.setRoot(menuRoot);
        menuRoot.setAlignment(Pos.CENTER);
        menuRoot.setHgap(10);
        menuRoot.setVgap(10);
        makeGameButtons();
        //access resource bundle
    }

    public void makeGameButtons() {
        menuRoot.add(makeButton(), 0,0);
        menuRoot.add(makeButton(), 0,1);//This will eventually (hopefully) become a loop that adds buttons for each game
    }

    private Button makeButton() { //This will need to access a properties file and determine the proper settings for each game button
        Button SlotMachineButton = new Button("Slot Machine");
        SlotMachineButton.setOnAction(event -> setUpGame());
        SlotMachineButton.setId("SlotMachineButton");
        return SlotMachineButton;
    }

    private void setUpGame() { //should take argument that tells it which game it should set up
        menuRoot.getChildren().clear();
        new GameTable(myScene);


    }
}
