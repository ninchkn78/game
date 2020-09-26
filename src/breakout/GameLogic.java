package breakout;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameLogic {

  private final Group myRoot;
  private boolean ballLaunched = false;
  private boolean gamePaused = false;
  private boolean gameWon = false;
  private Level level;
  private int levelNum;
  private final Display myDisplay = new Display();

  private final Map<KeyCode, Runnable> myKeyActions = new HashMap<>();

  //take in a level
  public GameLogic(int level, Group root) {
    levelNum = level;
    myRoot = root;
    //maybe could add a class to package these guys together
    setUpLevel(levelNum, root);
    makeKeyActionsMap();
  }

  private void makeKeyActionsMap() {
    addKeyInput(KeyCode.SHIFT, this::setBallLaunched);
    addKeyInput(KeyCode.SPACE, this::setGamePaused);
    addKeyInput(KeyCode.RIGHT,() -> movePaddle(KeyCode.RIGHT));
    addKeyInput(KeyCode.LEFT, () -> movePaddle(KeyCode.LEFT));
    addKeyInput(KeyCode.R, this::resetGame);
    addKeyInput(KeyCode.P, this::addPowerup);
    addKeyInput(KeyCode.S, this::advanceLevel);
    addKeyInput(KeyCode.B, this::addBall);
    addKeyInput(KeyCode.L, this::addLife);
    addKeyInput(KeyCode.D, this::destroyFirstBlock);
    addKeyInput(KeyCode.I, () -> level.alternateImmunity());
    addKeyInput(KeyCode.UP,this::increaseBallSpeed);
    addKeyInput(KeyCode.DOWN,this::decreaseBallSpeed);
    addKeyInput(KeyCode.DIGIT1,() -> changeLevel(1));
    addKeyInput(KeyCode.DIGIT2,() -> changeLevel(2));
    addKeyInput(KeyCode.DIGIT3,() -> changeLevel(3));
  }

  private void addKeyInput(KeyCode code, Runnable executable){
    myKeyActions.put(code, executable);
  }

  private void increaseBallSpeed(){
    level.changeBallSpeed(1.05);
  }
  private void decreaseBallSpeed(){
    level.changeBallSpeed(.95);
  }



  private void destroyFirstBlock() {
    if(!level.noBlocks()) {//destroy first block
      level.removeBlock(0);
    }
  }

  private void addLife() {
    level.changeLives(-1);
  }

  private void addBall() {
    if(!gamePaused) {
      level.addBall();
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

  private void changeLevel(int level){
    resetGame();
    setUpLevel(level,myRoot);
    myDisplay.changeLevel(level, myRoot);
  }

  //found this on stack overflow
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
    myKeyActions.getOrDefault(code, () -> {} ).run();
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

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      level.moveBall(elapsedTime);
    }
    if(level.checkBallDroppedThroughBottom()) {
      ballLaunched = false;
      checkGameLost();
    }
  }

  private void resetGame() {
    ballLaunched = false;
    level.reset();
    setUpLevel(levelNum, myRoot);
  }

  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      level.dropPowerups(elapsedTime);
    }
  }

  private void checkGameWon() {
    if (level.noBlocks() && !gameWon) {
      System.out.println("Game won");
      Text won = new Text(Game.SIZE / 2 - 50, Game.SIZE / 2,
          "You won this level!\nPress S to continue");
      won.setId("WonText");
      level.add(won);
      gameWon = true;
    }
  }

  private void checkGameLost() {
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

