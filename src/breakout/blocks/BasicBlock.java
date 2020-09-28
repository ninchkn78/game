package breakout.blocks;

import breakout.Level;

public class BasicBlock extends Block {

  private boolean blockBroken = false;

  public BasicBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  @Override
  public boolean isBlockBroken() {
    return blockBroken;
  }

  @Override
  public void handleHit(Level level) {
    blockBroken = true;
  }
}

