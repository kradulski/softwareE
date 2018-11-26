package nflproject.mobile.cs.fsu.edu.nflfootballcoach.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.support.annotation.NonNull;

//Schema for games table

@Entity(tableName = "games", primaryKeys = {"home", "away", "week"}, foreignKeys = {
        @ForeignKey(entity = Team.class, parentColumns = "name", childColumns = "home", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Team.class, parentColumns = "name", childColumns = "away", onDelete = ForeignKey.CASCADE)
})
public class Game {

    @ColumnInfo
    @NonNull
    private String home;

    @ColumnInfo
    @NonNull
    private String away;

    @ColumnInfo
    private int week;

    @ColumnInfo
    private int homeScore;

    @ColumnInfo
    private int awayScore;

    public Game(String home, String away, int week, int homeScore, int awayScore)
    {
        this.home = home;
        this.away = away;
        this.week = week;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHome() {return home;}
    public void setHome(String home) {this.home = home;}

    public String getAway() {return away;}
    public void setAway(String away) {this.away = away;}

    public int getWeek() {return week;}
    public void setWeek(int week) {this.week = week;}

    public int getHomeScore() {return homeScore;}
    public void setHomeScore(int homeScore) {this.homeScore = homeScore;}

    public int getAwayScore() {return awayScore;}
    public void setAwayScore(int awayScore) {this.awayScore = awayScore;}

}
