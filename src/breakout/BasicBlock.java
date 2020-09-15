package breakout;

public class BasicBlock extends Block {

  public static int X_SCALE = 60;
  public static int Y_SCALE = 15;


  public BasicBlock(int row, int col, int width, int height) {
    super(row * X_SCALE,
            col * Y_SCALE,
            width,
            height);
  }

  @Override
  public void handleHit(Ball ball) {

  }



  }

