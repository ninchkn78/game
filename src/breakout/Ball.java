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
  private static int INITIAL_X;
  private static int INITIAL_Y;
  private int BALL_SPEED = 200;
  private int X_SPEED;
  private int Y_SPEED;
  private int X_DIRECTION;
  private int Y_DIRECTION;
  private Random random = new Random();

  private boolean ballLaunched = false;
  public Ball(int centerX, int centerY, int size) {
    super(centerX, centerY, size);
    INITIAL_X = centerX;
    INITIAL_Y = centerY;
    this.setFill(BALL_COLOR);
  }
  public boolean isBallLaunched(){
    return this.ballLaunched;
  }
  public void moveBallWithPaddle(KeyCode code) {
    if(code.equals(KeyCode.LEFT)){
      this.setCenterX(this.getCenterX() - Paddle.PADDLE_SPEED);
    }
    else if(code.equals(KeyCode.RIGHT)){
      this.setCenterX(this.getCenterX() + Paddle.PADDLE_SPEED);
    }
  }

  //use triangles
  public void moveBall(double elapsedTime) {
      // there are more sophisticated ways to animate shapes, but these simple ways work fine to start
      checkWallCollision();
      this.setCenterY(this.getCenterY() + this.Y_SPEED * this.Y_DIRECTION * elapsedTime);
      this.setCenterX(this.getCenterX() - this.X_SPEED * this.X_DIRECTION * elapsedTime);

  }

  private void checkWallCollision() {
    if (this.getCenterY() <= 0) {
      Y_DIRECTION *= -1;
    }
    if (this.getCenterX() <= 0 || this.getCenterX() >= 350 ){
      X_DIRECTION *= -1;
    }
  }
  public void setLaunch(){
    ballLaunched = true;
    this.setDirection(random.nextBoolean() ? -1 : 1,
        -1);
    this.X_SPEED = GameLogic.getRandomNumber((int) (this.BALL_SPEED * X_SPEED_RANGE_START),
        (int) (this.BALL_SPEED * X_SPEED_RANGE_END));
    this.Y_SPEED = (int) Math.sqrt((this.BALL_SPEED * this.BALL_SPEED) - (this.X_SPEED * this.X_SPEED));
  }

  public boolean checkBallDroppedThroughBottom() {

      return this.getCenterY() > 355;
  }


  public boolean checkBallObjectCollision(Shape object){
    if (this.getBoundsInParent().intersects(object.getBoundsInParent())){
        this.Y_DIRECTION *= -1;
        return true;
    }
    return false;
  }

  public void setDirection(int xDirection, int yDirection){
    this.X_DIRECTION = xDirection;
    this.Y_DIRECTION = yDirection;
  }


  public void reset() {
    this.setCenterX(INITIAL_X);
    this.setCenterY(INITIAL_Y);
    this.setDirection(1,1);
  }

  public int getDirectionX() {
    return this.X_DIRECTION;
  }
  public int getDirectionY() {
    return this.Y_DIRECTION;
  }
}
