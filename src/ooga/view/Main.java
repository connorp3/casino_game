package ooga.view;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {


    public static void main (String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        GameScene scene = new GameScene(root, 750, 750);
        new Menu(scene);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}