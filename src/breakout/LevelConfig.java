package breakout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.Group;


public class LevelConfig {

  private final
  static int BLOCK_WIDTH = 50;
  private final static int BLOCK_HEIGHT = 10;
  private static final int PADDLE_WIDTH = 75;
  private static final int PADDLE_XPOS = Main.SIZE / 2 - PADDLE_WIDTH / 2;
  private static final int PADDLE_YPOS = 300;

  public Paddle myPaddle;
  public Ball myBall;

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

  void setUpBlocks(Group root, String dataSource) {
    List<Block> blockLocations = getBlockLocations(dataSource);
    for (Block block: blockLocations){
      root.getChildren().add(block);
    }


  }
// need this to track collisions
  public List<Block> getBlockLocations(String dataSource){
    List<Block> blocks = new ArrayList<>();
    int rowNum, colNum = 0;
    List<String[]> blockFile = readBlockFile(dataSource);
    for (String[] row : blockFile) {
      rowNum = 0;
      for (String blockType : row) {
        Block block = getBlock(blockType, rowNum, colNum);
        if(block != null){
          block.setId(String.format("%d,%d", rowNum, colNum));
          blocks.add(block);
        }
        rowNum++;

      }
      colNum++;
    }
    return blocks;
  }

  private Block getBlock(String blockType, int rowNum, int colNum) {
    if (blockType.equals("0")) {
      return new BasicBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT);
    }
    return null;
  }

  private void setUpBall(Group root) {
    myBall = new Ball(Main.SIZE / 2, 295, 5);
    myBall.setId("myBall");
    root.getChildren().add(myBall);
  }

  private void setUpPaddle(Group root) {
    System.out.println(PADDLE_XPOS);
    myPaddle = new Paddle(PADDLE_XPOS, PADDLE_YPOS, 75, 10);
    myPaddle.setId("myPaddle");
    root.getChildren().add(myPaddle);
  }

  public void setUpLevel(int level, Group root) {
    String blockFile;
    if (level == 1) {
      //change later to just be one statement
      blockFile = "level1.txt";
      setUpBlocks(root,blockFile );
      setUpPaddle(root);
      setUpBall(root);
    }
  }
}


