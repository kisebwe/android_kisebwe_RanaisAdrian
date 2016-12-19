package com.example.hmkis.listv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by hmkis on 25/11/2016.
 */

public class Home extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //setTitle("Top Bieres");

        Button site = (Button)findViewById(R.id.topsite);
        site.setOnClickListener(this);


        Button yourbeer = (Button)findViewById(R.id.yourbeer);
        yourbeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Home.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View view) {

        String url = "http://www.paradis-biere.com/top-biere.html";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }






    /**********Mon menu**************************/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_write:
                //do

                String url ="https://www.linkedin.com/in/rapha%C3%ABl-ranais-adrian-85aa5b117?authType=NAME_SEARCH&authToken=fINd&locale=fr_FR&trk=tyah&trkInfo=clickedVertical%3Amynetwork%2CclickedEntityId%3A489327622%2CauthType%3ANAME_SEARCH%2Cidx%3A1-1-1%2CtarId%3A1482094219495%2Ctas%3Ar";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);


            case R.id.action_help:
                //do
                String url2 = "https://www.linkedin.com/in/hugo-kisebwe-323652114?trk=nav_responsive_tab_profile_pic";
                Intent i2 = new Intent(Intent.ACTION_VIEW);
                i2.setData(Uri.parse(url2));
                startActivity(i2);
               // return true;
        }
        return super.onOptionsItemSelected(item);
    }




    /**********************Fin Menu**********************/

}

