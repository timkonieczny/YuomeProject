package com.timkonieczny.yuome;

import com.timkonieczny.yuome.R;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

public class SplashscreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_splashscreen, menu);
        return true;
    }*/
    
    public void myDebts(View view){
        Intent my_debts = new Intent(this, MyDebtsActivity.class);
        startActivity(my_debts);
    }

    public void scanReceipt(View view){
        Intent scan_receipt = new Intent(this, ScanReceiptActivity.class);
        startActivity(scan_receipt);
    }

    public void testMethod(View view){
        Intent test = new Intent(this, TestActivity.class);
        startActivity(test);
    }

    public void myReceipts(View view){
        Intent my_receipts = new Intent(this, MyReceiptsActivity.class);
        startActivity(my_receipts);
    }
    
}
