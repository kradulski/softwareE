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

    public State(String playerTeam, int newGame)
    {
        this.playerTeam = playerTeam;
        this.newGame = newGame;
    }

    public String getPlayerTeam() {return playerTeam; }
    public void setPlayerTeam(String playerTeam) { this.playerTeam = playerTeam; }

    public  int getNewGame() {return newGame;}
    public  void setNewGame() {this.newGame = newGame;}

}
