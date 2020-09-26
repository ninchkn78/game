package breakout;

import javafx.scene.paint.Color;

public class BasicBlock extends Block {

  public final static int Y_DISPLACEMENT = 5;
  public final static int X_DISPLACEMENT = 10;
  public final static int SPACE_FROM_TOP = 50;

  private boolean blockBroken = false;

  public BasicBlock(int row, int col, int width, int height) {
    super(row * (width + X_DISPLACEMENT),
            col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP,
            width,
            height);
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

