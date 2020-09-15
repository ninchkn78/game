package breakout;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

  public static final String TITLE = "Breakout";
  public static final int SIZE = 350;
  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final Paint BACKGROUND = Color.AZURE;



  // some things needed to remember during game
  private Scene myScene;
  private Timeline animation;

  private LevelConfig myConfig;
  private GameLogic gameLogic;

  /**
   * Start the program.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Initialize what will be displayed and how it will be updated.
   */
  @Override
  public void start(Stage stage) {
    // attach scene to the stage and display it
    myScene = setupScene(SIZE, SIZE, BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle(TITLE);
    stage.show();
    // attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  // Create the game's "scene": what shapes will be in the game and their starting properties
  Scene setupScene(int width, int height, Paint background) {
    myConfig = new LevelConfig();
    myConfig.setUpLevel(1);
    gameLogic = new GameLogic(myConfig);
    Group root = myConfig.getRoot();
    Scene scene = new Scene(root, width, height, background);
    // respond to input
    scene.setOnKeyPressed(e -> gameLogic.handleKeyInput(e.getCode()));
    return scene;
  }

  // Handle the game's "rules" for every "moment":
  // - movement, how do things change over time
  // - collisions, did things intersect and, if so, what should happen
  // - goals, did the game or level end?

       void step(double elapsedTime) {

            gameLogic.moveBall(elapsedTime);
            gameLogic.checkBallDropsThroughBottom();
            gameLogic.checkCollision();


        }

    }



