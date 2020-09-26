package breakout;

import javafx.scene.paint.Color;

public class DurableBlock extends Block{

    private static final int X_DISPLACEMENT = 10;
    private static final int Y_DISPLACEMENT = 5;
    private static final int SPACE_FROM_TOP = 50;
    private int count = 3;
    private boolean blockBroken = false;

    public DurableBlock(int row, int col, int width, int height) {
        super(row * (width + X_DISPLACEMENT),
                col * (height + Y_DISPLACEMENT) + SPACE_FROM_TOP,
                width,
                height);
        this.setFill(Color.color( .5, 0, .5));
    }
    @Override
    public void handleHit(Level level) {
        this.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        this.count -= 1;
        if (this.count == 0){
            blockBroken = true;
        }
    }

    @Override
    public boolean isBlockBroken() {
        return blockBroken;
    }
}
