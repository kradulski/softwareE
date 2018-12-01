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

        playerTeam = stateDAO.getPlayerTeamString();  //get team player is currently playing as
        List<Game> gameList = gamesDAO.getGamesOfTeam(playerTeam); //get games of team player is playing as
        ArrayList<Game> games = new ArrayList<Game>(gameList.size());
        games.addAll(gameList);

        ScheduleListAdapter adapter = new ScheduleListAdapter(getActivity(), R.layout.schedule_adapter_view_layout, games);
        schedule.setAdapter(adapter);

        return view;
    }
}
