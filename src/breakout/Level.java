package breakout;

import breakout.blocks.Block;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;



public class Level {

  //might be worth it to think about putting all the balls in their own class

  private static final double PADDLE_WIDTH = 75;
  public static final int RANDOM_POWERUP = -1;
  public static final int PADDLE_HEIGHT = 10;
  public static final int BALL_SIZE = 5;
  private final Group myRoot;
  private final List<Powerup> myPowerups = new ArrayList<>();
  private final int numOfTopBalls;
  private final int numOfBottomBalls;
  private Paddle myPaddle;
  private List<Ball> myBalls = new ArrayList<>();
  private List<Block> myBlocks = new ArrayList<>();
  private Display myDisplay;
  private boolean immunity;

  public Level(Group gameRoot, int paddleX, int paddleY, int numTopBalls, int numBottomballs, List<Block> blockList) {
    myRoot = gameRoot;
    setUpPaddle(paddleX, paddleY);
    setUpBalls(numTopBalls, numBottomballs);
    setUpBlocks(blockList);
    setUpDisplay();
    numOfTopBalls = numTopBalls;
    numOfBottomBalls = numBottomballs;
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


  //different way to do this would to do the same thing that's happening in powerup
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
  }

  public void changeBallSpeed(double modifier){
    for (Ball ball: myBalls){
      ball.changeBallSpeed(modifier);
    }
  }
  public void changePaddleWidth(double modifier){
    myPaddle.changePaddleWidth(modifier);
  }

  public void setBallLaunched() {
    for (Ball ball : myBalls) {
      if (!ball.isBallLaunched()) {
        ball.setLaunch();
      }
    }
  }

  private void addTopBall(){
    int ballY = (int) myPaddle.getY() - PADDLE_HEIGHT;
    addBall(ballY);
  }
  private void addBottomBall(){
    int ballY = (int) myPaddle.getY() + PADDLE_HEIGHT * 2;
    addBall(ballY);
  }

  //probability of adding either top ball or bottom ball is based off of how many of each are in the level
  private void addBall(int ballY) {
    int ballX = (int) (myPaddle.getX() + PADDLE_WIDTH/2);
    Ball ball = new Ball(ballX, ballY, BALL_SIZE);
    ball.setId(String.format("ball%d", myBalls.size()));
    myRoot.getChildren().add(ball);
    myBalls.add(ball);
  }
  public void addNewBall(){
    double topBalls = numOfTopBalls;
    double topBallProb = topBalls / (numOfTopBalls + numOfBottomBalls) ;
    double check = Math.random();
    System.out.println(topBallProb);
    System.out.println(check);
    if (check <= topBallProb) {
      addTopBall();
    } else {
      addBottomBall();
    }

  }
  public void addPowerupFromBlock(Block block, int powerupType) {
    double xPos = block.getX();
    double yPos = block.getY();
    addPowerupFrom(xPos,yPos, powerupType);
  }

  private void addPowerupFrom(double xPos, double yPos, int powerupType){
    PowerupChooser powerupChooser = new PowerupChooser(xPos,yPos);
    Powerup powerup = powerupChooser.getPowerup(powerupType);
    myRoot.getChildren().add(powerup);
    myPowerups.add(powerup);
    powerup.setId(String.format("powerup%d", myPowerups.indexOf(powerup)));
  }

  public void addRandomPowerup(){
    int xPos = GameLogic.getRandomNumber(0, Game.SIZE);
    addPowerupFrom(xPos,0, RANDOM_POWERUP);
  }

  private void setUpBalls(int numTopBalls, int numBottomBalls) {
    // TODO: 2020-09-27 remove duplicated code
    myBalls = new ArrayList<>();
    while (numTopBalls > 0) {
      addTopBall();
      numTopBalls -= 1;
    }
    while (numBottomBalls > 0) {
      addBottomBall();
      numBottomBalls -= 1;
    }
  }

  private void setUpPaddle(int x, int y) {
    myPaddle = new Paddle(x, y, Level.PADDLE_WIDTH, PADDLE_HEIGHT);
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
    setUpBalls(numOfTopBalls, numOfBottomBalls);
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
    // TODO: 2020-09-26 change name from decrement ?
    myDisplay.decrementLives(change, myRoot);
  }

  // TODO: generalize this to check for when ball drops through top too (for later levels), maybe add as a parameter
  public boolean checkBallDroppedThroughBottom() {
    int numBalls = myBalls.size();
    for (Ball ball : myBalls) {
      if (!immunity) {
        if (ball.checkBallDroppedThroughBottom()) {
          numBalls -= 1;
        }
      } else {
        ball.ignoreBottom();
      }
      if (numBalls == 0) {
        reset();
        changeLives(1);
        return true;
      }
    }
    return false;
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
  // TODO: immunity could be in ball? not sure about this one
  public void alternateImmunity() {
    immunity = !immunity;
  }
}




