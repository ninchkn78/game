package breakout;

/**
 * Subclass of Powerup
 *
 * A powerup that increases the width of the paddle in a Level object
 *
 * @author Alex Chao
 */

public class PaddleWidthPowerup extends Powerup {

  public static final double PADDLE_WIDTH_MODIFIER = 1.25;

  public PaddleWidthPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_WIDTH_POWERUP_COLOR);
  }
  /**
   * Tells the Level passed in to increase the paddle width by a factor of the paddle width modifier constant
   * @param level
   */
  @Override
  protected void doPowerup(Level level) {
    level.changePaddleWidth(PADDLE_WIDTH_MODIFIER);
  }
}

