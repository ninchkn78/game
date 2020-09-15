package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;

import java.util.List;

public class Ball extends Circle {

  private static int INITIAL_X;
  private static int INITIAL_Y;
  private int BALL_SPEED = 100;
  private int X_DIRECTION = 1;
  private int Y_DIRECTION = 1;
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

    if (this.getCenterY() < 0) {
      Y_DIRECTION *= -1;
    }
    if (this.getCenterX() <= 0 || this.getCenterX() >= 350 ){
      X_DIRECTION *= -1;
    }
    this.setCenterY(this.getCenterY() - BALL_SPEED * Y_DIRECTION * elapsedTime);
    this.setCenterX(this.getCenterX() - BALL_SPEED * X_DIRECTION* elapsedTime);





  }

  public boolean checkBallDropsThroughBottom() {
    if (this.getCenterY() > 350) {
      return true;
    }
    return false;
  }
  public void checkBallBlockCollision(){
    LevelConfig config = new LevelConfig();
    List<Block> blockLocations = config.getBlockLocations("level1.txt");
    for(Block block : blockLocations){
      if (this.getBoundsInParent().intersects(block.getBoundsInParent())){
        this.Y_DIRECTION *= -1;
      }
    }
  }

  public void checkBallPaddleCollision(Paddle paddle){
    if (this.getBoundsInParent().intersects(paddle.getBoundsInParent())){
        this.Y_DIRECTION *= -1;
    }
  }

  public void setDirection(int xDirection, int yDirection){
    this.X_DIRECTION = xDirection;
    this.Y_DIRECTION = yDirection;
  }

  public int getDirectionX(){
    return this.X_DIRECTION;
  }
  public int getDirectionY(){
    return this.Y_DIRECTION;
  }

  public void reset() {
    this.setCenterX(INITIAL_X);
    this.setCenterY(INITIAL_Y);
    this.setDirection(1,1);
  }

}
