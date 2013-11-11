package com.timkonieczny.yuomeclickdummy;

import android.content.Intent;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.ImageView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MenuItem;
//import android.support.v4.app.NavUtils;


public class ScanReceiptActivity extends Activity {

    private static final int CAMERA_REQUEST = 1; //ID für die Callback-Methode der Kamera

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //final int CAMERA_REQUEST = 1; //ID für die Callback-Methode der Kamera
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_receipt);
        // Show the Up button in the action bar.
        setupActionBar();
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    /**
     * Get the uri of the captured file
     * @return A Uri which path is the path of an image file, stored on the dcim folder
     */
    private Uri getImageUri() {
        // Store image in dcim
        // Here you can change your internal storage path to store those images..
        String CAPTURE_TITLE="3.png";
        File file = new File(Environment.getExternalStorageDirectory() + "/Kassenzettel", CAPTURE_TITLE);
        Uri imgUri = Uri.fromFile(file);

        return imgUri;
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scan_receipt, menu);
        return true;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            ImageView receiptImage = (ImageView) findViewById(R.id.result_image);
            receiptImage.setImageBitmap(BitmapFactory.decodeFile("/Kassenzettel/3.png"));
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //receiptImage.setImageBitmap(photo);


            /*Uri receiptUri = Uri.parse("/Kassenzettel/3.png");

            try {
                InputStream inputStream = getContentResolver().openInputStream(receiptUri);
                Drawable drawable = Drawable.createFromStream(inputStream, null);
                receiptImage .setImageDrawable(drawable);
            } catch (FileNotFoundException e) {}
            //receiptImage.setImageResource(R.drawable.ic_launcher);*/
        }
        //receiptImage.setImageBitmap(BitmapFactory.decodeFile("/Kassenzettel/3.png"));
        //receiptImage.setImageResource(R.drawable.ic_launcher);
    }
}
	