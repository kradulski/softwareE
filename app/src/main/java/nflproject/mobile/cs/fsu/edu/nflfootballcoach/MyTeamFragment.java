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
import android.widget.TextView;

import java.util.*;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class MyTeamFragment extends Fragment {
    ListView thePlayersList;
    TextView theTeamRankAndName;
    TextView theTeamConf;
    TextView teamWinLoss;
    TextView teamCWinLoss;
    TextView career;
    AppDatabase database = AppDatabase.getInstance(getActivity());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View showView = inflater.inflate(R.layout.fragment_my_team, container, false);
        thePlayersList = showView.findViewById(R.id.playerlist);
        theTeamRankAndName = showView.findViewById(R.id.teamNameRank);
        theTeamConf = showView.findViewById(R.id.myConf);
        teamWinLoss = showView.findViewById(R.id.winLoss);
        teamCWinLoss = showView.findViewById(R.id.cWinLoss);
        career = showView.findViewById(R.id.career_record);
        StateDAO stateDAO = database.getStateDAO();
        List<State> tempName = stateDAO.getPlayerTeam();
        TeamsDAO teamsDAO = database.getTeamsDAO();
        Team winLossStuff = teamsDAO.getTeamByName(tempName.get(0).getPlayerTeam());
        List<Team> tempRankList = teamsDAO.getRankings();
        int rankMinusOne = 0;
        while (rankMinusOne < tempRankList.size() &&
               !tempRankList.get(rankMinusOne).getName().equals(winLossStuff.getName())){ rankMinusOne++; }
        String tempRank = Integer.toString(rankMinusOne + 1);
        String teamRank = "#" + tempRank + " " + tempName.get(0).getPlayerTeam();
        String tempConf = "Conference: " + winLossStuff.getConference();
        String tempWL = "W/L: " + winLossStuff.getWins() + " - " + winLossStuff.getLosses();
        String tempCWL = "Conf W/L: " + winLossStuff.getConWins() + " - " + winLossStuff.getConLosses();
        String careerWL = "Career Record: " + Integer.toString(tempName.get(0).getCareerWins()) + " - " + Integer.toString(tempName.get(0).getCareerLosses());
        theTeamRankAndName.setText(teamRank);
        theTeamConf.setText(tempConf);
        teamWinLoss.setText(tempWL);
        teamCWinLoss.setText(tempCWL);
        career.setText(careerWL);
        PlayersDAO playersDAO = database.getPlayersDAO();
        List<Players> playerList = playersDAO.getPlayers();

        String[] thePlayers = new String[48];
        for (int i = 0; i < 48; ++i)
        {
            Players player = playerList.get(i);
            thePlayers[i] = player.getFirstName() + " " + player.getLastName() + ", " + player.getPosition() + ", " + player.getYear() + ", rating: " + player.getRating();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, thePlayers);
        thePlayersList.setAdapter(adapter);
        return showView;
    }
}
