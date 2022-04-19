package com.example.livesportsodds;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SportHolder {

    public static final String [] sports = new String[66];

    public SportHolder() {
        for (int i = 0; i < sports.length; i++)
            sports[i] = "";


    }

    public String getSport(String sportJsonStr) throws JSONException {
        JSONObject sportJSONObj = new JSONObject(sportJsonStr);
        System.out.println(sportJSONObj);
        JSONArray dataArray = sportJSONObj.getJSONArray("data");
        // write a loop where the array elements and put the array elements in your own array of sports
        // dataArray.get(i).getString("sport");

        if (dataArray != null) {
            //  System.out.println(dataArray.length());
            for (int i = 0; i < dataArray.length(); i++) {
                //   System.out.println(i);
                JSONObject obj = dataArray.getJSONObject(i);
                //    System.out.println(i);
                if (obj != null) {
                    sports[i] = obj.getString("title");

                }
                else {
                    System.out.println("object is null");
                }
            }
        }
        return null;
    }
}
