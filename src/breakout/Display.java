package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.text.Text;

public class Display {

  private int currentLives = 3;
  private int currentScore = 0;
  private int currentLevel;
  private Text stats;

  public Text createDisplay(int levelNum) {
    this.currentLevel = levelNum;
    stats = new Text(350 / 2 - 50, 350 - 20,
        "Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
    stats.setId("stats");
    return stats;

  }

  public void changeScore(int score, Group root) {
    this.currentScore += score;
    for(Node node: root.getChildren()){
      if (node.getId().equals("stats")){
        stats = (Text) node;
        stats.setText("Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
      }
    }
    /*
    root.getChildren().remove(stats);
    this.currentScore += score;

    Text newStats = createDisplay(currentLevel);
    root.getChildren().add(newStats);

     */
  }

  // TODO: 2020-09-26 change this method to generally account for adding or decreasing lives
  public void changeLives(int lives, Group root) {

    this.currentLives -= lives;
    for(Node node: root.getChildren()){
      if (node.getId().equals("stats")){
        stats = (Text) node;
        stats.setText("Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
      }
    }

    /*
    root.getChildren().remove(stats);
    this.currentLives -= lives;
    Text newStats = createDisplay(currentLevel);
    root.getChildren().add(newStats);

     */
  }

  public void changeLevel(int level, Group root) {


    this.currentLevel = level;
    // for some reason .remove(stats) does not work here so I hardcoded it
    root.getChildren()
            .remove(root.getChildren().size() - 1); // assumes Text is always last in root???
    Text newStats = createDisplay(level);
    root.getChildren().add(newStats);
    //stats.setText("Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);



  }

  /* might need this later? not sure
  public void resetScore(Group root){
      root.getChildren().remove(stats);
      this.currentScore = 0;
      Text newStats = createDisplay();
      root.getChildren().add(newStats);

  }

   */
  public int getLives() {
    return currentLives;
  }


}
