package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;

public abstract class PowerupBlock extends Block {


  private boolean blockBroken = false;

  public PowerupBlock(int row, int col, int width, int height) {
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
    makePowerup(level);
  }
  public abstract void makePowerup(Level level);
}

