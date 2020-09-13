package breakout;

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

public class Paddle extends Rectangle {
  public static final int MOVER_SPEED = 5;
  public Paddle(int row, int col, int width, int height){
    super(row,col,width,height);
  }
  public void movePaddle (KeyCode code) {
    switch (code) {
      case LEFT -> this.setX(this.getX() - MOVER_SPEED);
      case RIGHT -> this.setX(this.getX() + MOVER_SPEED);
    }
}}

