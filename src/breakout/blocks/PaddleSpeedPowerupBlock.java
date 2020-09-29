package breakout.blocks;

import static breakout.PowerupChooser.PADDLE_SPEED_POWERUP;

import breakout.Level;

public class PaddleSpeedPowerupBlock extends PowerupBlock {

  public PaddleSpeedPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public void makePowerup(Level level) {
    level.addPowerupFromBlock(this, PADDLE_SPEED_POWERUP);
  }
}
