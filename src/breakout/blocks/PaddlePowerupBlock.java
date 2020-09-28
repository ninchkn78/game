package breakout.blocks;

import static breakout.PowerupChooser.PADDLE_POWERUP;

import breakout.Level;

public class PaddlePowerupBlock extends PowerupBlock {

  public PaddlePowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, PADDLE_POWERUP);
  }
}
