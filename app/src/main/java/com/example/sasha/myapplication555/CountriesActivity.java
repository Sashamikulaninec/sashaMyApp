package com.example.sasha.myapplication555;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.impl.conn.SingleClientConnManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CountriesActivity extends ListActivity {



    private static String url = "https://api.theprintful.com/countries";
    private static final String TAG_COUNTRIES = "countries";
    private static final String TAG_RESULT = "result";
    private static final String TAG_CODE = "code";
    private static final String TAG_NAME = "name";
    private static final String TAG_STATES = "states";
    JSONArray countries = null;
    Button btnCts;

    ArrayList<HashMap<String, String>> countriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        setTitle("Countries");
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.setBehindWidth(200);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.menu);
        countriesList = new ArrayList<HashMap<String, String>>();
        ListView lv = getListView();

        // Listview on item click listener
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.code))
                        .getText().toString();
                String code = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String states = ((TextView) view.findViewById(R.id.states))
                        .getText().toString();

                // Starting single contact activity


            }
        });
        new AsyncJSON().execute();


        btnCts = (Button) findViewById(R.id.btnCts);
        OnClickListener oclBtnCts = new OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONParser jParser = new JSONParser();
                JSONObject json = jParser.getJSONFromUrl(url);
                try {
                    countries = json.getJSONArray(TAG_COUNTRIES);
                    for (int i = 0; i < countries.length(); i++){
                        JSONObject c = countries.getJSONObject(i);
                        String result = c.getString(TAG_RESULT);
                        String code = c.getString(TAG_CODE);
                        String name = c.getString(TAG_NAME);
                        String states = c.getString(TAG_STATES);

                    }
                }
                catch (JSONException e){
                    e.printStackTrace();
                }
                ListAdapter adapter = new SimpleAdapter(
                        CountriesActivity.this, countriesList,
                        R.layout.list_item, new String[] { TAG_CODE, TAG_NAME,
                        TAG_STATES }, new int[] { R.id.code,
                        R.id.name, R.id.states });

                setListAdapter(adapter);

            }
        };
        btnCts.setOnClickListener(oclBtnCts);

    }




    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        getMenuInflater().inflate(R.menu.my, menu);

        return true;

    }
}