package breakout;

public class PaddlePowerup extends Powerup {

  public PaddlePowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_POWER_UP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changePaddleWidth(1.25);
  }
}

