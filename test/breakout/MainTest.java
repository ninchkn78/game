package breakout;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.concurrent.TimeUnit;
import util.DukeApplicationTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.util.WaitForAsyncUtils.sleep;


public class LevelConfigTest extends DukeApplicationTest {
  // create an instance of our game to be able to call in tests (like step())
  private final Main myGame = new Main();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests
  private Paddle myPaddle;


  /**
   * Start special test version of application that does not animate on its own before each test.
   *
   * Automatically called @BeforeEach by TestFX.
   */
  @Override
  public void start (Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene(Main.SIZE, Main.SIZE, Main.BACKGROUND);
    stage.setScene(myScene);
    stage.show();

    // find individual items within game by ID (must have been set in your code using setID())
    myPaddle = lookup("#myPaddle").query();
  }


  // Can write regular JUnit tests!
  // check initial configuration values of game items set when scene was created
  @Test
  public void testInitialPositions () {
    // GIVEN, start of the game
    // WHEN, no events have happened
    // THEN, check elements are correctly positioned (to as much detail as yuo need)

    assertEquals(200, myPaddle.getX());
    assertEquals(300, myPaddle.getY());
    assertEquals(75, myPaddle.getWidth());
    assertEquals(10, myPaddle.getHeight());

    sleep(1, TimeUnit.SECONDS);    // PAUSE: not typically recommended in tests, but helps "see" results
  }

  // check dynamic elements by setting up a specific scenario, "running" the game, then checking for specific results

  @Test
  public void testPaddleMove () {
    // GIVEN, mover is at some position in the scene
    myPaddle.setX(100);
    myPaddle.setY(100);
    //CHECK Left
    press(myScene, KeyCode.LEFT);
    assertEquals(95, myPaddle.getX());
    assertEquals(100, myPaddle.getY());
    myPaddle.setX(100);
    myPaddle.setY(100);
    press(myScene, KeyCode.RIGHT);
    assertEquals(105, myPaddle.getX());
    assertEquals(100, myPaddle.getY());
  }




}
