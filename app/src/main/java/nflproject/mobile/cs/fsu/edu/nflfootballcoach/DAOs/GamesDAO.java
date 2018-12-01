package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;

//Defines SQL queries for games table. Add any custom queries here

@Dao
public interface GamesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Game... games);

    @Delete
    public void delete(Game game);

    @Update
    public void update(Game... games);

    //get all games played by a team
    @Query("SELECT * FROM games WHERE home = :name OR away = :name")
    public List<Game> getGamesOfTeam(String name);

}