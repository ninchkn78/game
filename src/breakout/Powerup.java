package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Powerup extends Circle {
  public static final Paint POWER_UP_COLOR = Color.HOTPINK;
  private int dropSpeed = 200;

  public Powerup(int centerX, int centerY, int size){
    super(centerX,centerY,size);
    this.setFill(POWER_UP_COLOR);
  }


  public void drop(double elapsedTime){
    this.setCenterY(this.getCenterY() + dropSpeed * elapsedTime);
  }
}
