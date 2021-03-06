package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class RankingsFragment extends Fragment {
    AppDatabase database = AppDatabase.getInstance(getActivity());
    ListView theRankingsListView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rankings, null);
        theRankingsListView = view.findViewById(R.id.list_rankings);
        TeamsDAO teamsDAO = database.getTeamsDAO();
        List<Team> theRankingsList = teamsDAO.getRankings();
        String[] theRankings = new String[25];

        for (int i = 0; i < 25; ++i)
        {
            Team team = theRankingsList.get(i);
            theRankings[i] = "#" + (i + 1) + " " + team.getName() + ", " + team.getWins() + "-" + team.getLosses() + ", " + team.getRankingVotes();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, theRankings);
        theRankingsListView.setAdapter(adapter);

        return view;
    }
}
