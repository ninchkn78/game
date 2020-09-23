package breakout;

import java.util.List;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class GameLogic {

  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  private Level level;
  private int levelNum;
  private final Group myRoot;


    //take in a level
  public GameLogic(int level, Group root) {
    levelNum = level;
    myRoot = root;
    //maybe could add a class to package these guys together
    setUpLevel(levelNum, root);
  }

  public void setUpLevel(int levelNum, Group root){
    root.getChildren().clear();
    List<Block> blocks = LevelConfig.getBlockList(levelNum);
    level = LevelConfig.setUpLevel(levelNum, root);
  }
//  public void setUpLevel1(Group root){
//    level = LevelConfig1.setUpLevel(root);
//  }
//  public void setUpLevel2(Group root){
//    level = LevelConfig1.setUpLevel(root);
//  }



  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

 //stays in game logic
  public void handleKeyInput(KeyCode code) {
    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
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
      if(!gamePaused && ballLaunched){
      level.addPowerup();
    }}
    if (code.equals(KeyCode.S)){
      resetGame();
      levelNum++;
      setUpLevel(levelNum, level.getRoot());
    }
    if(code.equals(KeyCode.B)){
      level.addBall();
      setBallLaunched();
    }
    if(code.equals(KeyCode.L)){
      level.changeLives(-1);
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
  }

  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      level.dropPowerups(elapsedTime);
    }
  }
  public void checkGameWon(){
    if(level.noBlocks()){
      Text won = new Text(Game.SIZE/2 - 50, Game.SIZE/2, "You won this level!\nPress S to continue");
      won.setId("WonText");
      level.add(won);
    }
  }
  //TODO have some way to restart level
  public void checkGameLost(){
    if(level.noLives()){
      Text lost = new Text(Game.SIZE/2 - 50, Game.SIZE/2, "You lost this level! :( \nPress S to continue");
      lost.setId("lostText");
      level.add(lost);
    }
  }

  public void checkCollision() {
    level.checkCollisions();
    checkGameWon();
  }

}

