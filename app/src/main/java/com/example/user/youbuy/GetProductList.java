package com.example.user.youbuy;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;
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
import android.os.Bundle;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonObjectRequest;
//public class GetProductList{
//
//    protected void onCreate(Bundle savedInstanceState){
//
//    }
//}

public class GetProductList extends AsyncTask<Void, Void, Void> {
    String data;
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Log.e("BG", "Background process");
            URL url = new URL("https://rocky-ocean-68053.herokuapp.com/api/Products");
            HttpURLConnection  httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//            System.out.print(bufferedReader.readLine());
            String line="";
            while(line!=null){
                line = bufferedReader.readLine();
                data = data + line;
            }

//            JSONArray JA = new JSONArray(data);
//            for(int i = 0;i<JA.length();i++){
//                JSONObject JO = (JSONObject) JA.get(i);
//            }
            System.out.println("getting background processing");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        System.out.println("Data will shown there");
        System.out.println(data);
    }
}
