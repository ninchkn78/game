package breakout;

import javafx.scene.shape.Rectangle;

//should a block have access to the level ?

public abstract class Block extends Rectangle {

  public Block(int row, int col, int width, int height) {

    super(row, col, width, height);
  }

  public abstract void handleHit();

  public abstract boolean isBlockBroken();

  //bomb brick gets its neighbors when created
}

