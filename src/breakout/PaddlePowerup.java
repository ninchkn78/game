package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PaddlePowerup extends Powerup {


  public PaddlePowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(PADDLE_POWER_UP_COLOR);
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
