package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;

public class ScheduleListAdapter extends ArrayAdapter<Game> {

    AppDatabase database;
    String playerTeam;

    private Context context;
    private int resource;

    public ScheduleListAdapter(Context context, int resource, ArrayList<Game> objects)
    {
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
        database = AppDatabase.getInstance(context);
        playerTeam = database.getStateDAO().getPlayerTeamString();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String opponent;
        boolean home;
        if (playerTeam.equals(getItem(position).getHome())) {
            opponent = "vs " + getItem(position).getAway();
            home = true;
        } else {
            opponent = "at " + getItem(position).getHome();
            home = false;
        }

        String result;
        if (getItem(position).getHomeScore() == -1)
            result = "";
        else {
            if (getItem(position).getHomeScore() > getItem(position).getAwayScore() && home)
                result = "W ";
            else
                result = "L ";
            result += getItem(position).getHomeScore() + "-" + getItem(position).getAwayScore();
        }

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent, false);

        TextView oppText = (TextView) convertView.findViewById(R.id.oppText);
        TextView resultText = (TextView) convertView.findViewById(R.id.resText);

        oppText.setText(opponent);
        resultText.setText(result);

        return convertView;

    }
}
