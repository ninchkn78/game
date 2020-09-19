package breakout;

import java.util.Iterator;
import java.util.Random;
import javafx.scene.input.KeyCode;

public class GameLogic {

  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  private Level level;

  //take in a level
  public GameLogic(Level level) {

    //maybe could add a class to package these guys together
    setUpLevel(level);
  }

  public void setUpLevel(Level newLevel){
    this.level = newLevel;
  }

  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

 //stays in game logic
  public void handleKeyInput(KeyCode code) {
    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
      if (!gamePaused) {
        level.getPaddle().movePaddle(code);
        if (!ballLaunched) {
          for (Ball ball : level.getBalls()) {
            ball.moveBallWithPaddle(code);
          }
        }
      }
    }
    if (code.equals(KeyCode.SHIFT)) {
      setBallLaunched(true);
    }
    if (code.equals(KeyCode.SPACE)) {
      setGamePaused();
    }
    if (code.equals(KeyCode.R)) {
      resetGame();
    }
    if (code.equals(KeyCode.P)) {
      level.addPowerup();
    }
    if (code.equals(KeyCode.S)){
      resetGame();
      setUpLevel(LevelConfig.setUpLevel(1, level.getRoot()));
    }
  }



  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched(boolean status) {
    if (!ballLaunched) {
      for (Ball ball : level.getBalls()) {
        ball.setLaunch();
      }
    }
    ballLaunched = status;
  }

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      for (Ball ball : level.getBalls()) {
        ball.moveBall(elapsedTime);
      }
    }
  }

  public void resetGame() {
    setBallLaunched(false);
    for (Ball ball : level.getBalls()) {
      ball.reset();
    }
    level.getPaddle().reset();
  }

  public void checkBallBlockCollision(Ball ball) {
      Iterator<Block> itr = level.getBlockList().iterator();
      while (itr.hasNext()) {
        Block block = itr.next();
        if (ball.checkBallObjectCollision(block)) {
          block.handleHit();
          if (block.isBlockBroken()) {
            //TODO score changes here
            level.getRoot().getChildren().remove(block);
            itr.remove();
          }
        }
      }
  }

  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      for (Powerup powerup : level.getPowerups()) {
        powerup.drop(elapsedTime);
      }
    }
  }

  public void checkCollision() {
    for (Ball ball : level.getBalls()) {
      checkBallBlockCollision(ball);
      ball.checkBallObjectCollision(level.getPaddle());
    }
  }

  public void checkBallDroppedThroughBottom() {
    int numOfBalls = level.getBalls().size();
    for (Ball ball : level.getBalls()) {
      if (ball.checkBallDroppedThroughBottom()) {
        numOfBalls -= 1;
        //add something to account for if we want loss to only be on one ball ?
      }
    }
    if (numOfBalls == 0) {
      System.out.println("yup");
      resetGame();
    }
  }
}

