package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class HowToPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);

    }

    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }
}
