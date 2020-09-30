package breakout.blocks;

import static breakout.PowerupChooser.BALL_POWERUP;

import breakout.Level;

/**
 * A PowerupBlock that drops an ExtraBall powerup when broken
 *
 * @author Alex Chao
 */
public class BallPowerupBlock extends PowerupBlock {

  public BallPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  /**
   * Tells the Level object to make an ExtraBall powerup from the position of this block
   * @param level
   */
  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, BALL_POWERUP);
  }
}
