package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;

@Dao
public interface PlayersDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Players... players);

    @Delete
    void delete(Players players);

    @Update
    void update(Players... players);

    //returns all teams in the database in a List
    @Query("SELECT * FROM players")
    List<Players> getPlayers();
}
