package nflproject.mobile.cs.fsu.edu.nflfootballcoach.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//Stores any variables that must be persistent across a game

@Entity(tableName = "State")
public class State {

    @PrimaryKey
    @NonNull
    private String playerTeam;

    @ColumnInfo
    private int newGame;

    @ColumnInfo
    private int careerWins;

    @ColumnInfo
    private int careerLosses;

    @ColumnInfo
    private int year;

    @ColumnInfo
    private int week;

    @ColumnInfo
    private String difficulty;

    public State(String playerTeam, int newGame, int careerLosses, int careerWins, int year, int week, String difficulty)
    {
        this.difficulty = difficulty;
        this.playerTeam = playerTeam;
        this.newGame = newGame;
        this.careerWins = careerWins;
        this.careerLosses = careerLosses;
        this.year = year;
        this.week = week;
    }

    public String getDifficulty() {return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public int getWeek() {return week; }
    public void setWeek(int week) { this.week = week; }

    public String getPlayerTeam() {return playerTeam; }
    public void setPlayerTeam(String playerTeam) { this.playerTeam = playerTeam; }

    public  int getYear() {return year;}
    public  void setYear(int year) {this.year = year;}

    public  int getNewGame() {return newGame;}
    public  void setNewGame(int newGame) {this.newGame = newGame;}

    public  int getCareerWins() {return careerWins;}
    public  void setCareerWins(int careerWins) {this.careerWins = careerWins;}

    public  int getCareerLosses() {return careerLosses;}
    public  void setCareerLosses(int careerLosses) {this.careerLosses = careerLosses;}
}
