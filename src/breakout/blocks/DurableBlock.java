package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;

// TODO: 2020-09-26 add tests for this block 

public class DurableBlock extends Block {

  private int count = 3;
  private boolean blockBroken = false;

  public DurableBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
    this.setFill(Color.color(.5, 0, .5));
  }

  @Override
  public void handleHit(Level level) {
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
    this.count -= 1;
    if (this.count == 0) {
      blockBroken = true;
    }
  }

  @Override
  public boolean isBlockBroken() {
    return blockBroken;
  }
}
