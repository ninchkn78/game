package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import breakout.blocks.Block;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

//need to ask about how to adapt these tests for higher levels
public class GameStepTest extends DukeApplicationTest {

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

  // check dynamic elements by setting up a specific scenario, "running" the game, then checking for specific results

  public void breakBlock(Block block) {
    block.setX(150);
    block.setY(205);
    myBall.setCenterY(225);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, -1);
    myGame.step(Game.SECOND_DELAY);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
  }

  @Test
  public void testPaddleMove() {
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

  //add when ballLaunched is false?
  @Test
  public void testBallMoveWithPaddle() {
    //when ball hasn't been launched
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    press(myScene, KeyCode.LEFT);
    assertEquals(95, myBall.getCenterX());
    assertEquals(100, myBall.getCenterY());
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    press(myScene, KeyCode.RIGHT);
    assertEquals(105, myBall.getCenterX());
    assertEquals(100, myBall.getCenterY());
    //after ball has been launched
    press(myScene, KeyCode.SPACE);
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    press(myScene, KeyCode.LEFT);
    assertEquals(100, myBall.getCenterX());
    assertEquals(100, myBall.getCenterY());
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    press(myScene, KeyCode.RIGHT);
    assertEquals(100, myBall.getCenterX());
    assertEquals(100, myBall.getCenterY());

  }

  @Test
  public void testBallMove() {
    myBall.setCenterX(200);
    myBall.setCenterY(200);
    assertFalse(myBall.getCenterY() < 200);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, -1);
    myGame.step(Game.SECOND_DELAY);
    assertTrue(myBall.getCenterY() < 200);
  }

  @Test
  public void testBallBounce() {
    //launch ball
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    assertFalse(myBall.getCenterY() < 100);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(-1, 0);
    myGame.step(50); // give time to bounce upwards
    assertTrue(myBall.getCenterX() > 50);
    myGame.step(100); // give time to bounce downwards
    assertTrue(myBall.getCenterX() < 100);
  }

  @Test
  public void testBallOutOfBounds() {
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    //ball falls downwards
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, 1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 50));
    //ball should  be in starting position
    myBall = lookup("#ball0").query();
    assertEquals(175, myBall.getCenterX());
    assertEquals(290, myBall.getCenterY());
  }

  @Test
  public void testBallIntoCorner() {
    myBall.setCenterX(2);
    myBall.setCenterY(2);
    //set direction towards top left
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(-1, -1);
    myGame.step(Game.SECOND_DELAY);
    myGame.step(Game.SECOND_DELAY);
    myGame.step(Game.SECOND_DELAY);
    //check that it rebounds back exactly
    assertEquals(-1, myBall.getDirectionX());
    assertEquals(1, myBall.getDirectionY());
  }

  @Test
  public void testBallBreaksBlock() {
    Block testBlock = lookup("#0,0").query();
    assertFalse(testBlock.isBlockBroken());
    breakBlock(testBlock);
    assertTrue(testBlock.isBlockBroken());
  }

  @Test
  public void testGameWon() {
    // TODO: check this test
    List<Block> allBlocks = new ArrayList<>();
    allBlocks.add(lookup("#0,0").query());
    allBlocks.add(lookup("#0,1").query());
    allBlocks.add(lookup("#1,0").query());
    allBlocks.add(lookup("#1,1").query());
    for (Block testBlock : allBlocks) {
      breakBlock(testBlock);
    }
    lookup("#WonText").query();
  }

  @Test
  public void testPowerupDropsOnBrokenBlock() {
    Block powerupBlock = lookup("#1,1").query();
    breakBlock(powerupBlock);
    lookup("#powerup0").query();
  }

  @Test
  public void testScoreUpdates() {
    Block basicBlock = lookup("#0,0").query();
    breakBlock(basicBlock);
    sleep(1000);
    Text stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 3     Score: 1", stats.getText());

  }

  @Test
  public void testLivesUpdate() {
    Text stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 3     Score: 0", stats.getText());
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    //ball falls downwards
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, 1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 200));
    stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 2     Score: 0", stats.getText());
  }

  @Test
  public void testGameLost() {
    //first drop
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, 1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 200));
    //second drop
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, 1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 200));
    //third drop
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    press(myScene, KeyCode.SHIFT);
    myBall.setDirection(0, 1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 200));
    //check loss displays
    lookup("#lostText").query();
  }
}
