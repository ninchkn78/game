package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
  private int BALL_SPEED = 100;
  public Ball(int centerX, int centerY, int size){
    super(centerX,centerY,size);
  }
  public void moveBallWithPaddle (KeyCode code) {
    switch (code) {
      case LEFT -> this.setCenterX(this.getCenterX() - Paddle.MOVER_SPEED);
      case RIGHT -> this.setCenterX(this.getCenterX() + Paddle.MOVER_SPEED);
    }}

  public void moveBall (double elapsedTime) {
    // there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    this.setCenterY(this.getCenterY() - BALL_SPEED * elapsedTime);

  }

}
