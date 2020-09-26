package breakout;

public class PaddlePowerup extends Powerup {

  public PaddlePowerup(double centerX, double centerY, int size) {
    super(centerX, centerY, size);
  }

  @Override
  public boolean poweredUp(Level level, Paddle paddle) {
      if(hitPaddle(paddle)){
        paddle.changePaddleWidth(1.25);
        return true;
      }
      return false;
  }
}
