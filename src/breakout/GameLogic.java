package breakout;

import java.util.List;
import javafx.scene.Group;
import javafx.scene.input.KeyCode;

public class GameLogic {
  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  private Ball myBall;
  private Paddle myPaddle;
  private List<Block> blockList;
  private Group root;

  public GameLogic(LevelConfig currentConfig) {
    myBall = currentConfig.getMyBall();
    myPaddle = currentConfig.getMyPaddle();
    blockList = currentConfig.getBlockList();
    root = currentConfig.getRoot();
  }
  //ask about this method if statements
  //ask about these methods in their own class
  public void handleKeyInput(KeyCode code) {

    //set up condition for when ball is not launched, ball gets moved too
    if (code.equals(KeyCode.LEFT) || code.equals(KeyCode.RIGHT)) {
      if (!gamePaused) {
        myPaddle.movePaddle(code);
        if (!ballLaunched) {
          myBall.moveBallWithPaddle(code);
        }
      }
    }
    if (code.equals(KeyCode.SHIFT)) {
      setBallLaunched(true);
    }
    if (code.equals(KeyCode.SPACE)) {
      setGamePaused();
    }
    if (code.equals(KeyCode.R)) {
      resetGame();
    }
  }
  void step(double elapsedTime) {
    moveBall(elapsedTime);
  }

  private void setGamePaused() {
    gamePaused = !gamePaused;
  }

  private void setBallLaunched(boolean status) {
    ballLaunched = status;
  }

  public void moveBall(double elapsedTime) {
    if (ballLaunched && !gamePaused) {
      myBall.moveBall(elapsedTime);
    }
  }

  public void resetGame() {
    setBallLaunched(false);
    myBall.reset();
    myPaddle.reset();
  }
  public void checkBallBlockCollision(Ball ball){
    for(Block block : blockList){
      if (ball.getBoundsInParent().intersects(block.getBoundsInParent())){
        ball.setDirection(ball.getDirectionX(), ball.getDirectionY() * -1);
        //this needs to be changed
//        root.getChildren().remove(block);
//        blockList.remove(block);
      }
    }
  }
  public void checkCollision(){
      checkBallBlockCollision(myBall);
      myBall.checkBallPaddleCollision(myPaddle);
  }
  public void checkBallDropsThroughBottom(){
    if (myBall.checkBallDropsThroughBottom()){
     resetGame();
    }
  }





    }

