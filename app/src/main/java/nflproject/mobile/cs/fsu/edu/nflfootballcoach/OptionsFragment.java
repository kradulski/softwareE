package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class OptionsFragment extends Fragment implements View.OnClickListener {
    View optionView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        optionView = inflater.inflate(R.layout.fragment_options, null);

        Button howToPlayButton = optionView.findViewById(R.id.option_how_to_button);
        howToPlayButton.setOnClickListener(this);

        Button resetButton = optionView.findViewById(R.id.option_reset_button);
        resetButton.setOnClickListener(this);

        return optionView;
    }

    @Override
    public void onClick(View view) {
        Intent myIntent;
        switch (view.getId()) {
            case R.id.option_how_to_button:
                myIntent = new Intent(getActivity(), HowToPlayActivity.class);
                break;
            case R.id.option_reset_button:
                myIntent = new Intent(getActivity(), MainActivity.class);
                MainActivity.setResetVal(true);
                break;
            default:
                myIntent = new Intent(getActivity(), MainActivity.class);
                break;
        }

        startActivity(myIntent);
    }
}
