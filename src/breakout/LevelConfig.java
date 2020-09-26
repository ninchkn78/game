package breakout;

import breakout.blocks.BallPowerupBlock;
import breakout.blocks.BasicBlock;
import breakout.blocks.Block;
import breakout.blocks.DurableBlock;
import breakout.blocks.PaddlePowerupBlock;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.util.Pair;

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
    return new Scanner(Objects.requireNonNull(textFile));
  }
  private static Pair<String[], List<String[]>> readFile(String datasource) {
    Scanner scan = getLevelConfigFileScanner(datasource);
    List<String[]> blocks = new ArrayList<>();
    String[] block, setUpInfo;
    setUpInfo = scan.nextLine().split(",");
    //blocks start on second line
    while (scan.hasNextLine()) {
      block = scan.nextLine().split(",");
      blocks.add(block);
    }
    return new Pair<>(setUpInfo, blocks);
  }


  // need this to track collisions
  private static List<Block> makeListOfBlocks(String dataSource) {
    List<Block> blockList = new ArrayList<>();
    int rowNum, colNum = 0;
    List<String[]> blockFile = readFile(dataSource).getValue();
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
      return new PaddlePowerupBlock(rowNum,colNum,BLOCK_WIDTH,BLOCK_HEIGHT);
    else if(blockType.equals("B"))
      return new BallPowerupBlock(rowNum,colNum,BLOCK_WIDTH,BLOCK_HEIGHT);
    else if(blockType.equals("D")) {
      return new DurableBlock(rowNum,colNum,BLOCK_WIDTH,BLOCK_HEIGHT);
    }
    return null;
  }


  public static Level setUpLevel(int level, Group root) {
    root.getChildren().clear();
    String blockFile = String.format("level%d.txt", level);
    String[] setUpInfo = readFile(blockFile).getKey();
    int paddleX = Integer.parseInt(setUpInfo[0]);
    int paddleY = Integer.parseInt(setUpInfo[1]);
    int numBalls = Integer.parseInt(setUpInfo[2]);
    return new Level(root, paddleX, paddleY, numBalls, makeListOfBlocks(blockFile));
  }

}


