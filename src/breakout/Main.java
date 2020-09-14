package breakout;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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

  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  // some things needed to remember during game
  private Scene myScene;
  private Paddle myPaddle;
  private Ball myBall;
  private Timeline animation;

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
    Group root = new Group();
    LevelConfig config = new LevelConfig();
    config.setUpLevel(1, root);
    myPaddle = config.myPaddle;
    myBall = config.myBall;
    Scene scene = new Scene(root, width, height, background);
    // respond to input
    scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return scene;
  }

  // Handle the game's "rules" for every "moment":
  // - movement, how do things change over time
  // - collisions, did things intersect and, if so, what should happen
  // - goals, did the game or level end?
  void step(double elapsedTime) {
    if (!gamePaused) {
      moveBall(elapsedTime);
    }

  }

  private void handleKeyInput(KeyCode code) {
    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
      if (!gamePaused) {
        myPaddle.movePaddle(code);
        if (!ballLaunched) {
          myBall.moveBallWithPaddle(code);
        }
      }
    }
    if (code.equals(KeyCode.SHIFT)) {
      setBallLaunched(true);
    }
    if (code.equals(KeyCode.SPACE)) {
      setGamePaused();
    }
    if (code.equals(KeyCode.R)) {
      resetGame();
    }
  }

  //how to stop paddle from moving too?
  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched(boolean status) {
    ballLaunched = status;
  }

  private void moveBall(double elapsedTime) {
    if (ballLaunched) {
      myBall.moveBall(elapsedTime);
    }

  }

  private void resetGame() {
    setBallLaunched(false);
    myBall.reset();
    myPaddle.reset();
  }
}

