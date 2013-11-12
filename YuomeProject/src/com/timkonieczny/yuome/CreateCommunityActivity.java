package com.timkonieczny.yuome;


import com.timkonieczny.yuome.R;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CreateCommunityActivity extends Activity {

    Button b;
    EditText et;
    EditText pw;
    TextView tv;
    ProgressDialog dialog = null;
    HttpClient httpclient;
    HttpPost httppost;
    List<NameValuePair> nameValuePairs;
    HttpResponse response;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        
        b = (Button)findViewById(R.id.create_community);
        et = (EditText)findViewById(R.id.community_name);
        pw= (EditText)findViewById(R.id.community_password);
        tv = (TextView)findViewById(R.id.http_response);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(CreateCommunityActivity.this, "",
                        "Erstelle WG...", true);
                new Thread(new Runnable(){public void run(){create();}}).start();
            }
        });
    }

    void create(){
   
        Connection myCon;
        Statement myStmt;
        try{
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          myCon = DriverManager.getConnection("jdbc:mysql://127.0.0.1/deptdata?user=root");
          myStmt = myCon.createStatement();
          int result = myStmt.executeUpdate(
             "INSERT INTO community (name, password) VALUES ('" + et.getText().toString().trim() + "', '" + et.getText().toString().trim() + "')");
          myCon.close();
          Toast.makeText(CreateCommunityActivity.this, "Erstellen erfolgreich", Toast.LENGTH_SHORT).show();
          showAlert();
        }
        catch (Exception sqlEx){
          System.err.println(sqlEx);
        }
    }
    public void showAlert(){
        CreateCommunityActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateCommunityActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage("User not Found.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_community, menu);
        return true;
    }
    
}
