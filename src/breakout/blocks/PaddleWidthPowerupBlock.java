package breakout.blocks;

import static breakout.PowerupChooser.PADDLE_WIDTH_POWERUP;

import breakout.Level;

public class PaddleWidthPowerupBlock extends PowerupBlock {

  public PaddleWidthPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, PADDLE_WIDTH_POWERUP);
  }
}
