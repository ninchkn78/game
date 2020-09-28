package breakout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.shape.Shape;

public abstract class TypeChooser {

  public static final int RANDOM_TYPE = -1;

  //only have to change powerup chooser when adding a new random powerup
  private Map<Integer, Shape> types = new HashMap<>();
  private int numOfTypes = 0;

  public TypeChooser() {
  }

  public Shape getType(String type) {
    int typeIndex = getTypeIndex(type);
    if (typeIndex == RANDOM_TYPE) {
      Random rand = new Random();
      typeIndex = rand.nextInt(types.size());
    }
    return types.get(typeIndex);
  }

  public void addTypeToMap(Shape object) {
    types.put(numOfTypes, object);
    numOfTypes++;
  }

  public abstract int getTypeIndex(String type);


  public abstract void addAllTypes();
}
