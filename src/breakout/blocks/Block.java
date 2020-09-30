package breakout.blocks;

import breakout.Level;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * An abstract class that provides a template for creating Blocks, which can handle being hit
 * by another Object
 *
 * Color is default to a random color
 *
 * @author Alex Chao
 */

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

  /**
   * Enact different effects depending on the type of block when it is hit.
   * @param level
   */
  public abstract void handleHit(Level level);

  /**
   * Returns true if the block has been broken and false otherwise
   *
   * Note: on reflection, this method actually does not need to be abstract, since the logic for
   * checking that is in handleHit
   *
   * @return true if block broken, false otherwise
   */
  public abstract boolean isBlockBroken();

  protected void setRandomColor() {
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
  }
}

