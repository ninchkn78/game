package breakout;

public class PaddlePowerup extends Powerup {

  public static final double PADDLE_WIDTH_MODIFIER = 1.25;

  public PaddlePowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_POWER_UP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changePaddleWidth(PADDLE_WIDTH_MODIFIER);
  }
}

