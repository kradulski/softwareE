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

    public State(String playerTeam, int newGame, int careerLosses, int careerWins, int year)
    {
        this.playerTeam = playerTeam;
        this.newGame = newGame;
        this.careerWins = careerWins;
        this.careerLosses = careerLosses;
        this.year = year;
    }

    public String getPlayerTeam() {return playerTeam; }
    public void setPlayerTeam(String playerTeam) { this.playerTeam = playerTeam; }

    public  int getYear() {return year;}
    public  void setYear() {this.year = year;}

    public  int getNewGame() {return newGame;}
    public  void setNewGame() {this.newGame = newGame;}

    public  int getCareerWins() {return careerWins;}
    public  void setCareerWins() {this.careerWins = careerWins;}

    public  int getCareerLosses() {return careerLosses;}
    public  void setCareerLosses() {this.careerLosses = careerLosses;}
}
