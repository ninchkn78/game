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
  public Paddle myPaddle;
  private List<String[]> readBlockFile(String dataSource){
    List<String[]> blocks = new ArrayList<>();
    InputStream textFile = null;
    try {
      textFile = Objects.requireNonNull(LevelConfig.class.getClassLoader().getResource(dataSource))
              .openStream();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] block;
    Scanner scan = new Scanner(textFile);
    while (scan.hasNextLine()) {
      block = scan.nextLine().split(",");
      blocks.add(block);
    }
    return blocks;
  }
  void setUpBlocks(Group root, String dataSource){
    int rowNum, colNum = 0;
    List<String[]> blockFile = readBlockFile(dataSource);
    for(String[] row : blockFile){
      rowNum = 0;
      for(String blockType : row){
        root.getChildren().add(getBlock(blockType,rowNum,colNum));
        rowNum++;
      }
      colNum++;
    }
  }
  private Block getBlock(String blockType, int rowNum, int colNum){
    if(blockType.equals("0")){
      return new ExampleBlock(rowNum,colNum,BLOCK_WIDTH, BLOCK_HEIGHT);
    }
    return null;
  }

  private void setUpPaddle(Group root){
    myPaddle = new Paddle(200,300,75,10);
    root.getChildren().add(myPaddle);
  }

  public void setUpLevel(int level, Group root){
    if(level == 1){
      //change later to just be one statement
      setUpBlocks(root,"level1.txt");
      setUpPaddle(root);
    }
  }
  public static void main(String args[]){
    LevelConfig level = new LevelConfig();
    level.readBlockFile("level1.txt");
  }
}
