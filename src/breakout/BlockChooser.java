package breakout;

import static breakout.LevelConfig.BLOCK_HEIGHT;
import static breakout.LevelConfig.BLOCK_WIDTH;

import breakout.blocks.BallPowerupBlock;
import breakout.blocks.BasicBlock;
import breakout.blocks.DurableBlock;
import breakout.blocks.ExtraLifePowerupBlock;
import breakout.blocks.PaddlePowerupBlock;
import java.util.ArrayList;
import java.util.List;

public class BlockChooser extends TypeChooser {

  //only have to change block chooser when adding a new type of block

  private final List<String> blockTypes = new ArrayList<>();
  private final int rowNum;
  private final int colNum;

  public BlockChooser(int row, int col) {
    rowNum = row;
    colNum = col;
    makeBlockTypesList();
    addAllTypes();
  }

  private void makeBlockTypesList(){
    blockTypes.add("0");
    blockTypes.add("P");
    blockTypes.add("B");
    blockTypes.add("D");
    blockTypes.add("L");
  }

  @Override
  public int getTypeIndex(String type) {
    return blockTypes.indexOf(type);
  }

  @Override
  public void addAllTypes() {
    addTypeToMap(new BasicBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT));
    addTypeToMap(new PaddlePowerupBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT));
    addTypeToMap(new BallPowerupBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT));
    addTypeToMap(new DurableBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT));
    addTypeToMap(new ExtraLifePowerupBlock(rowNum, colNum, BLOCK_WIDTH, BLOCK_HEIGHT));
  }
}
