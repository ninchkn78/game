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
  private static final int PADDLE_XPOS = Main.SIZE / 2 - PADDLE_WIDTH / 2;
  private static final int PADDLE_YPOS = 300;

  private Paddle myPaddle;
  private Ball myBall;
  private List<Block> blockList;

  private Group root;

  public LevelConfig() {
    root = new Group();
  }

  public Ball getMyBall() {
    return myBall;
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
    blockList = new ArrayList<>();
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

  private void setUpBall() {
    myBall = new Ball(Main.SIZE / 2, 293, 5);
    myBall.setId("myBall");
    root.getChildren().add(myBall);
  }

  private void setUpPaddle() {
    myPaddle = new Paddle(PADDLE_XPOS, PADDLE_YPOS, 75, 10);
    myPaddle.setId("myPaddle");
    root.getChildren().add(myPaddle);
  }

  //is this open closed principle ?
  public void setUpLevel(int level) {
    String blockFile = String.format("level%d.txt", level);
    setUpBlocks(blockFile);
    setUpPaddle();
    setUpBall();
  }
}


