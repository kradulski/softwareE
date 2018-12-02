package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class ScheduleFragment extends Fragment {

    AppDatabase database = AppDatabase.getInstance(getActivity());
    GamesDAO gamesDAO = database.getGamesDAO();
    StateDAO stateDAO = database.getStateDAO();

    //Write this and pass res to the function: Resources res = getResources();
    void leavingPlayers(View testView, Resources res)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Players Graduating");

        PlayersDAO playersDAO = database.getPlayersDAO();
        List<Players> allPlayers = playersDAO.getPlayers();
        Vector<Integer> ids = new Vector<>();
        int counter = 0;
        Random seed = new Random();
        String[] firstNames = res.getStringArray(R.array.firstNames);
        String[] lastNames = res.getStringArray(R.array.lastNames);
        Vector<String> firstname = new Vector<>();
        Vector<String> position = new Vector<>();
        Vector<String> lastname = new Vector<>();

        final ArrayAdapter<String> newPlayers = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        final ArrayAdapter<String> graduates = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);
        for (int i = 0; i < 48; ++i)
        {
            if (allPlayers.get(i).getYear().equals("SR"))
            {
                Players player = allPlayers.get(i);
                graduates.add(player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition() + ", " + player.getYear() + ", rating: " + player.getRating());
                ids.add(i);
                ++counter;
                position.add(player.getPosition());
                int playerFirstName = seed.nextInt(firstNames.length);
                firstname.add(firstNames[playerFirstName]);
                int playerLastName = seed.nextInt(lastNames.length);
                lastname.add(lastNames[playerLastName]);
                playersDAO.deletePlayer(i);
            }
        }

        List<Players> remainingPlayers = playersDAO.getPlayers();
        final ArrayAdapter<String> playersStaying = new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_singlechoice);

        for (int i = 0; i < 48 - counter; ++i)
        {
            Players player = allPlayers.get(i);
            int skillIncrease = seed.nextInt(4) + 1;
            if (player.getYear().equals("FR"))
            {
                player.setYear("SO");
            }
            else if (allPlayers.get(i).equals("SO"))
            {
                player.setYear("JR");
            }
            else
            {
                player.setYear("SR");
            }
            int temp = player.getRating() + skillIncrease;
            if (temp >= 100)
                temp = 99;
            player.setRating(temp);
            playersDAO.update(player);
            playersStaying.add(player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition() + ", " + player.getYear() + ", rating: " + player.getRating() + " (+" + skillIncrease + ")");
        }

        for (int i = 0; i < firstname.size(); ++i)
        {
            playersDAO.insert(new Players(ids.get(i), 80, firstname.get(i), lastname.get(i), position.get(i), "FR"));
            newPlayers.add(firstname.get(i) + " " + lastname.get(i) + ", " + position.get(i) + ", FR, rating: " + "80");
        }


        String[] why = new String[graduates.getCount()];
        final String[] why2 = new String[graduates.getCount()];
        final String[] why3 = new String[playersStaying.getCount()];
        for (int i = 0; i < graduates.getCount(); ++i)
        {
            why[i] = graduates.getItem(i);
            why2[i] = newPlayers.getItem(i);
            why3[i] = playersStaying.getItem(i);
        }
        builder.setItems(why, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                AlertDialog.Builder builderInner = new AlertDialog.Builder(getActivity());
                builderInner.setTitle("New Players");
                builderInner.setItems(why2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builderInner2 = new AlertDialog.Builder(getActivity());
                        builderInner2.setTitle("Player Improvements");
                        builderInner2.setItems(why3, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        builderInner2.show();
                    }
                });
                builderInner.show();
            }
        });
        builder.show();
    }

    String playerTeam;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        ListView schedule = (ListView) view.findViewById(R.id.scheduleList);

        playerTeam = stateDAO.getPlayerTeamString();               //get team player is currently playing as
        List<Game> gameList = gamesDAO.getGamesOfTeam(playerTeam); //get games of team player is playing as

        //populate schedule rows
        ArrayList<scheduleRow> scheduleArr = new ArrayList<scheduleRow>(gameList.size());
        for(int i = 0; i<gameList.size(); i++)
        {
            scheduleRow entry = new scheduleRow("","");

            boolean home;
            if (playerTeam.equals(gameList.get(i).getHome())) {
                entry.setOpponent("vs " + database.getTeamsDAO().getTeamByName(gameList.get(i).getAway()).getAbbreviation());
                home = true;
            }
            else {
                entry.setOpponent("at " + database.getTeamsDAO().getTeamByName(gameList.get(i).getHome()).getAbbreviation());
                home = false;
            }

            if (gameList.get(i).getHomeScore() > gameList.get(i).getAwayScore() && home) {
                entry.setResult("W " + gameList.get(i).getHomeScore() + "-" + gameList.get(i).getAwayScore());
            }
            else if(gameList.get(i).getHomeScore() > gameList.get(i).getAwayScore() && !home) {
                entry.setResult("L " + gameList.get(i).getHomeScore() + "-" + gameList.get(i).getAwayScore());
            }
            else
                entry.setResult("");

            scheduleArr.add(entry);
        }

        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), R.layout.schedule_adapter_view_layout, scheduleArr);
        schedule.setAdapter(adapter);

        Resources res = getResources();
        leavingPlayers(view, res);


        return view;
    }
}
