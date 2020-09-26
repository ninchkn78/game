package breakout;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
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
  @Override
  public void start(Stage stage) {
    // create game's scene with all shapes in their initial positions and show it
    myScene = myGame.setupScene();
    stage.setScene(myScene);
    stage.show();
    // find individual items within game by ID (must have been set in your code using setID())
    myPaddle = lookup("#myPaddle").query();
    myBall = lookup("#ball1").query();
  }
  @Test
  public void testPaddleWidenPowerup() {
    press(myScene, KeyCode.SHIFT);
    press(myScene, KeyCode.P);
    Powerup powerup = lookup("#powerup0").query();
    powerup.setCenterX(200);
    powerup.setCenterY(210);
    myPaddle.setX(190);
    myPaddle.setY(200);
    myPaddle.setWidth(75);
    assertTrue(myPaddle.getWidth() == 75);
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    javafxRun(() -> myGame.step(Game.SECOND_DELAY));
    assertTrue(myPaddle.getWidth() > 75);
  }
}
