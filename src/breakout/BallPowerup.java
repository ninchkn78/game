package breakout;

public class BallPowerup extends Powerup {

  public static final double BALL_SPEED_SLOWDOWN = .95;

  public BallPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(BALL_POWER_UP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changeBallSpeed(BALL_SPEED_SLOWDOWN);
  }

}
