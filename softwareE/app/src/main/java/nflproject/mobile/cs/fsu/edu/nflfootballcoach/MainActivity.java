package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Example code for player name generation
        //Note getResources must be called after OnCreate or the program will crash
        //Random array index chosen from firstNames and lastNames string array resources
        //To do: Generate rest of player's stats
        Random seed = new Random();
        Resources res = getResources();
        String[] firstNames = res.getStringArray(R.array.firstNames);
        String[] lastNames = res.getStringArray(R.array.lastNames);
        int playerName = seed.nextInt(firstNames.length);
        int playerName2 = seed.nextInt(lastNames.length);
        Toast.makeText(this, firstNames[playerName] + " " + lastNames[playerName2],
                Toast.LENGTH_LONG).show();
        playerName = seed.nextInt(firstNames.length);
        playerName2 = seed.nextInt(lastNames.length);
        Toast.makeText(this, firstNames[playerName] + " " + lastNames[playerName2],
                Toast.LENGTH_LONG).show();

        Button playGameButton = findViewById(R.id.play_game_button);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, TeamSelectActivity.class);
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
            }
        });
    }
}
