package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;

public class BasicBlock extends Block {

  private boolean blockBroken = false;

  public BasicBlock(int row, int col, int width, int height) {
    super(row, col, width, height);
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
  }
  @Override
  public boolean isBlockBroken(){
    return blockBroken;
  }
  @Override
  public void handleHit(Level level) {
    blockBroken = true;
  }
  }

