package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.Iterator;


public class ExplodingBlock extends Block {

    public final static int Y_DISPLACEMENT = 5;
    public final static int X_DISPLACEMENT = 10;
    public final static int SPACE_FROM_TOP = 50;
    private int row;
    private int col;

    private boolean blockHit = false;
    private boolean blockBroken = false;


    public ExplodingBlock(int row, int col, int width, int height) {
        super(row * (width + X_DISPLACEMENT),
                col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP,
                width,
                height);
        this.row = row;
        this.col = col;
        this.setFill(Color.color(.3, .4, .1));

    }

    @Override
    public boolean isBlockBroken() {
        return blockBroken;
    }

    @Override
    public void handleHit(Level level) {

        String blockAboveID = (row + 1) + "," + col;
        System.out.println(blockAboveID);
        String blockbelowID = (row - 1) + "," + col;
        System.out.println(blockbelowID);
        String blockLeftID = row + "," + (col - 1);
        String blockRightID = row + "," + (col + 1);
        Group root = level.getRoot();
        Iterator<Node> itr = root.getChildren().iterator();
        blockBroken = true;
        while (itr.hasNext()){
            Node object = itr.next();
            if (object.getId().equals(blockAboveID) || object.getId().equals(blockbelowID) || object.getId().equals(blockLeftID) || object.getId().equals(blockRightID)){
                //level.remove((Block) object);

                itr.remove();
            }
        }
        /*
         {
            System.out.println(object.getId());
            if (object.getId().equals(blockAboveID) || object.getId().equals(blockbelowID) || object.getId().equals(blockLeftID) || object.getId().equals(blockRightID)) {
                level.remove((Block) object);
                System.out.print(object.getId());
                blockBroken = true;
            }
        }

         */
    }
}