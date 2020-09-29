package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import breakout.blocks.Block;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

//need to ask about how to adapt these tests for higher levels
public class LevelTwoTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Game myGame = new Game();
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
    myScene = myGame.setupScene();
    stage.setScene(myScene);
    stage.show();
    press(myScene,KeyCode.DIGIT2);
  }

  // Can write regular JUnit tests!
  // check initial configuration values of game items set when scene was created
  @Test
  public void testPaddleInitialPosition() {
    myPaddle = lookup("#myPaddle").query();
    assertEquals(Game.SIZE / 2 - 75 / 2, myPaddle.getX());
    assertEquals(25, myPaddle.getY());
    assertEquals(75, myPaddle.getWidth());
    assertEquals(10, myPaddle.getHeight());
  }

  @Test
  public void testBallInitialPositionVelocity() {
    myBall = lookup("#ball0").query();
    assertEquals(350 / 2, myBall.getCenterX());
    assertEquals(45, myBall.getCenterY());
    assertEquals(5, myBall.getRadius());
    myGame.step(Game.SECOND_DELAY);
    assertEquals(350 / 2, myBall.getCenterX());
    assertEquals(45, myBall.getCenterY());
    assertEquals(5, myBall.getRadius());
  }

  @Test
  public void testBLocksInitialPositions() {
    Block rowZeroStartBlock = lookup("#4,15").query();
    assertEquals(208, rowZeroStartBlock.getX());
    assertEquals(275, rowZeroStartBlock.getY());
    assertEquals(42, rowZeroStartBlock.getWidth());
    assertEquals(10, rowZeroStartBlock.getHeight());

    Block rowOneStartBlock = lookup("#3,15").query();
    assertEquals(156, rowOneStartBlock.getX());
    assertEquals(275, rowOneStartBlock.getY());
    assertEquals(42, rowOneStartBlock.getWidth());
    assertEquals(10, rowOneStartBlock.getHeight());
  }
  @Test
  public void testPowerupFallsUp() {
    myBall = lookup("#ball0").query();
    Block powerupBlock = lookup("#3,14").query();
    press(myScene,KeyCode.SHIFT);
    TestHelperMethods.breakBlock(powerupBlock, myBall, myGame);
    Powerup powerup = lookup("#powerup0").query();
    double powerupYPos = powerup.getCenterY();
    myGame.step(Game.SECOND_DELAY);
    myGame.step(Game.SECOND_DELAY);
    assertTrue(powerup.getCenterY() < powerupYPos);

  }

  @Test
  public void testLevelDisplay() {
    Text stats = lookup("#stats").queryText();
    assertEquals("Level: 2     Lives: 3     Score: 0", stats.getText());
  }
}

