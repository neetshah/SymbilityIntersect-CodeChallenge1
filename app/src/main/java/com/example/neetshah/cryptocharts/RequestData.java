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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;

public class RequestData extends AsyncTask {

    private Double getPrice(String curr) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("https://min-api.cryptocompare.com/data/price?fsym=" + URLEncoder.encode(curr, "UTF-8") + "&tsyms=CAD");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            line = reader.readLine();
//            Log.d("Response: ", "> " + line);
            JSONObject mainObject = new JSONObject(line);
            String val = mainObject.getString("CAD");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
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
        return 1.0;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        ArrayList<CurrencyData> currencyData = new ArrayList<>();
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
//                Log.d("Response: ", "> " + line);
                JSONObject mainObject = new JSONObject(line);
                JSONObject data = mainObject.getJSONObject("Data");
                Iterator<String> iterator = data.keys();
                ArrayList<String> names = new ArrayList<>();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    Double price = getPrice(key);
                    if (price == null) {
                        continue;
                    }
                    JSONObject currency = (JSONObject) data.get(key);
                    String name = currency.getString("CoinName");
                    names.add(name);
//                    Log.d("TAG", "NAME: " + key);
                    CurrencyData currencyData1 = CurrencyData.builder()
                            .fullName(name)
                            .name(key)
                            .isFavourite(false)
                            .price(price)
                            .build();
                }
            }


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
        Log.d("TAG, ", "DONE");
        return null;
    }
}