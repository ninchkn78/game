package breakout.blocks;

import static breakout.PowerupChooser.PADDLE_SPEED_POWERUP;

import breakout.Level;

/**
 * A PowerupBlock that drops a PaddleSpeed powerup when broken
 *
 * @author Alex Chao
 */

public class PaddleSpeedPowerupBlock extends PowerupBlock {

  public PaddleSpeedPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  /**
   * Tells the Level object to make a PaddleSpeed powerup from the position of this block
   * @param level
   */
  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, PADDLE_SPEED_POWERUP);
  }
}
