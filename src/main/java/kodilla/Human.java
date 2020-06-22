package kodilla;

public class Human  implements Player{
    private int points = 0;

    public int addPoints() {
        points++;
        return points;
    }

    public String getPoints() {
        return "" + points;
    }

}
