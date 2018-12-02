package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

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
    @Query("SELECT * FROM games WHERE home = :name OR away = :name ORDER BY week ASC")
    public List<Game> getGamesOfTeam(String name);

    //get all games played in a week
    @Query("SELECT * FROM games WHERE week = :week")
    public List<Game> getGamesOfWeek(int week);

    //checks if game is already in database
    @Query("SELECT * FROM games WHERE home = :home AND away = :away")
    public Game getGame(String home,String away);

}
