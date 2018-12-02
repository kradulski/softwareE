package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.GamesDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class ScheduleFragment extends Fragment {

    AppDatabase database = AppDatabase.getInstance(getActivity());
    GamesDAO gamesDAO = database.getGamesDAO();
    StateDAO stateDAO = database.getStateDAO();

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

        return view;
    }
}
