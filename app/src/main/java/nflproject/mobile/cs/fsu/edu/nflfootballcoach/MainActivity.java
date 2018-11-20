package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
}
