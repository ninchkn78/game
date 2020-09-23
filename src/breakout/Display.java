package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class Display {

private int currentLives = 3;
private int currentScore = 0;
private Text stats;

    public Display(){
    }
    
    public Text createDisplay(){

       stats = new Text(350 / 2 - 50, 350 - 20, "Lives: " + currentLives + "     Score: " + currentScore);
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
