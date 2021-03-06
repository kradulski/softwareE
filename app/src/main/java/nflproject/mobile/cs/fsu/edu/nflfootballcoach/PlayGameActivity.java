package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class PlayGameActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {
    AppDatabase database = AppDatabase.getInstance(this);
    TeamsDAO teamsDAO = database.getTeamsDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        loadFragment(new ScheduleFragment());

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.navigation_schedule:
                fragment = new ScheduleFragment();
                break;
            case R.id.navigation_rankings:
                fragment = new RankingsFragment();
                break;
            case R.id.navigation_my_team:
                fragment = new MyTeamFragment();
                break;
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch(item.getItemId()) {
            case R.id.how_to_option:
                Intent myIntent = new Intent(PlayGameActivity.this, HowToPlayActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.reset_option:
                DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent myIntent = new Intent(PlayGameActivity.this, MainActivity.class);
                                StateDAO stateDAO = database.getStateDAO();
                                stateDAO.deleteAll();
                                stateDAO.insert(new State("", 0, 0, 0, 2018, 1,""));
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
                                startActivity(myIntent);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.dismiss();
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(PlayGameActivity.this);
                builder.setTitle("Reset Game?")
                        .setMessage("Are you sure you want to reset all data? This cannot be undone.")
                        .setPositiveButton("Yes", dialogListener)
                        .setNegativeButton("No", dialogListener).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
