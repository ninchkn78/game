package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import breakout.blocks.Block;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

public class PowerupTest extends DukeApplicationTest {

  // create an instance of our game to be able to call in tests (like step())
  private final Game myGame = new Game();
  // keep created scene to allow mouse and keyboard events
  private Scene myScene;
  // keep any useful elements whose values you want to test directly in multiple tests
  private Paddle myPaddle;
  private Ball myBall;

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

  @Test
  public void testPaddleWidenPowerup() {
    press(myScene, KeyCode.SHIFT);
    Block powerupBlock = lookup("#1,1").query();
    TestHelperMethods.breakBlock(powerupBlock, myBall, myGame);
    assertEquals(myPaddle.getWidth(), 75);
    Powerup powerup = lookup("#powerup0").query();
    TestHelperMethods.makePaddleHitPowerup(myGame, myPaddle, powerup);
    assertTrue(myPaddle.getWidth() > 75);
  }

  @Test
  public void testPaddleSpeedsUpPowerup() {
    press(myScene, KeyCode.SHIFT);
    Block powerupBlock = lookup("#0,0").query();
    TestHelperMethods.breakBlock(powerupBlock, myBall, myGame);
    double initialPosition = myPaddle.getX();
    press(myScene,KeyCode.LEFT);
    press(myScene, KeyCode.RIGHT);
    assertEquals(initialPosition, myPaddle.getX());
    press(myScene, KeyCode.LEFT);
    Powerup powerup = lookup("#powerup0").query();
    TestHelperMethods.makePaddleHitPowerup(myGame, myPaddle, powerup);
    press(myScene,KeyCode.RIGHT);
    assertNotEquals(initialPosition, myPaddle.getX());
  }

  @Test
  public void testBallSlowDownPowerup() {
    press(myScene, KeyCode.SHIFT);
    Block powerupBlock = lookup("#0,1").query();
    TestHelperMethods.breakBlock(powerupBlock, myBall, myGame);
    double distanceTravelled = calculateDistanceTravelled();
    Powerup powerup = lookup("#powerup0").query();
    TestHelperMethods.makePaddleHitPowerup(myGame,myPaddle, powerup);
    double newDistanceTravelled = calculateDistanceTravelled();
    System.out.println(distanceTravelled);
    System.out.println(newDistanceTravelled);
    assertTrue(newDistanceTravelled < distanceTravelled);

  }

  @Test
  public void testExtraLifePowerup() {
    Text stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 3     Score: 0", stats.getText());
    press(myScene, KeyCode.SHIFT);
    Block powerupBlock = lookup("#1,0").query();
    TestHelperMethods.breakBlock(powerupBlock, myBall, myGame);
    Powerup powerup = lookup("#powerup0").query();
    TestHelperMethods.makePaddleHitPowerup(myGame, myPaddle, powerup);
    stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 4     Score: 1", stats.getText());
  }


  private double calculateDistanceTravelled() {
    myBall.setCenterX(25);
    myBall.setCenterY(275);
    myGame.step(Game.SECOND_DELAY);
    myGame.step(Game.SECOND_DELAY);
    return Math.abs(myBall.getCenterY() - 275);
  }
}
