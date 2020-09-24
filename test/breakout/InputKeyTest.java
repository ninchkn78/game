package breakout;


import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

import javafx.scene.text.Text;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//need to ask about how to adapt these tests for higher levels
public class InputKeyTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Game myGame = new Game();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests
  private Paddle myPaddle;
  private Ball myBall;
  private Level level;


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
    myBall = lookup("#ball1").query();

  }

  @Test
  public void testPause(){
    double initialBallX = myBall.getCenterX(), initialBallY = myBall.getCenterY();
    double initialPaddleX = myPaddle.getX(), initialPaddleY = myPaddle.getY();
    press(myScene, KeyCode.SPACE);
    press(myScene, KeyCode.SHIFT);
    press(myScene, KeyCode.LEFT);
    myGame.step(Game.SECOND_DELAY);
    assertEquals(initialBallX,myBall.getCenterX());
    assertEquals(initialBallY,myBall.getCenterY());
    assertEquals(initialPaddleX, myPaddle.getX());
    assertEquals(initialPaddleY, myPaddle.getY());
    press(myScene, KeyCode.SPACE);
    press(myScene, KeyCode.LEFT);
    myGame.step(Game.SECOND_DELAY);
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
    myGame.step(Game.SECOND_DELAY);
    press(myScene, KeyCode.SPACE);
    myGame.step(Game.SECOND_DELAY);
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
    press(myScene, KeyCode.SHIFT);
    press(myScene, KeyCode.P);
    Powerup powerup = lookup("#powerup0").query();
    assertEquals(0, powerup.getCenterY());
    double initialX = powerup.getCenterX();
    myGame.step(Game.SECOND_DELAY);
    assertTrue(powerup.getCenterY() > 0);
    assertEquals(initialX, powerup.getCenterX(), .1);
  }
  @Test
  public void testNewBallOnPress(){
    //launch ball
    press(myScene, KeyCode.B);
    press(myScene, KeyCode.B);
    Ball ball = lookup("#ball2").query();
    Ball ball2 = lookup("#ball3").query();

  }
  @Test
  public void testAddLives(){
    //add two lives
    press(myScene,KeyCode.L);
    press(myScene,KeyCode.L);
    Text stats = lookup("#stats").queryText();
    assertEquals("Lives: 5     Score: 0",stats.getText());
  }

  @Test
  public void testRemoveFirstBlock(){
    //test first block in list
    Block testBlock1 = lookup("#0,0").query();
    assertFalse(testBlock1.isBlockBroken()); //check that block is intact
    press(myScene, KeyCode.D);// use removeFirstBlock cheat key
    assertTrue(testBlock1.isBlockBroken()); // check that block broke

    //test second block in list
    Block testBlock2 = lookup("#1,0").query();
    assertFalse(testBlock2.isBlockBroken()); //check that block is intact
    press(myScene, KeyCode.D); // use removeFirstBlock cheat key
    assertTrue(testBlock2.isBlockBroken()); // check that block broke

  }

  @Test
  public void testToggleImmunity(){
    // no immunity
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    //ball falls downwards
    press(myScene,KeyCode.SHIFT);
    myBall.setDirection(0,1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 50));
    //ball should  be in starting position
    myBall = lookup("#ball1").query();
    assertEquals(175, myBall.getCenterX());
    assertEquals(293, myBall.getCenterY());

    // with immunity
    myBall.setCenterX(10);
    myBall.setCenterY(295);
    //ball falls downward
    press(myScene,KeyCode.SHIFT);
    press(myScene,KeyCode.I);
    myBall.setDirection(0,1);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY * 50));
    //ball should bounce directly back upwards
    assertEquals(-1, myBall.getDirectionY() );
  }
}

