package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * An abstract class that allows powerups to be in the game
 *
 * Powerups are circles and only move along the Y direction
 *
 * @author Alex Chao
 */
public abstract class Powerup extends Circle {

  public static final Paint BALL_POWER_UP_COLOR = Color.BLUEVIOLET;
  public static final Paint PADDLE_WIDTH_POWERUP_COLOR = Color.HOTPINK;
  public static final Paint EXTRA_LIFE_POWER_UP_COLOR = Color.GREENYELLOW;
  public static final Paint PADDLE_SPEED_POWERUP_COLOR = Color.INDIANRED;
  public static final int POWERUP_SIZE = 10;
  private boolean powerUpHappened = false;
  private int dropSpeed = 200;

  public Powerup(double centerX, double centerY) {
    super(centerX, centerY, POWERUP_SIZE);
    setDropDirection();
  }

  protected boolean hitPaddle(Paddle paddle) {
    return this.getBoundsInParent().intersects(paddle.getBoundsInParent());
  }

  /**
   * If the powerup has not done its effect, do this effect if it hits the paddle then return true
   * otherwise return false
   * @param level a Level Object
   * @param paddle a Paddle Object
   * @return true if powerup happens, false otherwise
   */
  public boolean poweredUp(Level level, Paddle paddle) {
    if (hitPaddle(paddle) && !powerUpHappened) {
      doPowerup(level);
      powerUpHappened = true;
      return true;
    }
    return false;
  }

  protected abstract void doPowerup(Level level);

  private void setDropDirection() {
    if (this.getCenterY() > Game.SIZE / 2) {
      dropSpeed *= -1;
    }
  }

  /**
   * Moves the powerup
   * @param elapsedTime
   */
  public void drop(double elapsedTime) {
    this.setCenterY(this.getCenterY() + dropSpeed * elapsedTime);
  }
}
