package breakout;


/**
 * Subclass of Powerup
 *
 * A powerup that slows down the ball in a Level object
 *
 * @author Alex Chao
 */
public class BallPowerup extends Powerup {

  public static final double BALL_SPEED_SLOWDOWN = .95;

  public BallPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(BALL_POWER_UP_COLOR);
  }

  /**
   * Tells the Level passed in to slow down its balls
   * @param level
   */
  @Override
  public void doPowerup(Level level) {
    level.changeBallSpeed(BALL_SPEED_SLOWDOWN);
  }

}
