package android.jobtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_jobs);


        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText mEdit;
                mEdit   = (EditText)findViewById(R.id.position_title);
                String userInput =mEdit.getText().toString();
                Intent myintent=new Intent(SearchJobs.this, JobListing.class).putExtra("userInput", userInput);
                startActivity(myintent);
//                Intent intent = new Intent(SearchJobs.this, JobListing.class);
//                startActivity(intent);
//                finish();
            }
        });

    }
}
