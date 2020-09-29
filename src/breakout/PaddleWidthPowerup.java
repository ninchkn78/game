package breakout;

public class PaddleWidthPowerup extends Powerup {

  public static final double PADDLE_WIDTH_MODIFIER = 1.25;

  public PaddleWidthPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_WIDTH_POWERUP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changePaddleWidth(PADDLE_WIDTH_MODIFIER);
  }
}

