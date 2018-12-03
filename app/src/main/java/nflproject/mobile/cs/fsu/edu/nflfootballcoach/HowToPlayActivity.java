package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

        DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEUTRAL:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(HowToPlayActivity.this);
        builder.setTitle("Season Summary")
                .setMessage((("<Name> (W-L) won the National Championship, beating <Name> (W-L) <Result>\n\n" +
                        "Your team, <Name>, finished the season ranked #<Rank> with <W> wins and <L> losses.")))
                .setNeutralButton("Dismiss", dialogListener).show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
