package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Random;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;


public class MainActivity extends AppCompatActivity {

    AppDatabase database = AppDatabase.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populates database with initial data on first run of app
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            populateDatabase();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }

        Button playGameButton = findViewById(R.id.play_game_button);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent;
                if (resetVal)
                    myIntent = new Intent(MainActivity.this, TeamSelectActivity.class);
                else
                    myIntent = new Intent(MainActivity.this, PlayGameActivity.class);

                startActivity(myIntent);
            }
        });

        Button howToPlayButton = findViewById(R.id.how_to_button);
        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, HowToPlayActivity.class);
                startActivity(myIntent);
            }
        });

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Code for resetting database and resetting user information goes here */
                setResetVal(true);
            }
        });
    }

    private static boolean resetVal = true;
    public static boolean getResetVal(){
        return resetVal;
    }
    public static void setResetVal(boolean newVal){
        resetVal = newVal;
    }

    public void createPlayers(Resources res, int teamRating){
        //Note getResources must be called after OnCreate or the program will crash
        //Write this and pass res to the function: Resources res = getResources();
        //Random array index chosen from firstNames and lastNames string array resources
        //To do: Generate rest of player's stats
        Random seed = new Random();
        //intention is to give each player unique ID
        //48 players so from 0-47
        //when someone graduates give new player the ID of player who graduated
        String[] firstNames = res.getStringArray(R.array.firstNames);
        String[] lastNames = res.getStringArray(R.array.lastNames);
        String[] years = {"FR", "SO", "JR", "SR"};
        //firstNames[playerName] + lastNames[playerName2]
        //Positions to be assigned on offense: 2 QB, 2 RB, 10 OL, 2 TE, 6 WR, 2 K, 2 P
        //Positions to be assigned on defense: 8 DL, 6 LB, 4 CB, 4 S
        String theFirstName, theLastName, theYear, position;
        for (int playerID = 0; playerID < 48; ++playerID)
        {
            int playerFirstName = seed.nextInt(firstNames.length);
            theFirstName = firstNames[playerFirstName];
            int playerLastName = seed.nextInt(lastNames.length);
            theLastName = lastNames[playerLastName];
            int playerYear = seed.nextInt(years.length);
            theYear = years[playerYear];
            if (playerID < 10)
                position = "OL";
            else if (playerID == 10 || playerID == 11)
                position = "QB";
            else if (playerID == 12 || playerID == 13)
                position = "RB";
            else if (playerID == 14 || playerID == 15)
                position = "TE";
            else if (playerID < 22)
                position = "WR";
            else if (playerID == 22 || playerID == 23)
                position = "K";
            else if (playerID == 24 || playerID == 25)
                position = "P";
            else if (playerID <= 33)
                position = "DL";
            else if (playerID <= 39)
                position = "LB";
            else if (playerID <= 43)
                position = "CB";
            else
                position = "S";


            //db.execSQL("INSERT INTO Players (id, rating, firstName, lastName, position, year) VALUES
        }
    }

    //insert all data into database
    public void populateDatabase()
    {
        populateTeams();
        populateGames();
    }

    //insert teams into database
    public void populateTeams()
    {
        TeamsDAO teamsDAO = database.getTeamsDAO();
        teamsDAO.deleteAllTeams();
        teamsDAO.insert(new Team("Boston College", "ACC", "Atlantic", 0, 0,
                0, 0, 76, 85, 38));
        teamsDAO.insert(new Team("Clemson", "ACC", "Atlantic", 0,0,
                0, 0, 81, 95, 1476));
        teamsDAO.insert(new Team("Florida State", "ACC", "Atlantic", 0,0,
                0,0,75,85,450));
        teamsDAO.insert(new Team("Louisville", "ACC", "Atlantic", 0,0,
                0, 0, 87,79,0));
        teamsDAO.insert(new Team("NC State", "ACC", "Atlantic", 0,0,
                0,0, 83,81,60));
        teamsDAO.insert(new Team("Syracuse", "ACC", "Atlantic", 0,0,
                0,0,77,77,0));
        teamsDAO.insert(new Team("Duke", "ACC", "Coastal", 0,0,
                0,0, 76,84,0));
        teamsDAO.insert(new Team("Georgia Tech", "ACC", "Coastal", 0,0,
                0,0,82,81,0));
        teamsDAO.insert(new Team("North Carolina", "ACC", "Coastal", 0,0,
                0,0, 73,78,0));
        teamsDAO.insert(new Team("Pittsburgh", "ACC", "Coastal", 0,0,
                0,0,75,80,0));
        teamsDAO.insert(new Team("Miami", "ACC", "Coastal", 0,0,
                0,0,79,87,1027));
        teamsDAO.insert(new Team("Virginia", "ACC", "Coastal", 0,0,
                0,0, 73,84, 0));
        teamsDAO.insert(new Team("Virginia Tech", "ACC", "Coastal", 0,0,
                0,0, 75,90,384));
        teamsDAO.insert(new Team("Wake Forest", "ACC", "Atlantic", 0,0,
                0,0, 75,75,0));
        /*
        teamsDAO.insert(new Team("Baylor", "Big 12", "", 0,0,
                0,0,72,75,0));
        teamsDAO.insert(new Team("Iowa State", "Big 12", "", 0,0,
                0,0,80,85,21));
        teamsDAO.insert(new Team("Kansas", "Big 12", "", 0,0,
                0,0,70,76,0));
        teamsDAO.insert(new Team("Kansas State", "Big 12", "", 0,0,
                0,0,76,79,22));
        teamsDAO.insert(new Team("Oklahoma", "Big 12", "", 0,0,
                0,0,95,76, 1173));
        teamsDAO.insert(new Team("Oklahoma State", "Big 12", "", 0,0,
                0,0,91,80,96));
        teamsDAO.insert(new Team("TCU", "Big 12", "", 0,0,
                0,0,78,88,543));
        teamsDAO.insert(new Team("Texas", "Big 12", "", 0,0,
                0,0,70,87,351));
        teamsDAO.insert(new Team("Texas Tech", "Big 12", "", 0,0,
                0,0,80,78,0));
        teamsDAO.insert(new Team("West Virginia", "Big 12", "", 0,0,
                0,0,79,77,533));

        teamsDAO.insert(new Team("Indiana", "Big Ten", "East", 0,0,
                0,0,75,86,0));
        teamsDAO.insert(new Team("Maryland", "Big Ten", "East", 0,0,
                0,0,78,78,0));
        teamsDAO.insert(new Team("Michigan", "Big Ten", "East", 0,0,
                0,0,74,90,778));
        teamsDAO.insert(new Team("Michigan State", "Big Ten", "East", 0,0,
                0,0,77,91,877));
        teamsDAO.insert(new Team("Ohio State", "Big Ten", "East", 0,0,
                0,0,89,90,1256));
        teamsDAO.insert(new Team("Penn State", "Big Ten", "East", 0,0,
                0,0,88,88,1012));
        teamsDAO.insert(new Team("Rutgers", "Big Ten", "East", 0,0,
                0,0,70,82,0));
        teamsDAO.insert(new Team("Illinois", "Big Ten", "West", 0,0,
                0,0,67,78,0));
        teamsDAO.insert(new Team("Iowa", "Big Ten", "West", 0,0,
                0,0,79,88,18));
        teamsDAO.insert(new Team("Minnesota", "Big Ten", "West", 0,0,
                0,0,75,84,0));
        teamsDAO.insert(new Team("Nebraska", "Big Ten", "West", 0,0,
                0,0,79,75,0));
        teamsDAO.insert(new Team("Northwestern", "Big Ten", "West", 0,0,
                0,0,76,87,28));
        teamsDAO.insert(new Team("Purdue", "Big Ten", "West", 0,0,
                0,0, 76,85,0));
        teamsDAO.insert(new Team("Wisconsin", "Big Ten", "West", 0,0,
                0,0,83,94,1271));

        teamsDAO.insert(new Team("California", "Pac-12", "North", 0,0,
        0,0,74,79,0));
        teamsDAO.insert(new Team("Oregon", "Pac-12", "North", 0,0,
                0,0,76,81,312));
        teamsDAO.insert(new Team("Oregon State", "Pac-12", "North", 0,0,
                0,0,71,69,0));
        teamsDAO.insert(new Team("Stanford", "Pac-12", "North", 0,0,
                0,0,83,81,804));
        teamsDAO.insert(new Team("Washington", "Pac-12", "North", 0,0,
                0,0,85,91,1215));
        teamsDAO.insert(new Team("Washington State", "Pac-12", "North", 0,0,
                0,0,75,85,0));
        teamsDAO.insert(new Team("Arizona", "Pac-12", "South", 0,0,
                0,0,86,75,68));
        teamsDAO.insert(new Team("Arizona State", "Pac-12", "South", 0,0,
                0,0,79,76,0));
        teamsDAO.insert(new Team("Colorado", "Pac-12", "South", 0,0,
                0,0,77,77,0));
        teamsDAO.insert(new Team("UCLA", "Pac-12", "South", 0,0,
                0,0,81,73,0));
        teamsDAO.insert(new Team("USC", "Pac-12", "South", 0,0,
                0,0,83,82,773));
        teamsDAO.insert(new Team("Utah", "Pac-12", "South", 0,0,
                0,0, 74,85,106));

        teamsDAO.insert(new Team("Florida", "SEC", "East",0,0,
                0,0,72,82,148));
        teamsDAO.insert(new Team("Georgia", "SEC", "East", 0,0,
                0,0,87,89,1350));
        teamsDAO.insert(new Team("Kentucky", "SEC", "East", 0,0,
                0,0,76,77,13));
        teamsDAO.insert(new Team("Missouri", "SEC", "East", 0,0,
                0,0,83,78,0));
        teamsDAO.insert(new Team("South Carolina", "SEC", "East", 0,0,
                0,0,75,85,216));
        teamsDAO.insert(new Team("Tennessee", "SEC", "East", 0,0,
                0,0,71,80,0));
        teamsDAO.insert(new Team("Vanderbilt", "SEC", "East", 0,0,
                0,0,79,81,0));
        teamsDAO.insert(new Team("Alabama", "SEC", "West", 0,0,
                0,0,85,95,1505));
        teamsDAO.insert(new Team("Arkansas", "SEC", "West", 0,0,
                0,0,80,75,0));
        teamsDAO.insert(new Team("Auburn", "SEC", "West",0,0,
                0,0,83,91,1013));
        teamsDAO.insert(new Team("LSU", "SEC", "West", 0,0,
                0,0,81,87,292));
        teamsDAO.insert(new Team("Ole Miss", "SEC", "West", 0,0,
                0,0,79,75,0));
        teamsDAO.insert(new Team("Mississippi State", "SEC", "West", 0,0,
                0,0,79,87,511));
        teamsDAO.insert(new Team("Texas A&M", "SEC", "West", 0,0,
                0,0,76,80,51));
        */
    }

    //insert games into database
    public void populateGames()
    {
        GamesDAO gamesDAO = database.getGamesDAO();
    }

}
