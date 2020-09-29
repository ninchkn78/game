package breakout;

import breakout.blocks.Block;
import util.DukeApplicationTest;

public class TestHelperMethods extends DukeApplicationTest {

  public static void makePaddleHitPowerup(Game game, Paddle paddle, Powerup powerup) {
    powerup.setCenterX(200);
    powerup.setCenterY(210);
    paddle.setX(150);
    paddle.setY(215);
    paddle.setWidth(75);
    javafxRun(() -> game.step(Game.SECOND_DELAY));
    javafxRun(() -> game.step(Game.SECOND_DELAY));
  }

  /**
   * Start special test version of application that does not animate on its own before each test.
   * <p>
   * Automatically called @BeforeEach by TestFX.
   */

  public static void breakBlock(Block block, Ball ball, Game game) {
    block.setX(150);
    block.setY(205);
    ball.setCenterY(225);
    ball.setCenterX(150);
    ball.setDirection(0, -1);
    game.step(Game.SECOND_DELAY);
    javafxRun(() -> game.step(Game.SECOND_DELAY));
    javafxRun(() -> game.step(Game.SECOND_DELAY));
  }
}
