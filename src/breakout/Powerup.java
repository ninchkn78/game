package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class Powerup extends Circle {
  public static final Paint POWER_UP_COLOR = Color.HOTPINK;
  private int dropSpeed = 200;

  public Powerup(double centerX, double centerY, int size){
    super(centerX,centerY,size);
    this.setFill(POWER_UP_COLOR);
  }

  protected boolean hitPaddle(Paddle paddle){
    return this.getBoundsInParent().intersects(paddle.getBoundsInParent());
  }
  public abstract boolean poweredUp(Level level, Paddle paddle);

  public void drop(double elapsedTime){
    this.setCenterY(this.getCenterY() + dropSpeed * elapsedTime);
  }
}
