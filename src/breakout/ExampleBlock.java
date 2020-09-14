package breakout;

public class ExampleBlock extends Block{
  public static int X_SCALE = 60;
  public static int Y_SCALE = 15;

  public ExampleBlock(int row, int col, int width, int height){
    super(row * X_SCALE,
        col * Y_SCALE,
        width,
        height);
  }
  @Override
  public void handleHit() {
  }
}
