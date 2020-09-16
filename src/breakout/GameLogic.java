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

  private Ball myBall;
  private Paddle myPaddle;
  private List<Block> blockList;
  private Group root;
  private List<Powerup> powerups = new ArrayList<>();

  public GameLogic(LevelConfig currentConfig) {
    myBall = currentConfig.getMyBall();
    myPaddle = currentConfig.getMyPaddle();
    blockList = currentConfig.getBlockList();
    root = currentConfig.getRoot();
  }
  //ask about this method if statements
  //ask about these methods in their own class
  public void handleKeyInput(KeyCode code) {

    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
      if (!gamePaused) {
        myPaddle.movePaddle(code);
        if (!ballLaunched) {
          myBall.moveBallWithPaddle(code);
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
    if (code.equals(KeyCode.P)){
      addPowerup();
    }
  }

  private void addPowerup() {
    int xPos = getRandomNumber(0, Game.SIZE);
    Powerup p = new Powerup(xPos,0, 10);
    root.getChildren().add(p);
    powerups.add(p);
    p.setId(String.format("powerup%d", powerups.indexOf(p)));
  }

  public static int getRandomNumber(int min, int max) {
    Random random = new Random();
    return random.nextInt(max - min) + min;
  }

  void step(double elapsedTime) {
    moveBall(elapsedTime);
  }

  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched(boolean status) {
    if(!ballLaunched){
      myBall.setLaunch();
    }
    ballLaunched = status;
  }

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      myBall.moveBall(elapsedTime);
    }
  }

  public void resetGame() {
    setBallLaunched(false);
    myBall.reset();
    myPaddle.reset();
  }
  public void checkBallBlockCollision(){
    Iterator<Block> itr = blockList.iterator();
    while (itr.hasNext()) {
      Block block = itr.next();
      if(myBall.checkBallObjectCollision(block)){
        block.handleHit();
        if(block.isBlockBroken()) {
          root.getChildren().remove(block);
          itr.remove();
        }
      }}
    }


  public void dropPowerups(double elapsedTime){
    if(!gamePaused){
    for(Powerup powerup : powerups) {
      powerup.drop(elapsedTime);
    }
    }
  }
  public void checkCollision(){
      checkBallBlockCollision();
      myBall.checkBallObjectCollision(myPaddle);
  }
  public void checkBallDropsThroughBottom(){
    if (myBall.checkBallDropsThroughBottom()){
     resetGame();
    }
  }
}

