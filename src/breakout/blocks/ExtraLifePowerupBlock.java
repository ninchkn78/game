package breakout.blocks;

import static breakout.PowerupChooser.EXTRA_LIFE_POWERUP;

import breakout.Level;

/**
 * A PowerupBlock that drops an ExtraLife powerup when broken
 *
 * @author Alex Chao
 */
public class ExtraLifePowerupBlock extends PowerupBlock {

  public ExtraLifePowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  /**
   * Tells the Level object to make an ExtraLife powerup from the position of this block
   * @param level
   */
  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, EXTRA_LIFE_POWERUP);
  }
}
