package com.example.classactivity2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    private TextView textView_tempMin;
    private TextView textView_tempMax;
    private TextView textView_feelsLike;
    private TextView textView_description;
    private TextView textView_location;
    private TextView textView_noCity;
    //private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
       // linearLayout = findViewById(R.id.linearLayout);
        Intent intent = getIntent();

        if (intent.getStringExtra("description")==null) {
            textView_noCity = findViewById(R.id.textView_noCity);
            textView_noCity.setText("No city found.");

        }

        else {
            textView_location = findViewById(R.id.textView_loc);
            textView_location.setText(intent.getStringExtra("location"));

            textView_description = findViewById(R.id.textView_des);
            textView_description.setText(intent.getStringExtra("description"));

            textView_tempMin = findViewById(R.id.textView_min);
            textView_tempMin.setText(intent.getStringExtra("temp_min") + "F");

            textView_tempMax = findViewById(R.id.textView_max);
            textView_tempMax.setText(intent.getStringExtra("temp_max") + "F");

            textView_feelsLike = findViewById(R.id.textView_feels);
            textView_feelsLike.setText(intent.getStringExtra("feels_like") + "F");
        }


    }


}
