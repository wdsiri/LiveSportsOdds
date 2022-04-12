package com.example.livesportsodds;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SportHolder spHandler = new SportHolder();

    boolean userSelect = false;
    private String url1 = "https://odds.p.rapidapi.com/v1/sports";
    private String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView sportList = findViewById(R.id.sportList);
        ArrayAdapter<String> sportAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spHandler.sports);

        sportList.setAdapter(sportAdapter);
        new FetchSport().execute();

    }


    class FetchSport extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String sport = null;
            try {
                URL url = new URL(url1);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key","aa657a5a07msh34b019e80346dd4p175a45jsnf1a888a50bc2");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(in));
                sport = getStringFromBuffer(reader);
                System.out.println(sport);

            }catch (Exception e) {
                Log.e(LOG_TAG,"Error" + e.getMessage());
                return null;
            }finally {
                if(urlConnection != null) {
                    urlConnection.disconnect();
                }
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG,"Error" + e.getMessage());
                        return null;
                    }
                }
            }
            return sport;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Log.d(LOG_TAG, "Result is null");
            //    Intent intent = new Intent(MainActivity.this, // new class );
            //    intent.putExtra("yearfact", result);
            //    startActivity(intent);
            }
        }
    }

    private String getStringFromBuffer(BufferedReader bufferedReader) {
        StringBuffer buffer = new StringBuffer();
        String line;

        if (bufferedReader != null) {
            try {
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line + '\n');
                }
                bufferedReader.close();
                return spHandler.getSport(buffer.toString());
            } catch (Exception e) {
                Log.e("MainActivity", "Error" + e.getMessage());
                return null;
            } finally {

            }
        }
        return null;
    }

}