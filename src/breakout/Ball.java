package breakout;

import java.util.Random;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball extends Circle {

  public static final Paint BALL_COLOR = Color.PLUM;
  public static final double X_SPEED_RANGE_START = .55;
  public static final double X_SPEED_RANGE_END = .90;
  private final Random random = new Random();
  private final int initialX;
  private final int initialY;
  private int xSpeed;
  private int ySpeed;
  private int xDirection;
  private int yDirection;
  private boolean ballLaunched = false;

  public Ball(int centerX, int centerY, int size) {
    super(centerX, centerY, size);
    initialX = centerX;
    initialY = centerY;
    this.setFill(BALL_COLOR);
  }

  public boolean isBallLaunched() {
    return this.ballLaunched;
  }

  public void moveBallWithPaddle(KeyCode code) {
    if (code.equals(KeyCode.LEFT)) {
      this.setCenterX(this.getCenterX() - Paddle.PADDLE_SPEED);
    } else if (code.equals(KeyCode.RIGHT)) {
      this.setCenterX(this.getCenterX() + Paddle.PADDLE_SPEED);
    }
  }

  public void moveBall(double elapsedTime) {
    checkWallCollision();
    this.setCenterY(this.getCenterY() + this.ySpeed * this.yDirection * elapsedTime);
    this.setCenterX(this.getCenterX() - this.xSpeed * this.xDirection * elapsedTime);
  }

  private void checkWallCollision() {
    if (this.getCenterY() <= Game.TOP_OF_STAGE) {
      yDirection *= -1;
    }
    if (this.getCenterX() <= Game.TOP_OF_STAGE || this.getCenterX() >= Game.SIZE) {
      xDirection *= -1;
    }
  }

  public void setLaunch() {
    ballLaunched = true;
    this.setDirection(random.nextBoolean() ? -1 : 1,
        -1);
    int ballSpeed = 200;
    this.xSpeed = GameLogic.getRandomNumber((int) (ballSpeed * X_SPEED_RANGE_START),
        (int) (ballSpeed * X_SPEED_RANGE_END));
    this.ySpeed = (int) Math.sqrt((ballSpeed * ballSpeed) - (this.xSpeed * this.xSpeed));
  }

  // TODO: 2020-09-26 added in gamelogic too, but generalize for dropping through top
  public boolean checkBallDroppedThroughBottom(int levelNum) {
    if (levelNum == 2) {
      return this.getCenterY() < 0;
    }

    return this.getCenterY() > Game.SIZE;
  }

  public boolean checkBallObjectCollision(Shape object) {
    if (this.getBoundsInParent().intersects(object.getBoundsInParent())) {
      this.yDirection *= -1;
      return true;
    }
    return false;
  }

  public void setDirection(int xDirection, int yDirection) {
    this.xDirection = xDirection;
    this.yDirection = yDirection;
  }


  public void reset() {
    this.setCenterX(initialX);
    this.setCenterY(initialY);
    this.setDirection(1, 1);
  }

  public int getDirectionX() {
    return this.xDirection;
  }

  public int getDirectionY() {
    return this.yDirection;
  }


  public void ignoreBottom() { // ball does not fall through bottom and instead bounces back up
    if (this.getCenterY() > Game.SIZE) {
      this.yDirection *= -1;
    }
  }

  public void changeBallSpeed(double modifier) {
    this.ySpeed *= modifier;
    this.xSpeed *= modifier;
  }
}
