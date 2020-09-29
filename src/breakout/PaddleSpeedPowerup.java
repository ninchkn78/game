package breakout;

public class PaddleSpeedPowerup extends Powerup {

  public static final double PADDLE_SPEED_MODIFIER = 1.25;

  public PaddleSpeedPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_SPEED_POWERUP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changePaddleSpeed(PADDLE_SPEED_MODIFIER);
  }
}