package kodilla;

import java.io.Serializable;

public class Player implements Serializable {
    private int points = 0;
    private String name;

    public Player() {

    }

    public Player(int points, String name) {
        this.points = points;
        this.name = name;
    }

    public int addPoints() {
        points++;
        return points;
    }

    public String getPoints() {
        return "" + points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name +
                " points: " + points;
    }
}
