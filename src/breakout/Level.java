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

  private Paddle myPaddle;
  private List<Ball> myBalls = new ArrayList<>() ;
  private List<Block> myBlocks = new ArrayList<>();
  private final Group myRoot;
  private final List<Powerup> myPowerups = new ArrayList<>();
  private final int numOfBalls;
  private Display myDisplay;
  private Text myStats;

  public Level(Group gameRoot, int paddleX, int paddleY, int numBalls, List<Block> blockList) {
    myRoot = gameRoot;
    setUpPaddle(paddleX,paddleY, PADDLE_WIDTH, 10);
    setUpBalls(numBalls);
    setUpBlocks(blockList);
    setUpDisplay();
    numOfBalls = numBalls;
  }
  public Group getRoot() {
    return myRoot;
  }

  void setUpBlocks(List<Block> blockList) {
    myBlocks = blockList;
    myRoot.getChildren().addAll(blockList);
  }
  public void dropPowerups(double elapsedTime) {
      for (Powerup powerup : myPowerups) {
        powerup.drop(elapsedTime);
        if(powerup.powerUpPaddle(myPaddle)){
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
          // TODO score changes here
          myDisplay.incrementScore(1, myRoot);
          remove(block);
          itr.remove();
        }
      }
    }
  }
  public void checkCollisions(){
    for(Ball ball : myBalls){
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

  public void setBallLaunched() {
    for (Ball ball : myBalls) {
      if(!ball.isBallLaunched()) {
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

  public void addPowerup() {
    int xPos = GameLogic.getRandomNumber(0, Game.SIZE);
    Powerup p = new Powerup(xPos, 0, 10);
    myRoot.getChildren().add(p);
    myPowerups.add(p);
    p.setId(String.format("powerup%d", myPowerups.indexOf(p)));
  }

  private void setUpBalls(int numOfBalls) {
    myBalls = new ArrayList<>();
    while (numOfBalls > 0) {
      addBall();
      numOfBalls -= 1;
    }
  }

  private void setUpPaddle(int x, int y, int width, int height) {
    myPaddle = new Paddle(x, y, width, height);
    myPaddle.setId("myPaddle");
    myRoot.getChildren().add(myPaddle);
  }

  private void setUpDisplay(){
    myDisplay = new Display();
    myStats = myDisplay.createDisplay();
    myRoot.getChildren().add(myStats);

  }
  public void reset(){
    for (Ball ball : myBalls) {
      ball.reset();
      remove(ball);
    }
    myPaddle.reset();
    setUpBalls(numOfBalls);
  }

  public void remove(Shape object){
    myRoot.getChildren().remove(object);

  }
  public void add(Shape object){
    myRoot.getChildren().add(object);
  }
  public void movePaddle(KeyCode code){
    myPaddle.movePaddle(code);
  }
  public void moveBallsWithPaddle(KeyCode code) {
    for (Ball ball : myBalls) {
      ball.moveBallWithPaddle(code);
    }
  }
  public void changeLives(int change){
    myDisplay.decrementLives(change,myRoot);
  }
  private void checkBallDroppedThroughBottom() {
    int numBalls = myBalls.size();
    for (Ball ball : myBalls) {
      if (ball.checkBallDroppedThroughBottom()) {
        numBalls -= 1;
        //add something to account for if we want loss to only be on one ball ?
      }
    }
    if (numBalls == 0) {
      reset();
      changeLives(1);
    }
  }
  public boolean noLives(){
    return myDisplay.getLives() <= 0;
  }
  public boolean noBlocks(){
    return myBlocks.isEmpty();
  }
}


