package ooga;


import api.Backend.Game;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main (String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        GameScene scene = new GameScene(root, 500, 500, Color.GREEN);
        new Menu(scene);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
