package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TeamSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button fsu_btn = findViewById(R.id.fsu_btn);
        fsu_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(TeamSelectActivity.this, PlayGameActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putString("teamName", "Florida State University");
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });

        Button uf_btn = findViewById(R.id.uf_btn);
        uf_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(TeamSelectActivity.this, PlayGameActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putString("teamName", "University of Florida");
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });

        Button um_btn = findViewById(R.id.um_btn);
        um_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(TeamSelectActivity.this, PlayGameActivity.class);
                Bundle myBundle = new Bundle();
                myBundle.putString("teamName", "University of Miami");
                myIntent.putExtras(myBundle);
                startActivity(myIntent);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
