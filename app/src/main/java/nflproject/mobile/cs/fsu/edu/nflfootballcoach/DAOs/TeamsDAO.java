package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Query;
import java.util.List;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

//Defines SQL queries for Teams table. Add any custom queries for that table here

@Dao
public interface TeamsDAO {

    @Insert
    public void insert(Team... teams);

    @Delete
    public void delete(Team team);

    @Update
    public void update(Team... teams);

    //returns all teams in the database in a List
    @Query("SELECT * FROM teams")
    public List<Team> getTeams();

    //returns singular team referred to by name
    @Query("SELECT * FROM teams WHERE name = :name")
    public Team getTeamByName(String name);

}
