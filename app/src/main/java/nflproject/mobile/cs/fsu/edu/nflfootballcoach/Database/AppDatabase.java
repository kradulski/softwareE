package nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

//Database creation


@Database(entities = {Team.class, Game.class, State.class, Players.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TeamsDAO getTeamsDAO();

    public abstract GamesDAO getGamesDAO();

    public abstract StateDAO getStateDAO();

    public abstract PlayersDAO getPlayersDAO();

    //the database instance
    public static AppDatabase instance;

    //get the database instance
    public static AppDatabase getInstance(Context context)
    {
        if(instance == null)
        {
            instance = Room.databaseBuilder(context, AppDatabase.class, "cfbdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

}
