package breakout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.shape.Shape;

/**
 * An abstract class that provides a template for functionality that allows the user make a specific
 * type of object
 *
 * Examples include the Powerup and Block choosers, which allow for easy access of a specific kind
 * of each of these
 *
 * @author Alex Chao
 */

public abstract class TypeChooser {

  public static final int RANDOM_TYPE = -1;

  private final Map<Integer, Shape> types = new HashMap<>();
  private int numOfTypes = 0;

  public TypeChooser() {
  }

  /**
   * Given a string that identifies a type of shape, returns a new Shape
   * @param type String that represents the type of the object wanted
   * @return the Shape associated with that type
   */
  public Shape getType(String type) {
    int typeIndex = getTypeIndex(type);
    if (typeIndex == RANDOM_TYPE) {
      Random rand = new Random();
      typeIndex = rand.nextInt(types.size());
    }
    return types.get(typeIndex);
  }

  protected void addTypeToMap(Shape object) {
    types.put(numOfTypes, object);
    numOfTypes++;
  }

  protected abstract int getTypeIndex(String type);

  protected abstract void addAllTypes();
}
