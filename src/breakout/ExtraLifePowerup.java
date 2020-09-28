package breakout;

public class ExtraLifePowerup extends Powerup {

  public ExtraLifePowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(EXTRA_LIFE_POWER_UP_COLOR);
  }

  @Override
  public void doPowerup(Level level) {
    level.changeLives(-1);
  }

}