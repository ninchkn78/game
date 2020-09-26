package breakout;

public class BallPowerup extends Powerup {


  public BallPowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(BALL_POWER_UP_COLOR);
  }

  @Override
  public boolean poweredUp(Level level, Paddle paddle) {
    if(hitPaddle(paddle)){
      level.changeBallSpeed(.95);
      return true;
    }
    return false;
  }
}
