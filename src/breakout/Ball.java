package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
  public Ball(int centerX, int centerY, int size){
    super(centerX,centerY,size);
  }
  public void moveBallWithPaddle (KeyCode code) {
    switch (code) {
      case LEFT -> this.setCenterX(this.getCenterX() - Paddle.MOVER_SPEED);
      case RIGHT -> this.setCenterX(this.getCenterX() + Paddle.MOVER_SPEED);
    }}


}
