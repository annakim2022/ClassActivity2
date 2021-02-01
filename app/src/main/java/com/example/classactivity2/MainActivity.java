package com.example.classactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    // define variables
    private Button button_go;
    private SearchView searchView_city;
    private static AsyncHttpClient client = new AsyncHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // look up button by id
        button_go = findViewById(R.id.button_go);
        searchView_city = findViewById(R.id.searchView_city);
        // add an event listener to listen for the click
        button_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // handle what happens after I click
                launchNextActivity(v);

            }
        });
    }
    public void launchNextActivity(View view) {
        String city = searchView_city.getQuery().toString();
        String api_url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=a6ed2508b2a773993e6cfafbada1d54f&units=imperial";

        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
               Log.d("api response", new String(responseBody));

                try {

                    JSONObject json = new JSONObject(new String(responseBody));
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    JSONObject sys = json.getJSONObject("sys");
                    String country = sys.getString("country");
                    String location = city + ", " + country;
                    intent.putExtra("location", location);

                    JSONArray weather = json.getJSONArray("weather");
                    JSONObject description = weather.getJSONObject(0);
                    intent.putExtra("description", description.getString("description"));

                    JSONObject temp_min = json.getJSONObject("main");
                    intent.putExtra("temp_min", temp_min.getString("temp_min"));

                    JSONObject temp_max = json.getJSONObject("main");
                    intent.putExtra("temp_max", temp_max.getString("temp_max"));

                    JSONObject feels_like = json.getJSONObject("main");
                    intent.putExtra("feels_like", feels_like.getString("feels_like"));

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });


    }


}