package breakout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.Group;

public class LevelConfig {

  private final static int BLOCK_WIDTH = 50;
  private final static int BLOCK_HEIGHT = 10;
  private static final int PADDLE_WIDTH = 75;
  private static final int PADDLE_XPOS = Game.SIZE / 2 - PADDLE_WIDTH / 2;
  private static final int PADDLE_YPOS = 300;

  private Paddle myPaddle;
  private List<Ball> myBalls = new ArrayList<>();
  private List<Block> blockList = new ArrayList<>();;

  private Group root;

  public LevelConfig(int level) {
    root = new Group();
    setUpLevel(level);
  }

  public List<Ball> getMyBall() {
    return myBalls;
  }

  public Paddle getMyPaddle() {
    return myPaddle;
  }

  public Group getRoot() {
    return root;
  }

  public List<String[]> readBlockFile(String dataSource) {
    List<String[]> blocks = new ArrayList<>();
    InputStream textFile = null;
    try {
      textFile = Objects.requireNonNull(LevelConfig.class.getClassLoader().getResource(dataSource))
          .openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] block;
    Scanner scan = new Scanner(Objects.requireNonNull(textFile));
    while (scan.hasNextLine()) {
      block = scan.nextLine().split(",");
      blocks.add(block);
    }
    return blocks;
  }

  public List<Block> getBlockList() {
    return blockList;
  }

  void setUpBlocks(String dataSource) {
    makeListOfBlocks(dataSource);
    for (Block block : blockList) {
      root.getChildren().add(block);
    }
  }

  // need this to track collisions
  private void makeListOfBlocks(String dataSource) {
    int rowNum, colNum = 0;
    List<String[]> blockFile = readBlockFile(dataSource);
    for (String[] row : blockFile) {
      rowNum = 0;
      for (String blockType : row) {
        Block block = getBlock(blockType, rowNum, colNum);
        if (block != null) {
          block.setId(String.format("%d,%d", rowNum, colNum));
          blockList.add(block);
        }
        rowNum++;
      }
      colNum++;
    }
  }
  private Block getBlock(String blockType, int rowNum, int colNum) {
    if (blockType.equals("0")) {
      return new BasicBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT);
    }
    return null;
  }

  private void addBall(int id) {
    Ball ball = new Ball(Game.SIZE / 2, 293, 5);
    ball.setId(String.format("ball%d",id));
    root.getChildren().add(ball);
    myBalls.add(ball);
  }
  private void setUpBalls(int numOfBalls) {
    while(numOfBalls > 0){
      addBall(numOfBalls);
      numOfBalls -= 1;
    }
  }

  private void setUpPaddle() {
    myPaddle = new Paddle(PADDLE_XPOS, PADDLE_YPOS, PADDLE_WIDTH, 10);
    myPaddle.setId("myPaddle");
    root.getChildren().add(myPaddle);
  }

  //is this open closed principle ?
  public void setUpLevel(int level) {
    String blockFile = String.format("level%d.txt", level);
    setUpBlocks(blockFile);
    setUpPaddle();
    setUpBalls(2);
  }
}


