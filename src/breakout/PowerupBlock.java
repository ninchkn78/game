package breakout;

import javafx.scene.paint.Color;

public class PowerupBlock extends Block {


  private boolean blockHit = false;
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
    dropPowerup(level);
  }
  private void dropPowerup(Level level){
    level.addPowerupFromBlock(this);
  }
}

