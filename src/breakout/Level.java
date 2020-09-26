package breakout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Level {

  private static final int PADDLE_WIDTH = 75;
  private final Group myRoot;
  private final List<Powerup> myPowerups = new ArrayList<>();
  private final int numOfBalls;
  private Paddle myPaddle;
  private List<Ball> myBalls = new ArrayList<>();
  private List<Block> myBlocks = new ArrayList<>();
  private Display myDisplay;
  private boolean immunity;

  public Level(Group gameRoot, int paddleX, int paddleY, int numBalls, List<Block> blockList) {
    myRoot = gameRoot;
    setUpPaddle(paddleX, paddleY);
    setUpBalls(numBalls);
    setUpBlocks(blockList);
    setUpDisplay();
    numOfBalls = numBalls;
  }

  void setUpBlocks(List<Block> blockList) {
    myBlocks = blockList;
    myRoot.getChildren().addAll(blockList);
  }

  public void dropPowerups(double elapsedTime) {
    for (Powerup powerup : myPowerups) {
      powerup.drop(elapsedTime);
      if (powerup.poweredUp(this, myPaddle)) {
        remove(powerup);
      }
    }
  }

  private void checkBallBlockCollision(Ball ball) {
    Iterator<Block> itr = myBlocks.iterator();
    while (itr.hasNext()) {
      Block block = itr.next();
      if (ball.checkBallObjectCollision(block)) {
        block.handleHit(this);
        if (block.isBlockBroken()) {
          myDisplay.incrementScore(1, myRoot);
          remove(block);
          itr.remove();
        }
      }
    }
  }

  public void checkCollisions() {
    for (Ball ball : myBalls) {
      checkBallBlockCollision(ball);
      ball.checkBallObjectCollision(myPaddle);
    }
  }

  public void moveBall(double elapsedTime) {
    for (Ball ball : myBalls) {
      ball.moveBall(elapsedTime);
    }
    checkBallDroppedThroughBottom();
  }

  public void changeBallSpeed(double modifier){
    for (Ball ball: myBalls){
      ball.changeBallSpeed(modifier);
    }
  }

  public void setBallLaunched() {
    for (Ball ball : myBalls) {
      if (!ball.isBallLaunched()) {
        ball.setLaunch();
      }
    }
  }

  public void addBall() {
    Ball ball = new Ball(Game.SIZE / 2, 293, 5);
    ball.setId(String.format("ball%d", myBalls.size() + 1));
    myRoot.getChildren().add(ball);
    myBalls.add(ball);
  }

  public void addPowerupFromBlock(Block block) {
    int xPos = block.getxPos();
    int yPos = block.getyPos();
    // TODO: 2020-09-26 add a random powerup
    addPowerupFrom(xPos,yPos);
  }

  private void addPowerupFrom(int xPos, int yPos){
    Powerup p = new PaddlePowerup(xPos, yPos, 10);
    myRoot.getChildren().add(p);
    myPowerups.add(p);
    p.setId(String.format("powerup%d", myPowerups.indexOf(p)));
  }
  public void addPowerUp(){
    int xPos = GameLogic.getRandomNumber(0, Game.SIZE);
    addPowerupFrom(xPos,0);
  }

  private void setUpBalls(int numOfBalls) {
    myBalls = new ArrayList<>();
    while (numOfBalls > 0) {
      addBall();
      numOfBalls -= 1;
    }
  }

  private void setUpPaddle(int x, int y) {
    myPaddle = new Paddle(x, y, Level.PADDLE_WIDTH, 10);
    myPaddle.setId("myPaddle");
    myRoot.getChildren().add(myPaddle);
  }

  private void setUpDisplay() {
    myDisplay = new Display();
    Text myStats = myDisplay.createDisplay();
    myRoot.getChildren().add(myStats);
  }

  public void reset() {
    for (Ball ball : myBalls) {
      ball.reset();
      remove(ball);
    }
    myPaddle.reset();
    setUpBalls(numOfBalls);
  }

  public void remove(Shape object) {
    myRoot.getChildren().remove(object);
  }

  public void add(Shape object) {
    myRoot.getChildren().add(object);
  }

  public void movePaddle(KeyCode code) {
    myPaddle.movePaddle(code);
  }

  public void moveBallsWithPaddle(KeyCode code) {
    for (Ball ball : myBalls) {
      ball.moveBallWithPaddle(code);
    }
  }

  public void changeLives(int change) {
    // TODO: 2020-09-26 change Name ?  
    myDisplay.decrementLives(change, myRoot);
  }

  private void checkBallDroppedThroughBottom() {
    int numBalls = myBalls.size();
    for (Ball ball : myBalls) {
      if (!immunity) {
        if (ball.checkBallDroppedThroughBottom()) {
          numBalls -= 1;
          //add something to account for if we want loss to only be on one ball ?
        }
      } else {
        ball.ignoreBottom();
      }
      if (numBalls == 0) {
        reset();
        changeLives(1);
      }
    }
  }

  public boolean noLives() {
    return myDisplay.getLives() <= 0;
  }

  public boolean noBlocks() {
    return myBlocks.isEmpty();
  }


  public void removeBlock(int index) { //index of block in blocklist
    Block block = myBlocks.get(index); //get block from list at index
    myBlocks.remove(index); //remove block from list at index
    block.handleHit(this);
    remove(block); //remove block from scene
  }

  // gets current value of immunity
  public Boolean getImmunity() {
    return immunity;
  }

  //sets value of immunity to argument passed
  public void setImmunity(Boolean identity) {
    immunity = identity;
  }

  //toggles immunity
  // TODO: immunity could be in the ball?
  public void alternateImmunity() {
    immunity = !immunity;
  }
}




