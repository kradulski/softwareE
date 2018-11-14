package nflproject.mobile.cs.fsu.edu.nflfootballcoach;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TeamSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_select);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button fsu_btn = findViewById(R.id.fsu_btn);
        fsu_btn.setText(Html.fromHtml("<b><big>" + "Florida State University" + "</big></b>" +
                                        "<br />" + "<small>" + "Prestige: 99" + "</small>" + "<br />"));
        fsu_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clickAction("Florida State University");
            }
        });

        Button uf_btn = findViewById(R.id.uf_btn);
        uf_btn.setText(Html.fromHtml("<b><big>" + "University of Florida" + "</big></b>" +
                "<br />" + "<small>" + "Prestige: 70" + "</small>" + "<br />"));
        uf_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clickAction("University of Florida");
            }
        });

        Button um_btn = findViewById(R.id.um_btn);
        um_btn.setText(Html.fromHtml("<b><big>" + "University of Miami" + "</big></b>" +
                "<br />" + "<small>" + "Prestige: 16" + "</small>" + "<br />"));
        um_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                clickAction("University of Miami");
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private void clickAction(String s){
        Intent myIntent = new Intent(TeamSelectActivity.this, PlayGameActivity.class);
        Bundle myBundle = new Bundle();
        myBundle.putString("teamName", s);
        myIntent.putExtras(myBundle);
        MainActivity.setResetVal(false);
        startActivity(myIntent);
    }
}
