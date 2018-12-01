package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;

public class TeamSelectActivity extends AppCompatActivity {

    AppDatabase database = AppDatabase.getInstance(this);
    StateDAO stateDAO = database.getStateDAO();

    ListView chooseTeam;
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
        MainActivity.setResetVal(false);
        startActivity(myIntent);
    }
}
