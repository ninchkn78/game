package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

/**
 * This class handles displaying important game information, including the current score, high score,  lives, and level.
 * @author christian welch
 */
public class Display {

  private int currentLives = 3;
  private int currentScore = 0;
  private int currentLevel;
  private Text stats;

  /**
   * Creates a JavaFX Text object and populates it with the current level, lives, and score.
   * @param levelNum
   * @return JavaFX Text object
   */
  public Text createDisplay(int levelNum) {
    this.currentLevel = levelNum;
    stats = new Text(350 / 2 - 50, 350 - 20,
        "Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
    stats.setId("stats");
    return stats;

  }

  /**
   * Takes the given score and updates the Text object to display it
   * @param score
   * @param root
   */
  public void changeScore(int score, Group root) {
    this.currentScore += score;
    for(Node node: root.getChildren()){
      if (node.getId().equals("stats")){
        stats = (Text) node;
        stats.setText("Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
      }
    }

  }

  /**
   * Takes the given number of lives and updates the Text object to display it
   * @param lives
   * @param root
   */
  public void changeLives(int lives, Group root) {

    this.currentLives -= lives;
    for(Node node: root.getChildren()){
      if (node.getId().equals("stats")){
        stats = (Text) node;
        stats.setText("Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
      }
    }

  }

  /**
   * Takes in the given level number and updates the Text object to display it
   * @param level
   * @param root
   */
  public void changeLevel(int level, Group root) {


    this.currentLevel = level;
    // for some reason .remove(stats) does not work here so I hardcoded it
    root.getChildren()
            .remove(root.getChildren().size() - 1); // assumes Text is always last in root???
    Text newStats = createDisplay(level);
    root.getChildren().add(newStats);

  }

  /**
   * Returns current number of lives
   * @return integer
   */
  public int getLives() {
    return currentLives;
  }
  /**
   * Returns current score
   * @return integer
   */
  public int getScore(){
    return currentScore;
  }


}
