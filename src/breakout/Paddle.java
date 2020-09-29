package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

  public static final double PADDLE_SPEED = 5;

  public static final Paint PADDLE_COLOR = Color.LIGHTCORAL;

  private static double initialX;
  private static double initialY;
  private final double paddleWidth;
  private double paddleSpeedModifier = 1;

  public Paddle(double xPos, double yPos, double width, double height) {
    super(xPos, yPos, width, height);
    initialX = xPos;
    initialY = yPos;
    paddleWidth = width;
    this.setFill(PADDLE_COLOR);
  }

  public void reset() {
    this.setX(initialX);
    this.setY(initialY);
    this.setWidth(paddleWidth);
    this.changePaddleSpeed(1);
  }

  public void movePaddle(KeyCode code) {
    if (code.equals(KeyCode.LEFT)) {
      this.setX(this.getX() - PADDLE_SPEED * paddleSpeedModifier);
    } else if (code.equals(KeyCode.RIGHT)) {
      this.setX(this.getX() + PADDLE_SPEED * paddleSpeedModifier);
    }
  }
  public void changePaddleSpeed(double modifier){
    paddleSpeedModifier *= modifier;
  }


  public void changePaddleWidth(double modifier) {
    this.setWidth(modifier * paddleWidth);
  }
}

