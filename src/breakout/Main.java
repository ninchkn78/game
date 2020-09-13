package breakout;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public static final String TITLE = "Breakout";
    public static final int SIZE = 400;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.AZURE;
    public static final Paint HIGHLIGHT = Color.OLIVEDRAB;
    public static final Paint RACER_COLOR = Color.HOTPINK;


    // some things needed to remember during game
    private Scene myScene;
    private Paddle myPaddle;

    /**
     * Initialize what will be displayed and how it will be updated.
     */
    @Override
    public void start (Stage stage) {
        // attach scene to the stage and display it
        myScene = setupScene(SIZE, SIZE, BACKGROUND);
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    // Create the game's "scene": what shapes will be in the game and their starting properties
    Scene setupScene (int width, int height, Paint background) {
        // create one top level collection to organize the things in the scene
        Group root = new Group();
        // make some shapes and set their properties
        LevelConfig config = new LevelConfig();
        config.setUpLevel(1,root);
        myPaddle = config.myPaddle;
        Scene scene = new Scene(root, width, height, background);
        // respond to input
        scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return scene;
    }

    // Handle the game's "rules" for every "moment":
    // - movement, how do things change over time
    // - collisions, did things intersect and, if so, what should happen
    // - goals, did the game or level end?
    void step (double elapsedTime) {

    }

    private void handleKeyInput (KeyCode code) {

        // NEW syntax with Java 13 that some prefer over IF statements


    }
    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}

