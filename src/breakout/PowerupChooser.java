package breakout;

import javafx.scene.shape.Shape;

public class PowerupChooser extends TypeChooser {

  //only have to change powerup chooser when adding a new random powerup

  public static final int BALL_POWERUP = 0;
  public static final int PADDLE_POWERUP = 1;
  public static final int EXTRA_LIFE_POWERUP = 2;
  private double xPos;
  private double yPos;;

  public PowerupChooser(double x, double y) {
    xPos = x;
    yPos = y;
    addAllTypes();
  }


  @Override
  public int getTypeIndex(String type) {
    return Integer.parseInt(type);
  }

  @Override
  public void addAllTypes() {
    addTypeToMap(new BallPowerup(xPos, yPos));
    addTypeToMap(new PaddlePowerup(xPos, yPos));
    addTypeToMap(new ExtraLifePowerup(xPos, yPos));
  }
}
