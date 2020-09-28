package breakout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */

  public void breakBlock(Block block){
    block.setX(150);
    block.setY(205);
    myBall.setCenterY(225);
    myBall.setCenterX(150);
    myBall.setDirection(0, -1);
    myGame.step(Game.SECOND_DELAY);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
  }

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
    breakBlock(powerupBlock);
    assertTrue(myPaddle.getWidth() == 75);
    makePaddleHitPowerup();
    assertTrue(myPaddle.getWidth() > 75);
  }
  @Test
  public void testBallSlowDownPowerup() {
    press(myScene, KeyCode.SHIFT);
    Block powerupBlock = lookup("#0,1").query();
    breakBlock(powerupBlock);
    double distanceTravelled = calculateDistanceTravelled();
    makePaddleHitPowerup();
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
    breakBlock(powerupBlock);
    makePaddleHitPowerup();
    stats = lookup("#stats").queryText();
    assertEquals("Level: 0     Lives: 4     Score: 1",stats.getText());
  }

  private void makePaddleHitPowerup() {
    Powerup powerup = lookup("#powerup0").query();
    powerup.setCenterX(200);
    powerup.setCenterY(210);
    myPaddle.setX(190);
    myPaddle.setY(200);
    myPaddle.setWidth(75);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
  }
  private double calculateDistanceTravelled(){
    myBall.setCenterX(25);
    myBall.setCenterY(275);
    myGame.step(Game.SECOND_DELAY);
    myGame.step(Game.SECOND_DELAY);
    return Math.abs(myBall.getCenterY() - 275);
  }
}
