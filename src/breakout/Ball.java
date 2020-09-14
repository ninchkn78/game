package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

public class Ball extends Circle {

  private static int INITIAL_X;
  private static int INITIAL_Y;
  private int BALL_SPEED = 100;

  public Ball(int centerX, int centerY, int size) {
    super(centerX, centerY, size);
    INITIAL_X = centerX;
    INITIAL_Y = centerY;

  }

  public void moveBallWithPaddle(KeyCode code) {
    if(code.equals(KeyCode.LEFT)){
      this.setCenterX(this.getCenterX() - Paddle.MOVER_SPEED);
    }
    else if(code.equals(KeyCode.RIGHT)){
      this.setCenterX(this.getCenterX() + Paddle.MOVER_SPEED);
    }
  }

  public void moveBall(double elapsedTime) {
    // there are more sophisticated ways to animate shapes, but these simple ways work fine to start
    this.setCenterY(this.getCenterY() - BALL_SPEED * elapsedTime);

  }

  public void reset() {
    this.setCenterX(INITIAL_X);
    this.setCenterY(INITIAL_Y);
  }

}
