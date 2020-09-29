package breakout;

/**
 * @author Alex Chao
 */

import static org.junit.jupiter.api.Assertions.assertThrows;

import javafx.scene.Group;
import org.junit.jupiter.api.Test;

/**
 * Runs tests
 */

class LevelConfigErrorTest {
  @Test
  void levelDoesNotExist() {
    assertThrows(NullPointerException.class, () ->
        LevelConfig.setUpLevel(4, new Group()));
  }
  @Test
  void levelFileBadFormat() {
   LevelConfig.setUpLevel(99, new Group());
  }
}