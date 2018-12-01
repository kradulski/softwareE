package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.DAOs.StateDAO;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;

public class MyTeamFragment extends Fragment {

    AppDatabase database = AppDatabase.getInstance(getActivity());

    String playerTeam;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_my_team, container, false);

        playerTeam = database.getStateDAO().getPlayerTeam();

        Toast.makeText( getActivity(), "The player team is " + playerTeam, Toast.LENGTH_SHORT).show();

        return view;

    }
}
