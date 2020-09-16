package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
    myScene = myGame.setupScene(Game.SIZE, Game.SIZE, Game.BACKGROUND);
    stage.setScene(myScene);
    stage.show();
    // find individual items within game by ID (must have been set in your code using setID())
    myPaddle = lookup("#myPaddle").query();
    myBall = lookup("#myBall").query();
  }
  // Can write regular JUnit tests!
  // check initial configuration values of game items set when scene was created

  // check dynamic elements by setting up a specific scenario, "running" the game, then checking for specific results

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
  public void testBallMove () {
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    assertFalse(myBall.getCenterY() < 100);
    press(myScene, KeyCode.SHIFT);
    myGame.step(Game.SECOND_DELAY);
    assertTrue(myBall.getCenterY() < 100);
  }
  @Test
  public void testBallBounce(){
    //launch ball
    myBall.setCenterX(100);
    myBall.setCenterY(100);
    assertFalse(myBall.getCenterY() < 100);
    press(myScene,KeyCode.SHIFT);
    myGame.step(50); // give time to bounce upwards
    assertTrue(myBall.getCenterY() < 50);
    myGame.step(100); // give time to bounce downwards
    assertTrue(myBall.getCenterY()> 100);
  }
  @Test
  public void testBallOutOfBounds(){
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    //ball falls downwards
    myBall.setDirection(0,-1);
    press(myScene,KeyCode.SHIFT);
    myGame.step(Game.SECOND_DELAY*50);
    //ball should  be in starting position
    assertEquals(175, myBall.getCenterX());
    assertEquals(295, myBall.getCenterY());
  }
  @Test
  public void testBallIntoCorner(){
    //set ball in center of screen
    myBall.setCenterX(1);
    myBall.setCenterY(1);
    //set direction towards top left
    myBall.setDirection(-1,1);
    press(myScene, KeyCode.SHIFT);

    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    myGame.step(Game.SECOND_DELAY);

    //check that it rebounds back exactly
    assertEquals(1,myBall.getDirectionX());
    assertEquals(-1,myBall.getDirectionY());
  }
  @Test
  public void testBallBreaksBlock() {
    Block testBlock = lookup("#5,0").query();
    testBlock.setX(150);
    testBlock.setY(105);
    myBall.setCenterY(125);
    press(myScene,KeyCode.SHIFT);
    myBall.setDirection(0, -1);
    sleep(1, TimeUnit.SECONDS);
    myGame.step(Game.SECOND_DELAY);
    sleep(1, TimeUnit.SECONDS);
    myGame.step(Game.SECOND_DELAY);
    sleep(1, TimeUnit.SECONDS);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));

    sleep(1, TimeUnit.SECONDS);




  }
}
