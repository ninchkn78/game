package breakout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameLogic {

  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  private List<Ball> myBalls;
  private Paddle myPaddle;
  private List<Block> blockList;
  private Group root;
  private List<Powerup> powerups = new ArrayList<>();
  private int numOfBalls;


  public GameLogic(LevelConfig currentConfig) {
    myBalls = currentConfig.getMyBall();
    myPaddle = currentConfig.getMyPaddle();
    blockList = currentConfig.getBlockList();
    root = currentConfig.getRoot();
    numOfBalls = myBalls.size();
  }

  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  //ask about this method if statements
  //ask about these methods in their own class
  public void handleKeyInput(KeyCode code) {
    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
      if (!gamePaused) {
        myPaddle.movePaddle(code);
        if (!ballLaunched) {
          for(Ball ball: myBalls) {
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
      addPowerup();
    }
  }

  private void addPowerup() {
    int xPos = getRandomNumber(0, Game.SIZE);
    Powerup p = new Powerup(xPos, 0, 10);
    root.getChildren().add(p);
    powerups.add(p);
    p.setId(String.format("powerup%d", powerups.indexOf(p)));
  }

    private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched(boolean status) {
    if (!ballLaunched) {
      for(Ball ball: myBalls) {
        ball.setLaunch();
      }
    }
    ballLaunched = status;
  }

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      for(Ball ball: myBalls) {
        ball.moveBall(elapsedTime);
      }
    }
  }

  public void resetGame() {
    setBallLaunched(false);
    for(Ball ball: myBalls) {
      ball.reset();
    }
    myPaddle.reset();
    numOfBalls = myBalls.size();
  }

  public void checkBallBlockCollision() {
    //ask about where to loop
    for(Ball ball : myBalls){ Iterator<Block> itr = blockList.iterator();
    while (itr.hasNext()) {
      Block block = itr.next();
      if (ball.checkBallObjectCollision(block)) {
        block.handleHit();
        if (block.isBlockBroken()) {
          root.getChildren().remove(block);
          itr.remove();
        }
      }
      }
    }
  }


  public void dropPowerups(double elapsedTime) {
    if (!gamePaused) {
      for (Powerup powerup : powerups) {
        powerup.drop(elapsedTime);
      }
    }
  }

  public void checkCollision() {
    checkBallBlockCollision();
    for(Ball ball : myBalls) {
      ball.checkBallObjectCollision(myPaddle);
    }
  }

  public void checkBallDroppedThroughBottom() {
      for (Ball ball : myBalls) {
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

