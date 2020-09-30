package breakout.blocks;

import static breakout.PowerupChooser.PADDLE_WIDTH_POWERUP;

import breakout.Level;

/**
 * A PowerupBlock that drops a PaddleWith powerup when broken
 *
 * @author Alex Chao
 */
public class PaddleWidthPowerupBlock extends PowerupBlock {

  public PaddleWidthPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  /**
   * Tells the Level object to make a PaddleWidth powerup from the position of this block
   * @param level
   */
  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, PADDLE_WIDTH_POWERUP);
  }
}
