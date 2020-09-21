package breakout;

import javafx.scene.Group;
import javafx.scene.text.Text;



public class Display {

private int currentLives;
private int currentScore;
private Text stats;

    public Display(){



    }


    public Text createDisplay(){
       stats = new Text(350 / 2 - 50, 350 - 20, "Lives: " + currentLives + "     Score: " + currentScore);
       stats.setId("stats");
       return stats;

    }
    public void updateScore(int score, Group root){
        root.getChildren().remove(stats);
        this.currentScore = score;
        Text newStats = createDisplay();
        root.getChildren().add(newStats);
    }




}
