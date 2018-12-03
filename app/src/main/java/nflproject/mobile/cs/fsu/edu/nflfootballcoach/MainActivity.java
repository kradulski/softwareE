package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.DatabaseHelper;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;


public class MainActivity extends AppCompatActivity {

    AppDatabase database = AppDatabase.getInstance(this);
    GamesDAO gamesDAO = database.getGamesDAO();
    TeamsDAO teamsDAO = database.getTeamsDAO();
    StateDAO stateDAO = database.getStateDAO();

    Random rand = new Random();  //random number generation needed in some places

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //populates database with initial data on first run of app
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            DatabaseHelper dh = new DatabaseHelper(this, database);
            dh.populateDatabase();
            dh.newGame();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }

        Button playGameButton = findViewById(R.id.play_game_button);
        playGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent;
                StateDAO stateDAO = database.getStateDAO();
                List<State> theState = stateDAO.getPlayerTeam();
                if (theState.get(0).getNewGame() == 0)
                {
                    DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    /* UPDATE MODE TO CHALLENGE */
                                    AppDatabase database = AppDatabase.getInstance(MainActivity.this);
                                    StateDAO stateDAO = database.getStateDAO();
                                    List<State> temp = stateDAO.getPlayerTeam();
                                    temp.get(0).setDifficulty("hard");
                                    stateDAO.update(temp.get(0));
                                    Intent myIntent = new Intent(MainActivity.this, TeamSelectActivity.class);
                                    startActivity(myIntent);
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    /* KEEP MODE AT NORMAL */
                                    Intent myIntent2 = new Intent(MainActivity.this, TeamSelectActivity.class);
                                    startActivity(myIntent2);
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Mode")
                            .setMessage("Would you like to play in Normal Mode or Challenge Mode? Games in Challenge Mode will be more difficult to win.")
                            .setPositiveButton("Challenge", dialogListener)
                            .setNegativeButton("Normal", dialogListener).show();
                }
                else {
                    myIntent = new Intent(MainActivity.this, PlayGameActivity.class);
                    startActivity(myIntent);
                }
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
                DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                StateDAO stateDAO = database.getStateDAO();
                                stateDAO.deleteAll();
                                stateDAO.insert(new State("", 0, 0, 0, 2018, 1, ""));
                                List<Team> everyTeam = teamsDAO.getEveryTeam();
                                for (int i = 0; i < everyTeam.size(); ++i)
                                {
                                    Team individual = everyTeam.get(i);
                                    individual.setWins(0);
                                    individual.setLosses(0);
                                    individual.setConLosses(0);
                                    individual.setConWins(0);
                                    teamsDAO.update(individual);
                                }
                                //populateTeams();
                                //populateGames();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Reset Game?")
                        .setMessage("Are you sure you want to reset all data? This cannot be undone.")
                        .setPositiveButton("Yes", dialogListener)
                        .setNegativeButton("No", dialogListener).show();
            }
        });

        this.setTitle("College Football Coach");
    }
}
