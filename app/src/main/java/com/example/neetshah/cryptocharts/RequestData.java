package com.example.neetshah.cryptocharts;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestData extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://www.cryptocompare.com/api/data/coinlist/");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                JSONObject mainObject = new JSONObject(line);
//                JSONObject uniObject = mainObject.getJSONObject("Data");
//                JSONObject object2 = uniObject.getJSONObject("365");
                JSONArray object3 = mainObject.getJSONArray("Data");
                Log.d("LENGHT: ", String.valueOf(object3.length()));
//                for (int i =0; i<object3.length(); i++) {
////                    Log.d("CHECK"+i, object3.getJSONObject(i).toString());
//                    object3.getJSONObject(i);
//                }
                JSONObject a = object3.getJSONObject(4);
//                Log.d("CHECK", array.toString());
            }

            //return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}