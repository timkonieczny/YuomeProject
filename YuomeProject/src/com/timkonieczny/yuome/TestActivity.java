package com.timkonieczny.yuome;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ImageView testImage = (ImageView) findViewById(R.id.test_image);
        testImage.setImageBitmap(BitmapFactory.decodeFile("/Kassenzettel/3.png"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
    
}
