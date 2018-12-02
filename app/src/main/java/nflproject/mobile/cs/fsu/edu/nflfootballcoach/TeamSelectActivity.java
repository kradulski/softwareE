package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;


public class TeamSelectActivity extends AppCompatActivity {

    ListView chooseTeam;
    AppDatabase database = AppDatabase.getInstance(this);
    public void createPlayers(Resources res, int teamRating){
        PlayersDAO playersDAO = database.getPlayersDAO();
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
        int newOffenseRating = 0;
        int newDefenseRating = 0;
        for (int playerID = 0; playerID < 48; ++playerID)
        {
            int playerFirstName = seed.nextInt(firstNames.length);
            theFirstName = firstNames[playerFirstName];
            int playerLastName = seed.nextInt(lastNames.length);
            theLastName = lastNames[playerLastName];
            int playerYear = seed.nextInt(years.length);
            theYear = years[playerYear];
            int rating = seed.nextInt(teamRating + 15 - (teamRating - 15) + 1) + teamRating - 15;
            if (rating >= 100)
                rating = 99;
            if (playerID < 10)
            {
                position = "OL";
                newOffenseRating += rating;
            }
            else if (playerID == 10 || playerID == 11)
            {
                newOffenseRating += rating;
                position = "QB";
            }
            else if (playerID == 12 || playerID == 13)
            {
                newOffenseRating += rating;
                position = "RB";
            }
            else if (playerID == 14 || playerID == 15)
            {
                newOffenseRating += rating;
                position = "TE";
            }
            else if (playerID < 22)
            {
                newOffenseRating += rating;
                position = "WR";
            }
            else if (playerID == 22 || playerID == 23)
            {
                newOffenseRating += rating;
                position = "K";
            }
            else if (playerID == 24 || playerID == 25)
            {
                newOffenseRating += rating;
                position = "P";
            }
            else if (playerID <= 33)
            {
                newDefenseRating += rating;
                position = "DL";
            }
            else if (playerID <= 39)
            {
                newDefenseRating += rating;
                position = "LB";
            }
            else if (playerID <= 43)
            {
                newDefenseRating += rating;
                position = "CB";
            }
            else {
                newDefenseRating += rating;
                position = "S";
            }

            playersDAO.insert(new Players(playerID, rating, theFirstName, theLastName, position, theYear));
        }
        newDefenseRating = newDefenseRating/22;
        newOffenseRating = newOffenseRating/26;
        TeamsDAO teamsDAO = database.getTeamsDAO();
        StateDAO stateDAO = database.getStateDAO();
        List<State> tempName = stateDAO.getPlayerTeam();
        Team yourTeam = teamsDAO.getTeamByName(tempName.get(0).getPlayerTeam());
        yourTeam.setDefRating(newDefenseRating);
        yourTeam.setOffRating(newOffenseRating);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);

        chooseTeam = findViewById(R.id.teamSelect);
        chooseTeam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedTeam = chooseTeam.getItemAtPosition(i).toString();
                String[] splitValues = selectedTeam.split("\\s+");
                Resources res = getResources();
                StateDAO stateDAO = database.getStateDAO();
                if (splitValues.length == 5)
                {
                    int rate = Integer.parseInt(splitValues[4]);
                    String temp = splitValues[1] + " " + splitValues[2];
                    stateDAO.deleteAll();
                    stateDAO.insert(new State(temp, 1));
                    createPlayers(res, rate);
                }
                else
                {
                    int rate = Integer.parseInt(splitValues[3]);
                    String temp = splitValues[1];
                    stateDAO.deleteAll();
                    stateDAO.insert(new State(temp, 1));
                    createPlayers(res, rate);
                }
                clickAction();
            }
        });
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void clickAction(){
        Intent myIntent = new Intent(TeamSelectActivity.this, PlayGameActivity.class);
        //MainActivity.setResetVal(false);
        startActivity(myIntent);
    }
}
