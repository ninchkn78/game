package breakout.blocks;

import breakout.Level;

/**
 * A Block that breaks on a single hit with no other special characteristics
 *
 * @author Alex Chao
 */
public class BasicBlock extends Block {

  private boolean blockBroken = false;

  public BasicBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public boolean isBlockBroken() {
    return blockBroken;
  }

  /**
   * Sets the block to be broken on one hit
   * @param level
   */
  @Override
  public void handleHit(Level level) {
    blockBroken = true;
  }
}

