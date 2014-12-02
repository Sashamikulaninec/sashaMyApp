package com.example.sasha.myapplication555;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.view.View;

import com.github.kevinsawicki.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by sasha on 01.12.14.
 */
public class AsyncJSON extends AsyncTask<String ,Void ,JSONObject> {







    private static String url = "https://api.theprintful.com/countries";


    private ProgressDialog pDialog;

    @Override
    protected void onPreExecute() {


            super.onPreExecute();
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();


    }

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject jsonObject = null;
        HttpRequest request = HttpRequest.get("https://api.theprintful.com/countries");

        if (request.code() == 200) {
            String response = request.body();
            try {
                jsonObject = new JSONObject(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject result) {

        super.onPostExecute(result);
        result.toString();
        Log.v("bllaaa", result.toString());
    }
}