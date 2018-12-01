package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.PlayersDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.TeamsDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Players;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.State;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Team;

public class MyTeamFragment extends Fragment {
    ListView thePlayersList;
    TextView theTeamName;
    TextView teamWinLoss;
    TextView teamCWinLoss;
    AppDatabase database = AppDatabase.getInstance(getActivity());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View showView = inflater.inflate(R.layout.fragment_my_team, container, false);
        thePlayersList = showView.findViewById(R.id.playerlist);
        theTeamName = showView.findViewById(R.id.teamName);
        teamWinLoss = showView.findViewById(R.id.winLoss);
        teamCWinLoss = showView.findViewById(R.id.cWinLoss);
        StateDAO stateDAO = database.getStateDAO();
        List<State> tempName = stateDAO.getPlayerTeam();
        theTeamName.setText(tempName.get(0).getPlayerTeam());
        TeamsDAO teamsDAO = database.getTeamsDAO();
        Team winLossStuff = teamsDAO.getTeamByName(tempName.get(0).getPlayerTeam());
        String tempWL = "W/L " + winLossStuff.getWins() + " - " + winLossStuff.getLosses();
        String tempCWL = "Conf W/L " + winLossStuff.getConWins() + " - " + winLossStuff.getConLosses();
        teamWinLoss.setText(tempWL);
        teamCWinLoss.setText(tempCWL);

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
