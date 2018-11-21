package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Teams;

//Database creation

@Database(entities = {Teams.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract TeamsDAO getTeamsDAO();

}
