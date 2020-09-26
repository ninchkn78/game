package breakout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.Group;

public class LevelConfig {

  //reads in a block file to make blocks
  //makes a paddle
  //adds balls ?

  //make a paddle, send it to Level, add it to the root
  //make a paddle add it to the root, send the root to the Level
  private static final int BLOCK_WIDTH = 50;
  private final static int BLOCK_HEIGHT = 10;

  private static Scanner getLevelConfigFileScanner(String dataSource){
    InputStream textFile = null;
    try {
      textFile = Objects.requireNonNull(LevelConfig.class.getClassLoader().getResource(dataSource))
          .openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scanner scan = new Scanner(Objects.requireNonNull(textFile));
    return scan;
  }
  private static List<String[]> readBlockFile(String datasource) {
    Scanner scan = getLevelConfigFileScanner(datasource);
    List<String[]> blocks = new ArrayList<>();
    String[] block;
//    String[] setUpInfo;
//    setUpInfo = scan.nextLine().split(",");
//    scan.nextLine();
    //blocks start on second line
    while (scan.hasNextLine()) {
      block = scan.nextLine().split(",");
      blocks.add(block);
    }
    return blocks;
  }


  // need this to track collisions
  private static List<Block> makeListOfBlocks(String dataSource) {
    List<Block> blockList = new ArrayList<>();
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
    return blockList;
  }
  //ask about this
  private static Block getBlock(String blockType, int rowNum, int colNum) {
    if (blockType.equals("0")) {
      return new BasicBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT);
    }
    else if(blockType.equals("P"))
      return new PowerupBlock(rowNum,colNum,BLOCK_WIDTH,BLOCK_HEIGHT);
    else if(blockType.equals("D")) {
      return new DurableBlock(rowNum,colNum,BLOCK_WIDTH,BLOCK_HEIGHT);
    }
    return null;
  }


  public static Level setUpLevel(int level, Group root) {
    // TODO: 2020-09-26 add information to file  
    root.getChildren().clear();
    String blockFile = String.format("level%d.txt", level);
//    String setUpInfo[] = //;
//    int paddleX = setUpInfo[0];
//    int paddleY = setUpInfo[1];
//    int numBalls = setUpInfo[2];
    int paddleX = 137;
    int paddleY = 300;
    int numBalls = 1;
    System.out.println(makeListOfBlocks(blockFile));
    return new Level(root, paddleX, paddleY, numBalls, makeListOfBlocks(blockFile));
  }

  public static List<Block> getBlockList(int level) {
    return makeListOfBlocks(String.format("level%d.txt", level));
  }
}


