package breakout;

public class ExampleBlock extends Block{
  public ExampleBlock(int row, int col, int width, int height){
    super(row * 60, col * 15, width, height);
  }

  @Override
  public void handleHit() {

  }
}
