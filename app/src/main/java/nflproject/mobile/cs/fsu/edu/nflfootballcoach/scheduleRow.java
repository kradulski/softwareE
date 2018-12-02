package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

//Class to make it easier to create the schedule

public class scheduleRow {

    private String opponent;
    private String result;

    public scheduleRow(String opponent, String result) {
        this.opponent = opponent;
        this.result = result;
    }

    public String getOpponent() {
        return opponent;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
