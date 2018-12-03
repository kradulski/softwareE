package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;

@Dao
public interface StateDAO {

    @Insert
    public void insert(State state);

    @Delete
    public void delete(State state);

    @Update
    public void update(State state);

    //get playerTeam
    @Query("SELECT * FROM State")
    public List<State> getPlayerTeam();

    @Query("SELECT playerTeam FROM State")
    public String getPlayerTeamString();

    @Query("SELECT difficulty FROM State")
    public String getDifficulty();

    //removes all rows from teams table
    @Query("DELETE FROM State")
    void deleteAll();

    @Query("SELECT week FROM State")
    public int getWeek();

    //delete state
    @Query("DELETE FROM State")
    public void deleteGame();

    @Query("SELECT * FROM state")
    public State getState();

}
