package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Key;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
//need to ask about how to adapt these tests for higher levels
public class InputKeyTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Main myGame = new Main();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests
  private Paddle myPaddle;
  private Ball myBall;

  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */
  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Main.SIZE, Main.SIZE, Main.BACKGROUND);
    stage.setScene(myScene);
    stage.show();
    // find individual items within game by ID (must have been set in your code using setID())
    myPaddle = lookup("#myPaddle").query();
    myBall = lookup("#myBall").query();

  }

  @Test
  public void testPause(){
    double initialBallX = myBall.getCenterX(), initialBallY = myBall.getCenterY();
    double initialPaddleX = myPaddle.getX(), initialPaddleY = myPaddle.getY();
    press(myScene, KeyCode.SPACE);
    press(myScene, KeyCode.SHIFT);
    press(myScene, KeyCode.LEFT);
    myGame.step(Main.SECOND_DELAY);
    assertEquals(initialBallX,myBall.getCenterX());
    assertEquals(initialBallY,myBall.getCenterY());
    assertEquals(initialPaddleX, myPaddle.getX());
    assertEquals(initialPaddleY, myPaddle.getY());
    press(myScene, KeyCode.SPACE);
    press(myScene, KeyCode.LEFT);
    myGame.step(Main.SECOND_DELAY);
    assertNotEquals(initialBallX,myBall.getCenterX());
    assertNotEquals(initialBallY,myBall.getCenterY());
    assertNotEquals(initialPaddleX, myPaddle.getX());
  }
  @Test
  public void testResetKey(){
    double initialBallX = myBall.getCenterX(), initialBallY = myBall.getCenterY();
    double initialPaddleX = myPaddle.getX(), initialPaddleY = myPaddle.getY();
    press(myScene, KeyCode.SHIFT);
    press(myScene, KeyCode.LEFT);
    myGame.step(Main.SECOND_DELAY);
    press(myScene, KeyCode.SPACE);
    myGame.step(Main.SECOND_DELAY);
    assertNotEquals(initialBallX,myBall.getCenterX());
    assertNotEquals(initialBallY,myBall.getCenterY());
    assertNotEquals(initialPaddleX, myPaddle.getX());
    press(myScene, KeyCode.R);
    assertEquals(initialBallX,myBall.getCenterX());
    assertEquals(initialBallY,myBall.getCenterY());
    assertEquals(initialPaddleX, myPaddle.getX());
    assertEquals(initialPaddleY, myPaddle.getY());

  }

  @Test
  public void testPowerupDropOnPress(){
    //launch ball
    press(myScene, KeyCode.P);
    Powerup powerup = lookup("#powerup0").query();
    assertEquals(0, powerup.getCenterY());
    double initialX = powerup.getCenterX();
    myGame.step(Main.SECOND_DELAY);
    assertTrue(powerup.getCenterY() > 0);
    assertEquals(initialX, powerup.getCenterX(), .1);
  }
}
