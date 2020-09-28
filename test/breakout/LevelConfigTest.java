package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import breakout.blocks.Block;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import javafx.scene.input.KeyCode;

//need to ask about how to adapt these tests for higher levels
public class LevelConfigTest extends DukeApplicationTest {

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
    // find individual items within game by ID (must have been set in your code using setID())
    myPaddle = lookup("#myPaddle").query();
    myBall = lookup("#ball0").query();
  }

  // Can write regular JUnit tests!
  // check initial configuration values of game items set when scene was created
  @Test
  public void testPaddleInitialPosition() {
    assertEquals(Game.SIZE/2 - 75/2, myPaddle.getX());
    assertEquals(300, myPaddle.getY());
    assertEquals(75, myPaddle.getWidth());
    assertEquals(10, myPaddle.getHeight());
  }

  @Test
  public void testBallInitialPositionVelocity() {
    assertEquals(350/2, myBall.getCenterX());
    assertEquals(290, myBall.getCenterY());
    assertEquals(5, myBall.getRadius());
    myGame.step(Game.SECOND_DELAY);
    assertEquals(350/2, myBall.getCenterX());
    assertEquals(290, myBall.getCenterY());
    assertEquals(5, myBall.getRadius());
  }

  @Test
  public void testBLocksInitialPositions() {
    Block rowZeroStartBlock = lookup("#0,0").query();
    assertEquals(0, rowZeroStartBlock.getX());
    assertEquals(50, rowZeroStartBlock.getY());
    assertEquals(50, rowZeroStartBlock.getWidth());
    assertEquals(10, rowZeroStartBlock.getHeight());

    Block rowOneStartBlock = lookup("#0,1").query();
    assertEquals(0, rowOneStartBlock.getX());
    assertEquals(65, rowOneStartBlock.getY());
    assertEquals(50, rowOneStartBlock.getWidth());
    assertEquals(10, rowOneStartBlock.getHeight());



  // check dynamic elements by setting up a specific scenario, "running" the game, then checking for specific results

}

  @Test
  public void testLevelDisplay(){
    Text stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 3     Score: 0", stats.getText());
    press(myScene,KeyCode.S);
    stats = lookup("#stats").queryText();
    assertEquals("Level: 1     Lives: 3     Score: 0", stats.getText());
  }
}

