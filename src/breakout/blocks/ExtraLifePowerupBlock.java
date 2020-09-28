package breakout.blocks;

import static breakout.PowerupChooser.EXTRA_LIFE_POWERUP;

import breakout.Level;


public class ExtraLifePowerupBlock extends PowerupBlock {

  public ExtraLifePowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public void makePowerup(Level level){
    level.addPowerupFromBlock(this, EXTRA_LIFE_POWERUP);
  }
}
