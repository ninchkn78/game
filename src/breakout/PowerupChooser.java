package breakout;


/**
 * Subclass of TypeChooser
 *
 * Makes powerups at the given x and y position and gives the powerup of the type passed into the parent method
 * getType
 *
 * Example: PowerupChooser.getType(0) would return a BallPowerup
 *
 * @author Alex Chao
 */
public class PowerupChooser extends TypeChooser {

  //only have to change powerup chooser when adding a new random powerup

  public static final int BALL_POWERUP = 0;
  public static final int PADDLE_WIDTH_POWERUP = 1;
  public static final int EXTRA_LIFE_POWERUP = 2;
  public static final int PADDLE_SPEED_POWERUP = 3;
  private final double xPos;
  private final double yPos;

  public PowerupChooser(double x, double y) {
    xPos = x;
    yPos = y;
    addAllTypes();
  }

  @Override
  protected int getTypeIndex(String type) {
    return Integer.parseInt(type);
  }

  @Override
  protected void addAllTypes() {
    addTypeToMap(new BallPowerup(xPos, yPos));
    addTypeToMap(new PaddleWidthPowerup(xPos, yPos));
    addTypeToMap(new ExtraLifePowerup(xPos, yPos));
    addTypeToMap(new PaddleSpeedPowerup(xPos,yPos));
  }
}
