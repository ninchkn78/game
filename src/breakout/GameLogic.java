package breakout;

import javafx.scene.input.KeyCode;

public class GameLogic {
  private boolean ballLaunched = false;
  private boolean gamePaused = false;

  private Ball myBall;
  private Paddle myPaddle;

  public GameLogic(LevelConfig currentConfig) {
    myBall = currentConfig.getMyBall();
    myPaddle = currentConfig.getMyPaddle();

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

  private void resetGame() {
    setBallLaunched(false);
    myBall.reset();
    myPaddle.reset();
  }
}
