package breakout.blocks;

import static breakout.PowerupChooser.BALL_POWERUP;

import breakout.Level;


public class BallPowerupBlock extends PowerupBlock {

  public BallPowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public void makePowerup(Level level){
    level.addPowerupFromBlock(this, BALL_POWERUP);
  }
}
