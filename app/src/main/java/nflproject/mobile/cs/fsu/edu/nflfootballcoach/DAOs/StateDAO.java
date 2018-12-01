package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
    @Query("SELECT playerTeam FROM State")
    public String getPlayerTeam();

    //delete state
    @Query("DELETE FROM State")
    public void deleteGame();

}
