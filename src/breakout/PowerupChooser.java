package breakout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PowerupChooser {
  public static final int BALL_POWERUP = 0;
  public static final int PADDLE_POWERUP = 1;

  private int numOfPowerups = 0;
  private double xPos;
  private double yPos;

  public PowerupChooser(double x, double y){
    xPos = x;
    yPos = y;
    addAllPowerups();
  }

  private Map<Integer, Powerup> powerupTypes = new HashMap<>();

   public Powerup getPowerup(int powerupIndex){
     if(powerupIndex == -1) {
       Random rand = new Random();
       powerupIndex = rand.nextInt(powerupTypes.size());
     }
     return powerupTypes.get(powerupIndex);
  }
  private void addPowerupToMap(Powerup powerup){
    powerupTypes.put(numOfPowerups, powerup);
    numOfPowerups++;
  }

  private void addAllPowerups(){
    addPowerupToMap(new BallPowerup(xPos,yPos));
    addPowerupToMap(new PaddlePowerup(xPos,yPos));
  }

}
