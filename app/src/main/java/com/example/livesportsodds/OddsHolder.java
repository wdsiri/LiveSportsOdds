package com.example.livesportsodds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OddsHolder {

    public static final String [] odds = new String[66];

    public OddsHolder() {
        for (int i = 0; i < odds.length; i++)
            odds[i] = "";


    }

    public String getOdds(String oddsJsonStr) throws JSONException {
        JSONObject oddsJSONObj = new JSONObject(oddsJsonStr);
        System.out.println(oddsJSONObj);
        JSONArray oddsArray = oddsJSONObj.getJSONArray("title");
        // write a loop where the array elements and put the array elements in your own array of sports
        // dataArray.get(i).getString("odds");

        if (oddsArray != null) {
            //  System.out.println(dataArray.length());
            for (int i = 0; i < oddsArray.length(); i++) {
                //   System.out.println(i);
                JSONObject obj = oddsArray.getJSONObject(i);
                //    System.out.println(i);
                if (obj != null) {
                    odds[i] = obj.getString("h2h");

                }
                else {
                    System.out.println("object is null");
                }
            }
        }
        return null;
    }
}
