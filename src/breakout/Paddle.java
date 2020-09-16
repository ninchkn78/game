package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

  public static final int PADDLE_SPEED = 5;

  public static final Paint PADDLE_COLOR = Color.LIGHTCORAL;

  private static int INITIAL_X;
  private static int INITIAL_Y;

  public Paddle(int xPos, int yPos, int width, int height) {
    super(xPos, yPos, width, height);
    INITIAL_X = xPos;
    INITIAL_Y = yPos;
    this.setFill(PADDLE_COLOR);
  }

  public void reset() {
    this.setX(INITIAL_X);
    this.setY(INITIAL_Y);
  }

  public void movePaddle(KeyCode code) {
    if(code.equals(KeyCode.LEFT)){
      this.setX(this.getX() - PADDLE_SPEED);
    }
    else if(code.equals(KeyCode.RIGHT)){
      this.setX(this.getX() + PADDLE_SPEED);
    }
  }
}

