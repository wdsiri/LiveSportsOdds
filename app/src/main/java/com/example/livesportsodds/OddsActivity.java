package com.example.livesportsodds;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OddsActivity extends AppCompatActivity {

    OddsHolder oddsHandler = new OddsHolder();
    SportHolder sportHandler = new SportHolder();

    boolean userSelect = false;
    private String url1 = "https://odds.p.rapidapi.com/v1/odds";
    private String LOG_TAG = OddsActivity.class.getSimpleName();
    private ArrayAdapter<String> oddsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView oddsList = findViewById(R.id.oddsList);
        oddsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, oddsHandler.odds);

        oddsList.setAdapter(oddsAdapter);
        new OddsActivity.FetchOdds().execute();
    }

    class FetchOdds extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String odds = null;
            try {
                URL url = new URL(url1);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("X-RapidAPI-Key", "aa657a5a07msh34b019e80346dd4p175a45jsnf1a888a50bc2");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(in));
                odds = getStringFromBuffer(reader);


            } catch (Exception e) {
                Log.e(LOG_TAG, "Error" + e.getMessage());
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e(LOG_TAG, "Error" + e.getMessage());
                        return null;
                    }
                }
            }
            return odds;
        }

        @Override
        protected void onPostExecute(String result) {
            oddsAdapter.notifyDataSetChanged();

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
                    return oddsHandler.getOdds(buffer.toString());
                } catch (Exception e) {
                    Log.e("OddsActivity", "Error" + e.getMessage());
                    return null;
                } finally {

                }
            }
            return null;
        }
    }
}
