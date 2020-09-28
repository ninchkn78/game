package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

//should a block have access to the level ?

public abstract class Block extends Rectangle {

  public final static int Y_DISPLACEMENT = 5;
  public final static int X_DISPLACEMENT = 10;
  public final static int SPACE_FROM_TOP = 50;

  public Block(int row, int col, int width, int height) {
    super(row * (width + X_DISPLACEMENT),
        col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP,
        width,
        height);
    setRandomColor();
  }

  public abstract void handleHit(Level level);

  public abstract boolean isBlockBroken();

  protected void setRandomColor() {
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
  }
  //bomb brick gets its neighbors when created
}

