package breakout;
/**
 * Subclass of Powerup
 *
 * A powerup that speeds upu the paddle in a Level object
 *
 * @author Alex Chao
 */
public class PaddleSpeedPowerup extends Powerup {

  public static final double PADDLE_SPEED_MODIFIER = 1.25;

  public PaddleSpeedPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_SPEED_POWERUP_COLOR);
  }

  /**
   * Tells the Level passed in to speed up the paddle by a factor of the paddle speed modifier constant
   * @param level
   */
  @Override
  protected void doPowerup(Level level) {
    level.changePaddleSpeed(PADDLE_SPEED_MODIFIER);
  }
}