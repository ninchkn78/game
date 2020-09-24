package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class Display {

private int currentLives = 3;
private int currentScore = 0;
private int currentLevel = 0;
private Text stats;

    public Display(){
    }
    
    public Text createDisplay(){

       stats = new Text(350 / 2 - 50, 350 - 20, "Level: " + currentLevel + "     Lives: " + currentLives + "     Score: " + currentScore);
       stats.setId("stats");
       return stats;

    }
    public void incrementScore(int score, Group root){
        root.getChildren().remove(stats);
        this.currentScore += score;

        Text newStats = createDisplay();
        root.getChildren().add(newStats);
    }
    public void decrementLives(int lives, Group root){
        root.getChildren().remove(stats);
        this.currentLives -= lives;
        Text newStats = createDisplay();
        root.getChildren().add(newStats);
    }
    public void changeLevel(int level, Group root){
        this.currentLevel = level;
        // for some reason .remove(stats) does not work here so I hardcoded it
        root.getChildren().remove(root.getChildren().size() - 1); // assumes Text is always last in root???
        Text newStats = createDisplay();
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
    public int getLives(){
        return currentLives;
    }


}
