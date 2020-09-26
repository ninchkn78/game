package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class Powerup extends Circle {
  public static final Paint PADDLE_POWER_UP_COLOR = Color.HOTPINK;
  public static final Paint BALL_POWER_UP_COLOR = Color.BLUEVIOLET;

  private int dropSpeed = 200;
  public static final int POWERUP_SIZE = 10;

  public Powerup(double centerX, double centerY){
    super(centerX,centerY,POWERUP_SIZE);
  }

  protected boolean hitPaddle(Paddle paddle){
    return this.getBoundsInParent().intersects(paddle.getBoundsInParent());
  }
  public abstract boolean poweredUp(Level level, Paddle paddle);

  public void drop(double elapsedTime){
    this.setCenterY(this.getCenterY() + dropSpeed * elapsedTime);
  }

}
