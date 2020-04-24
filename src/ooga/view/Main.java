package ooga.view;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int SCENE_WIDTH = 1500;
    private static final int SCENE_HEIGHT = 800;

    public static void main (String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        GameScene scene = new GameScene(root, SCENE_WIDTH, SCENE_HEIGHT);
        new Menu(scene);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
