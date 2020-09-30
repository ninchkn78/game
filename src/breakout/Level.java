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

  public static final int PADDLE_HEIGHT = 10;
  public static final int BALL_SIZE = 5;
  private static final double PADDLE_WIDTH = 75;
  public static final double EQUAL_ODDS = .5;
  private final Group myRoot;
  private final List<Powerup> myPowerups = new ArrayList<>();
  private final int numOfTopBalls;
  private final int numOfBottomBalls;
  private Paddle myPaddle;
  private List<Ball> myBalls = new ArrayList<>();
  private List<Block> myBlocks = new ArrayList<>();
  private Display myDisplay;
  private boolean immunity;

  public Level(Group gameRoot, int paddleX, int paddleY, int numTopBalls, int numBottomballs,
      List<Block> blockList, int levelNum) {
    myRoot = gameRoot;
    setUpPaddle(paddleX, paddleY);
    setUpBalls(numTopBalls, numBottomballs);
    setUpBlocks(blockList);
    setUpDisplay(levelNum);
    numOfTopBalls = numTopBalls;
    numOfBottomBalls = numBottomballs;
  }

  void setUpBlocks(List<Block> blockList) {
    myBlocks = blockList;
    myRoot.getChildren().addAll(blockList);
  }

  /**
   * Moves the powerups and if they hit the paddle, perform their designated powerup and then remove
   * it
   * @param elapsedTime
   */
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
          myDisplay.changeScore(1, myRoot);
          remove(block);
          itr.remove();
        }
      }
    }
  }

  /**
   * Handles when each ball hits either the paddle or a block
   */
  public void checkCollisions() {
    for (Ball ball : myBalls) {
      checkBallBlockCollision(ball);
      ball.checkBallObjectCollision(myPaddle);
    }
  }

  /**
   * Tells all the balls to move across the screen
   * @param elapsedTime
   */
  public void moveBall(double elapsedTime) {
    for (Ball ball : myBalls) {
      ball.moveBall(elapsedTime);
    }
  }

  /**
   * Tells the ball to change its speed by a factor of the modifier
   * @param modifier
   */
  public void changeBallSpeed(double modifier) {
    for (Ball ball : myBalls) {
      ball.changeBallSpeed(modifier);
    }
  }

  /**
   * Tells the paddle to change its width based on a factor of the modifier
   * @param modifier
   */
  public void changePaddleWidth(double modifier) {
    myPaddle.changePaddleWidth(modifier);
  }

  /**
   * Tells the paddle to change its speed based on a factor of the modifier
   * @param modifier
   */
  public void changePaddleSpeed(double modifier) {myPaddle.changePaddleSpeed(modifier);}

  /**
   * Launch every ball that has not already been launched
   */
  public void setBallLaunched() {
    for (Ball ball : myBalls) {
      if (!ball.isBallLaunched()) {
        ball.setLaunch();
      }
    }
  }

  private void addTopBall() {
    int ballY = (int) myPaddle.getY() - PADDLE_HEIGHT;

    addBall(ballY);
  }

  private void addBottomBall() {
    int ballY = (int) myPaddle.getY() + PADDLE_HEIGHT * 2;
    addBall(ballY);
  }



  //probability of adding either top ball or bottom ball is based off of how many of each are in the level
  private void addBall(int ballY) {
    int ballX = (int) (myPaddle.getX() + PADDLE_WIDTH / 2);
    Ball ball = new Ball(ballX, ballY, BALL_SIZE);
    ball.setId(String.format("ball%d", myBalls.size()));
    add(ball);
    myBalls.add(ball);
  }

  /**
   * Adds a new ball to the stage,
   * the probability of a top ball or a bottom ball is dependent on the ratio of the initial number of balls
   */
  public void addNewBall() {
    double topBallProb = (double) numOfTopBalls / (numOfTopBalls + numOfBottomBalls);
    if (Math.random() <= topBallProb) {
      addTopBall();
    } else {
      addBottomBall();
    }
  }

  /**
   * Given a block and a powerup type, adds a powerup of that type at the position of the block
   * @param block
   * @param powerupType
   */
  public void addPowerupFromBlock(Block block, int powerupType) {
    double xPos = block.getX();
    double yPos = block.getY();
    addPowerupFrom(xPos, yPos, powerupType);
  }

  private void addPowerupFrom(double xPos, double yPos, int powerupType) {
    PowerupChooser powerupChooser = new PowerupChooser(xPos, yPos);
    Powerup powerup = (Powerup) powerupChooser.getType(Integer.toString(powerupType));
    add(powerup);
    myPowerups.add(powerup);
    powerup.setId(String.format("powerup%d", myPowerups.indexOf(powerup)));
  }

  /**
   * Adds a random powerup to a random x coordinate, and a y coordinate depending on the position of
   * the paddle.
   * If the paddle is at the bottom, adds powerup to the top
   * If the paddle is at the top, adds powerup to the bottom
   * If the paddle is in the middle, an equal chance of either.
   */
  public void addRandomPowerup() {
    int xPos = GameLogic.getRandomNumber(0, Game.SIZE);
    int yPos = getRandomPowerupYPosition();
    addPowerupFrom(xPos, yPos, TypeChooser.RANDOM_TYPE);
  }

  private int getRandomPowerupYPosition() {
    if (myPaddle.getY() < Game.SIZE / 2) {
      return Game.SIZE;
    } else if (myPaddle.getY() > Game.SIZE / 2) {
      return Game.TOP_OF_STAGE;
    } else {
      return Math.random() <= EQUAL_ODDS ? Game.SIZE : Game.TOP_OF_STAGE;
    }
  }

  private void setUpBalls(int numTopBalls, int numBottomBalls) {
    //is there a way to not have this be duplicated?
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
    add(myPaddle);
  }
  private void setUpDisplay(int levelNum) {
    myDisplay = new Display();
    Text myStats = myDisplay.createDisplay(levelNum);
    add(myStats);
  }


  /**
   * Resets the game by moving the paddle to its original position, clearing all the bals, then
   * setting up new balls based off of the original number of balls
   */
  public void reset() {
    for (Ball ball : myBalls) {
      ball.reset();
      remove(ball);
    }
    myPaddle.reset();
    setUpBalls(numOfTopBalls, numOfBottomBalls);
  }

  /**
   * Removes the specified object from the root
   * @param object
   */
  public void remove(Shape object) {
    myRoot.getChildren().remove(object);
  }

  /**
   * Adds the object to the root
   * @param object
   */
  public void add(Shape object) {
    myRoot.getChildren().add(object);
  }

  /**
   * Tells the paddle to move either left or right
   * @param code LEFT or RIGHT
   */
  public void movePaddle(KeyCode code) {
    myPaddle.movePaddle(code);
  }

  /**
   * Tells all balls to move either horizontally with the paddle
   * @param code LEFT or RIGHT
   */
  public void moveBallsWithPaddle(KeyCode code) {
    for (Ball ball : myBalls) {
      ball.moveBallWithPaddle(code);
    }
  }

  public void changeLives(int change) {
    myDisplay.changeLives(change, myRoot);
  }

  // TODO: generalize this to check for when ball drops through top too (for later levels), maybe add as a parameter
  public boolean checkBallDroppedThroughBottom(int levelNum) {
    int numBalls = myBalls.size();
    for (Ball ball : myBalls) {
      if (!immunity) {
        if (ball.checkBallDroppedThroughBottom(levelNum)) {
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

  /**
   * Checks to see if the number of lives is zero
   * @return true if number of lives is <= 0, false otherwise
   */
  public boolean noLives() {
    return myDisplay.getLives() <= 0;
  }

  /**
   * Checks to see if all blocks have been broken
   * @return true if there are no blocks remaining, false otherwise
   */
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
  public void alternateImmunity() {
    immunity = !immunity;
  }
}




