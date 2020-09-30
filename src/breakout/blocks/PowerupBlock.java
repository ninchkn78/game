package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;

/**
 *A subclass of Block
 *
 * Abstract class that provides a template for making PowerupBlocks which will drop a specific
 * type of powerup when broken
 *
 * @author Alex Chao
 */

public abstract class PowerupBlock extends Block {


  private boolean blockBroken = false;

  public PowerupBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
  }

  @Override
  public boolean isBlockBroken() {
    return blockBroken;
  }

  /**
   * If the block is hit, it is set to be broken and it makes a poweurp
   * @param level
   */
  @Override
  public void handleHit(Level level) {
    blockBroken = true;
    makePowerup(level);
  }

  /**
   * Functionality for this method changes depending on which type of PowerupBlock it is. It makes the
   * corresponding Powerup
   * @param level
   */
  public abstract void makePowerup(Level level);
}

