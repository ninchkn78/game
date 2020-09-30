package breakout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

/**
 * Given a level and a root, makes a corresponding Level Object and
 * manages what happens in the Level based on conditions and inputs
 *
 * @authors Alex Chao and Christian Welch
 */
public class GameLogic {

  public static final double REDUCE_BALL_SPEED_RATIO = .95;
  private final Group myRoot;
  private Display myDisplay = new Display();
  private final Map<KeyCode, Runnable> myKeyActions = new HashMap<>();
  private boolean ballLaunched = false;
  private boolean gamePaused = false;
  private boolean gameWon = false;
  private Level level;
  private int levelNum;

  public GameLogic(int level, Group root) {
    levelNum = level;
    myRoot = root;
    setUpLevel(levelNum, root);
    makeKeyActionsMap();
  }

  /**
   * Gets a random number b/w the min and the max, assumes min is less than max and works for negative values
   * @param min
   * @param max
   * @return a random int
   */
  //found this on stack overflow
  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  private void makeKeyActionsMap() {
    addKeyInput(KeyCode.SHIFT, this::setBallLaunched);
    addKeyInput(KeyCode.SPACE, this::setGamePaused);
    addKeyInput(KeyCode.RIGHT, () -> movePaddle(KeyCode.RIGHT));
    addKeyInput(KeyCode.LEFT, () -> movePaddle(KeyCode.LEFT));
    addKeyInput(KeyCode.R, this::resetGame);
    addKeyInput(KeyCode.P, this::addPowerup);
    addKeyInput(KeyCode.TAB, this::advanceLevel);
    addKeyInput(KeyCode.B, this::addBall);
    addKeyInput(KeyCode.L, this::addLife);
    addKeyInput(KeyCode.D, this::destroyFirstBlock);
    addKeyInput(KeyCode.I, () -> level.alternateImmunity());
    addKeyInput(KeyCode.UP, this::increaseBallSpeed);
    addKeyInput(KeyCode.DOWN, this::decreaseBallSpeed);
    addKeyInput(KeyCode.DIGIT1, () -> changeLevel(1));
    addKeyInput(KeyCode.DIGIT2, () -> changeLevel(2));
    addKeyInput(KeyCode.DIGIT3, () -> changeLevel(3));
  }

  private void addKeyInput(KeyCode code, Runnable executable) {
    myKeyActions.put(code, executable);
  }

  private void increaseBallSpeed() {
    level.changeBallSpeed(Ball.INCREASE_BALL_SPEED_RATIO);
  }

  private void decreaseBallSpeed() {
    level.changeBallSpeed(REDUCE_BALL_SPEED_RATIO);
  }

  private void destroyFirstBlock() {
    if (!level.noBlocks()) {//destroy first block
      level.removeBlock(0);
    }
  }

  private void addLife() {
    level.changeLives(-1);
  }

  private void addBall() {
    if (!gamePaused) {
      level.addNewBall();
      setBallLaunched();
    }
  }

  private void advanceLevel() {
    levelNum++;
    changeLevel(levelNum);

  }

  private void addPowerup() {
    if (!gamePaused && ballLaunched) {
      level.addRandomPowerup();
    }
  }


  private void changeLevel(int level) {
    resetGame();
    levelNum = level;
    setUpLevel(level, myRoot);
    levelNum = level;
    myDisplay.changeLevel(levelNum, myRoot);
  }

  public void setUpLevel(int levelNum, Group root) {
    root.getChildren().clear();
    //List<Block> blocks = LevelConfig.getBlockList(levelNum);
    level = LevelConfig.setUpLevel(levelNum, root);

  }
  private void setUpDisplay(int levelNum) {
    myDisplay = new Display();
    Text myStats = myDisplay.createDisplay(levelNum);
    myRoot.getChildren().add(myStats);
  }

  /**
   * Performs the action associated with the input passed in
   * @param code a keyboard input
   */
  public void handleKeyInput(KeyCode code) {
    myKeyActions.getOrDefault(code, () -> {
    }).run();
  }

  private void movePaddle(KeyCode code) {
    if (!gamePaused) {
      level.movePaddle(code);
      if (!ballLaunched) {
        level.moveBallsWithPaddle(code);
      }
    }
  }

  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched() {
    ballLaunched = true;
    level.setBallLaunched();
  }

  /**
   * Tells the Level to move its balls
   * If all balls pass out of stage, checks to see if the game was lost
   * @param elapsedTime
   */
  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      level.moveBall(elapsedTime);
    }
    if (level.checkBallDroppedThroughBottom(levelNum)) {
      ballLaunched = false;
      checkGameLost();
    }
  }


  private void resetGame() {
    ballLaunched = false;
    level.reset();
    setUpLevel(levelNum, myRoot);

  }

  /**
   * Tells the level to move its powerups
   * @param elapsedTime
   */
  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      level.dropPowerups(elapsedTime);
    }
  }

  private void checkGameWon() {
    if (level.noBlocks() && !gameWon) {
      Text won = new Text(Game.SIZE / 2 - LevelConfig.TEXT_DISPLACEMENT, Game.SIZE / 2,
          "You won this level!\nPress Tab to continue");
      won.setId("WonText");
      level.add(won);
      gameWon = true;
      setGamePaused();
    }
  }


  private void checkGameLost() {
    if (level.noLives()) {
      Text lost = new Text(Game.SIZE / 2 - LevelConfig.TEXT_DISPLACEMENT, Game.SIZE / 2,
          "You lost this level! :( \nPress R to restart");
      lost.setId("lostText");
      level.add(lost);
      setGamePaused();
    }
  }

  public void checkCollision() {
    level.checkCollisions();
    checkGameWon();
  }

  // high score implementation does not work, but a skeleton is here.
  public void addHighScoreToFile(int HighScore){
    try {
      FileWriter myWriter = new FileWriter("./doc/HighScore.txt");
      myWriter.write(String.valueOf(HighScore));
      myWriter.close();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public int readHighScore() throws FileNotFoundException {
    int CurrentHighScore = 0;
    String file = "./doc/HighScore.txt";
    Scanner s = new Scanner(new File(file));
    while (s.hasNext()){
      CurrentHighScore = Integer.parseInt(s.next());
    }
    return CurrentHighScore;
  }
  public void updateHighScore() throws FileNotFoundException {
    int highScore = readHighScore();

    if (myDisplay.getScore() > highScore){
      addHighScoreToFile(myDisplay.getScore());

    }
  }


}

