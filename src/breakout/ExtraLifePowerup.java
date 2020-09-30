package breakout;

public class ExtraLifePowerup extends Powerup {

  /**
   * Subclass of Powerup
   *
   * A powerup that grants an extra life in a Level object
   *
   * @author Alex Chao
   */

  public ExtraLifePowerup(double centerX, double centerY) {
    super(centerX, centerY);
    this.setFill(EXTRA_LIFE_POWER_UP_COLOR);
  }

  /**
   * Tells the Level passed in to grant an extra life
   * @param level
   */
  @Override
  public void doPowerup(Level level) {
    level.changeLives(-1);
  }

}