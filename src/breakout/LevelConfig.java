package breakout;

import breakout.blocks.Block;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.util.Pair;

public class LevelConfig {

  public static final int BLOCK_WIDTH = 42;
  public final static int BLOCK_HEIGHT = 10;
  public static final int TEXT_DISPLACEMENT = 50;
  public static final int PADDLE_X_INDEX = 0;
  public static final int PADDLE_Y_INDEX = 1;
  public static final int NUM_TOP_BALLS_INDEX = 2;
  public static final int NUM_BOTTOM_BALLS_INDEX = 3;
  public static final String LEVEL_FILE_NAME = "level%d.txt";

  private LevelConfig() {
  }

  private static Scanner getLevelConfigFileScanner(String dataSource) {
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

  private static Block getBlock(String blockType, int rowNum, int colNum) {
    BlockChooser blockChooser = new BlockChooser(rowNum,colNum);
    return (Block) blockChooser.getType(blockType);
  }


  public static Level setUpLevel(int level, Group root) {
    root.getChildren().clear();
    String blockFile = String.format(LEVEL_FILE_NAME, level);
    String[] setUpInfo = readFile(blockFile).getKey();
    int paddleX = Integer.parseInt(setUpInfo[PADDLE_X_INDEX]);
    int paddleY = Integer.parseInt(setUpInfo[PADDLE_Y_INDEX]);
    int numTopBalls = Integer.parseInt(setUpInfo[NUM_TOP_BALLS_INDEX]);
    int numBottomBalls = Integer.parseInt(setUpInfo[NUM_BOTTOM_BALLS_INDEX]);
    return new Level(root, paddleX, paddleY, numTopBalls, numBottomBalls,
        makeListOfBlocks(blockFile));
  }
}


