package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Paddle extends Rectangle {

  public static final int MOVER_SPEED = 5;

  private static int INITIAL_X;
  private static int INITIAL_Y;

  public Paddle(int xPos, int yPos, int width, int height) {
    super(xPos, yPos, width, height);
    INITIAL_X = xPos;
    INITIAL_Y = yPos;
  }

  public void reset() {
    this.setX(INITIAL_X);
    this.setY(INITIAL_Y);
  }

  public void movePaddle(KeyCode code) {
    switch (code) {
      case LEFT -> this.setX(this.getX() - MOVER_SPEED);
      case RIGHT -> this.setX(this.getX() + MOVER_SPEED);
    }
  }
}

