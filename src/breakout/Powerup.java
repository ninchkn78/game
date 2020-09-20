package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Powerup extends Circle {
  public static final Paint POWER_UP_COLOR = Color.HOTPINK;
  private int dropSpeed = 200;

  public Powerup(double centerX, double centerY, int size){
    super(centerX,centerY,size);
    this.setFill(POWER_UP_COLOR);
  }

  private boolean hitPaddle(Paddle paddle){
    return this.getBoundsInParent().intersects(paddle.getBoundsInParent());
  }
  public boolean powerUpPaddle(Paddle paddle){
    if(hitPaddle(paddle)){
      paddle.changePaddleWidth(1.25);
      return true;
    }
    return false;
  }

  public void drop(double elapsedTime){
    this.setCenterY(this.getCenterY() + dropSpeed * elapsedTime);
  }
}
