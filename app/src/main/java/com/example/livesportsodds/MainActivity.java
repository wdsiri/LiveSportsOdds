package com.example.livesportsodds;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    static final String[] sportsGroups = new String[0];
    boolean userSelect = false;
    private String url1 = "https://odds.p.rapidapi.com/v1/sports";
    private String url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
    }
}