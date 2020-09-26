package breakout;

import javafx.scene.shape.Rectangle;

//should a block have access to the level ?

public abstract class Block extends Rectangle {
  public final static int Y_DISPLACEMENT = 5;
  public final static int X_DISPLACEMENT = 10;
  public final static int SPACE_FROM_TOP = 50;
  private int xPos;
  private int yPos;

  public Block(int row, int col, int width, int height) {
    super(row * (width + X_DISPLACEMENT),
        col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP,
        width,
        height);
    xPos = row * (width + X_DISPLACEMENT);
    yPos = col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP;

  }

  public int getxPos() {
    return xPos;
  }
  public int getyPos() {
    return yPos;
  }

  public abstract void handleHit(Level level);

  public abstract boolean isBlockBroken();

  //bomb brick gets its neighbors when created
}

