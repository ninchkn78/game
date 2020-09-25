package breakout;

import static javafx.scene.input.KeyCode.RIGHT;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameLogic {

  private final Group myRoot;
  private boolean ballLaunched = false;
  private boolean gamePaused = false;
  private Level level;
  private int levelNum;
  private Display myDisplay = new Display();

  private Map<KeyCode, Consumer> myKeyActions = new HashMap<>();

  //take in a level
  public GameLogic(int level, Group root) {
    levelNum = level;
    myRoot = root;
    //maybe could add a class to package these guys together
    setUpLevel(levelNum, root);
  }

  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }
//  public void setUpLevel1(Group root){
//    level = LevelConfig1.setUpLevel(root);
//  }
//  public void setUpLevel2(Group root){
//    level = LevelConfig1.setUpLevel(root);
//  }

  public void setUpLevel(int levelNum, Group root) {
    root.getChildren().clear();
    //List<Block> blocks = LevelConfig.getBlockList(levelNum);
    level = LevelConfig.setUpLevel(levelNum, root);
  }

  //stays in game logic
  public void handleKeyInput(KeyCode code) {
    myKeyActions.put(RIGHT, (KeyCode) -> level.movePaddle(RIGHT));

    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(RIGHT)) {
      if (!gamePaused) {
        level.movePaddle(code);
        if (!ballLaunched) {
          level.moveBallsWithPaddle(code);
        }
      }
    }
    if (code.equals(KeyCode.SHIFT)) {
      setBallLaunched();
    }
    if (code.equals(KeyCode.SPACE)) {
      setGamePaused();
    }
    if (code.equals(KeyCode.R)) {
      resetGame();
    }
    if (code.equals(KeyCode.P)) {
      if (!gamePaused && ballLaunched) {
        level.addPowerup();
      }
    }
    if (code.equals(KeyCode.S)) {
      resetGame();
      levelNum++;
      myDisplay.changeLevel(levelNum, myRoot);
      //System.out.println(level.getRoot().getChildren());
    }
    if (code.equals(KeyCode.B)) {
      level.addBall();
      setBallLaunched();
    }
    if (code.equals(KeyCode.L)) {
      level.changeLives(-1);
    }
    if (code.equals(KeyCode.D)) { //destroy first block
      level.removeBlock(0);
    }
    if (code.equals(KeyCode.I)) { // toggle immunity
      level.alternateImmunity();
    }
  }

  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched() {
    ballLaunched = true;
    level.setBallLaunched();
  }

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      level.moveBall(elapsedTime);
    }
    checkGameLost();
  }

  public void resetGame() {
    ballLaunched = false;
    level.reset();
    setUpLevel(levelNum, myRoot);
  }

  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      level.dropPowerups(elapsedTime);
    }
  }

  public void checkGameWon() {
    if (level.noBlocks()) {
      Text won = new Text(Game.SIZE / 2 - 50, Game.SIZE / 2,
          "You won this level!\nPress S to continue");
      won.setId("WonText");
      level.add(won);
    }
  }

  public void checkGameLost() {
    if (level.noLives()) {
      Text lost = new Text(Game.SIZE / 2 - 50, Game.SIZE / 2,
          "You lost this level! :( \nPress R to restart");
      lost.setId("lostText");
      level.add(lost);
    }
  }

  public void checkCollision() {
    level.checkCollisions();
    checkGameWon();
  }

}

