package android.jobtracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JobListing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);

        final TextView textView = (TextView) findViewById(R.id.text);
// ...
        String s= getIntent().getStringExtra("userInput");

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.indeed.com/jobs?"+ "q="+ s + "&l=95112";
        Log.d("url: ", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
// Display the first 500 characters of the response string.

//                .substring(0, 2000)
                Document doc =  Jsoup.parse(response);
                String title = doc.title();
                Elements links = doc.select("a[href]");

                final StringBuilder builder = new StringBuilder();
                builder.append(title).append("\n");

                for (Element link : links) {
//                    builder.append("\n\n").append("Link : ").append(link.attr("href")).append(link.attr("rel"))
                           builder .append("\n\n").append("Text : ").append(link.text()).append(link.attr(" id "));
                }

                textView.setText("Response is: " + builder.toString());

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });


        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }
    }

