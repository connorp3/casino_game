package ooga;

import javafx.scene.Group;
import javafx.scene.control.Button;


public class GameTable {
    Group myRoot;
    public GameTable(Group root) {
        myRoot = root;
        Button MainMenuButton = new Button("Main Menu");
        MainMenuButton.setOnAction(event -> returnToMainMenu());
        MainMenuButton.setId("Main Menu");
        myRoot.getChildren().add(MainMenuButton);
    }

    private void returnToMainMenu() {
        myRoot.getChildren().removeAll();
        Group menuRoot = new Group();
        myRoot.getChildren().add(menuRoot);
        new Menu(menuRoot);

    }
}
