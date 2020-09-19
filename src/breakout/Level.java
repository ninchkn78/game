package breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;

public class Level {

  private static final int PADDLE_WIDTH = 75;

  private Paddle myPaddle;
  private List<Ball> myBalls ;
  private List<Block> myBlocks = new ArrayList<>();
  private Group myRoot;
  private List<Powerup> myPowerups = new ArrayList<>();

  public Level(Group gameRoot, int paddleX, int paddleY, int numBalls, List<Block> blockList) {
    myRoot = gameRoot;
    setUpPaddle(paddleX,paddleY, PADDLE_WIDTH, 10);
    setUpBalls(numBalls);
    setUpBlocks(blockList);
  }

  public List<Ball> getBalls() {
    return myBalls;
  }

  public List<Powerup> getPowerups(){
    return myPowerups;
  }

  public Paddle getPaddle() {
    return myPaddle;
  }

  public Group getRoot() {
    return myRoot;
  }

  public List<Block> getBlockList() {
    return myBlocks;
  }

  void setUpBlocks(List<Block> blockList) {
    myBlocks = blockList;
    myRoot.getChildren().addAll(blockList);
  }


  public void addBall(int id) {
    Ball ball = new Ball(Game.SIZE / 2, 293, 5);
    ball.setId(String.format("ball%d", id));
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
      addBall(numOfBalls);
      numOfBalls -= 1;
    }
  }

  private void setUpPaddle(int x, int y, int width, int height) {
    myPaddle = new Paddle(x, y, width, height);
    myPaddle.setId("myPaddle");
    myRoot.getChildren().add(myPaddle);
  }
  public void reset(){
    setUpBalls(2);
  }

}


