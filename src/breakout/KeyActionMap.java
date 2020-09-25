package breakout;

import static javafx.scene.input.KeyCode.RIGHT;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javafx.scene.input.KeyCode;

public class KeyActionMap {

  private GameLogic myGameLogic;

  public KeyActionMap(GameLogic gameLogic){
    myGameLogic = gameLogic;
    shiftKey();
  }
  private final Map<KeyCode, Runnable> myKeyActions = new HashMap<>();

  private void shiftKey(){
    myKeyActions.put(KeyCode.SHIFT, () -> myGameLogic.setBallLaunched());
  }

  public Map<KeyCode, Runnable> getMyKeyActions(){
    return myKeyActions;
  }
}
