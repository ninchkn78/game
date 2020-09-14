package breakout;

import javafx.scene.shape.Rectangle;

public abstract class Block extends Rectangle {

  public Block(int row, int col, int width, int height) {
    super(row, col, width, height);
  }

  public abstract void handleHit();
}

