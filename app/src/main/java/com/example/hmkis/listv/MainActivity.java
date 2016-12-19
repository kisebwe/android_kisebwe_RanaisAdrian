package com.example.hmkis.listv;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity {


    private ListView lv;
    private String TAG = MainActivity.class.getSimpleName();

    ArrayList<Biere> biereList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Top Bieres");


        new GetBiere().execute();

    }



    /******************Prsing **************************/

    private class GetBiere extends AsyncTask<Void, Void, ArrayList<Biere>> {


        public void onClick(View v) {

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MainActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected ArrayList<Biere> doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "http://binouze.fabrigli.fr/bieres.json";
            String jsonStr = sh.makeServiceCall(url);


            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray bieres = new JSONArray(jsonStr);

                    // Getting JSON Array node

                    biereList = new ArrayList<>();

                    // looping through All biere
                    for (int i = 0; i < bieres.length(); i++) {
                        JSONObject b = bieres.getJSONObject(i);


                        String name = b.getString("name");
                        String description = b.getString("description");


                        Biere biere1 = new Biere();

                        // adding each child node to HashMap key => value
                        biere1.description=  description;
                        biere1.name=  name;
                        biereList.add(biere1);


                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });


                }


            } else {
                Log.e(TAG, "Couldn't get json from server.");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }

            Log.i("bie", "count " + biereList.size());

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Biere> result) {
            super.onPostExecute(result);
            /*
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, biereList,
                    R.layout.list_cell, new String[]{"name", "description"},
                    new int[]{R.id.name, R.id.description});
            lv.setAdapter(adapter);
        }*/


            final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
            rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            rv.setAdapter(new MyAdapter(biereList));

        }

    }

    /*******************************fin parsing*****************************/


}