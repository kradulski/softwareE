package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nflproject.mobile.cs.fsu.edu.nflfootballcoach.Database.AppDatabase;
import nflproject.mobile.cs.fsu.edu.nflfootballcoach.models.Game;

public class ScheduleListAdapter extends ArrayAdapter<scheduleRow> {

    private Context mContext;
    int mResource;

    public ScheduleListAdapter(Context context, int resource, ArrayList<scheduleRow> objects)
    {
        super(context,resource,objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String opponent = getItem(position).getOpponent();
        String result = getItem(position).getResult();

        scheduleRow entry = new scheduleRow(opponent, result);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView oppText = (TextView) convertView.findViewById(R.id.oppText);
        TextView resText = (TextView) convertView.findViewById(R.id.resText);

        oppText.setText(opponent);
        resText.setText(result);
        if(result.matches("W.*"))
            resText.setBackgroundColor(Color.parseColor("#ADFF2F"));
        else if(result.matches("L.*"))
            resText.setBackgroundColor(Color.parseColor("#B22222"));

        return convertView;

    }
}
