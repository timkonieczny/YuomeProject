package com.timkonieczny.yuome;

import com.timkonieczny.yuome.R;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class FirstActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.first, menu);
        return true;
    }

    /*public void createCommunity(View view){
        Intent create_community = new Intent(this, CreateActivity.class);
        startActivity(create_community);
    }*/

    public void joinCommunity(View view){
        Intent join_community = new Intent(this, JoinCommunityActivity.class);
        startActivity(join_community);
    }

    public void createCommunity(View view){
        Intent create_community = new Intent(this, CreateCommunityActivity.class);
        startActivity(create_community);
    }

}
//aofjiöjsefljia