package breakout;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LevelConfig {
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
  void addBlocksToRoot(Group root, String dataSource){
    int rowNum = 0, colNum;
    List<String[]> blockFile = readBlockFile(dataSource);
    for(String[] row : blockFile){
      colNum = 0;
      for(String blockType : row){
        root.getChildren().add(getBlock(blockType,rowNum,colNum));
        colNum++;
      }
      rowNum++;
    }
  }
  private Block getBlock(String blockType, int rowNum, int colNum){
    if(blockType.equals("0")){
      return new ExampleBlock(rowNum,colNum);
    }
    return null;
  }
  public static void main(String args[]){
    LevelConfig level = new LevelConfig();
    level.readBlockFile("level1.txt");
  }
}
