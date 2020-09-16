package breakout;

import javafx.scene.paint.Color;

public class BasicBlock extends Block {

  public static int X_SCALE = 60;
  public static int Y_SCALE = 15;

  private boolean blockHit = false;

  public BasicBlock(int row, int col, int width, int height) {
    super(row * X_SCALE,
            col * Y_SCALE,
            width,
            height);
    this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
  }
  public boolean wasHit(){
    return blockHit;
  }
  @Override
  public void handleHit() {
    blockHit = true;
  }



  }

