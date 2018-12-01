package nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

//Defines SQL queries for Teams table. Add any custom queries for that table here

@Dao
public interface TeamsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Team... teams);

    @Delete
    void delete(Team team);

    @Update
    void update(Team... teams);

    //returns all teams in the database in a List
    @Query("SELECT * FROM teams")
    List<Team> getTeams();

    //returns singular team referred to by name
    @Query("SELECT * FROM teams WHERE name = :name")
    Team getTeamByName(String name);

    //removes all rows from teams table
    @Query("DELETE FROM teams")
    void deleteAllTeams();

    //gets the names of all teams, returns an List
    @Query("SELECT name FROM teams")
    List<String> getTeamNames();

    //get all teams from a particular conference
    @Query("SELECT * FROM teams WHERE conference = :conference")
    List<Team> getTeamsFromConference(String conference);

    //get top 25 rankings:
    @Query("SELECT * FROM teams ORDER BY rankingVotes DESC LIMIT 25")
    List<Team> getRankings();

    //get teams by division
    @Query("SELECT * FROM teams WHERE division = :division")
    List<Team> getTeamsByDivision(String division);

}
