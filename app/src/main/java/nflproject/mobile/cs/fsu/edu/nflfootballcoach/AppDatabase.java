package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

//Database creation

@Database(entities = {Team.class, Game.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TeamsDAO getTeamsDAO();

    public abstract GamesDAO getGamesDAO();

}
