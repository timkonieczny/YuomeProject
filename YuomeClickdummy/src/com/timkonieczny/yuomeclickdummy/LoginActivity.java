package com.timkonieczny.yuomeclickdummy;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button b;
    EditText user;
	EditText pass;
    TextView tv;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		b = (Button)findViewById(R.id.login_button);
        user = (EditText)findViewById(R.id.username);
        pass= (EditText)findViewById(R.id.password);

        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(LoginActivity.this, "","Validating user...", true);
                new Thread(new Runnable(){public void run(){userLogIn();}}).start();
            }
        });
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
    public void userSignUp(View view){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    
    public void userLogIn(){
    	 try{
    		 final String response = PHPConnector.getLoginResponse("http://andibar.dyndns.org:5678/Yuome/check_for_user.php", user.getText().toString().trim(), pass.getText().toString().trim());
             System.out.println("Response : " + response);
             dialog.dismiss();
             if(response.equalsIgnoreCase(user.getText() + " has logged in successfully.")){
                 runOnUiThread(new Runnable() {
                     public void run() {
                         Toast.makeText(LoginActivity.this,response, Toast.LENGTH_SHORT).show();
                     }
                 });

                 Intent intent = new Intent(this, SplashscreenActivity.class);
                 startActivity(intent);
             }
             else if(response.equalsIgnoreCase(user.getText() + " already logged in.")){
                 runOnUiThread(new Runnable() {
                     public void run() {
                         Toast.makeText(LoginActivity.this,response, Toast.LENGTH_SHORT).show();
                     }
                 });
                 Intent intent = new Intent(this, SplashscreenActivity.class);
                 startActivity(intent);
             }else{
                 showAlert();
             }

         }catch(Exception e){
             dialog.dismiss();
             showNoConnectionAlert(e.getMessage());
    	 }
    }
    public void showAlert(){
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Login Error");
                builder.setMessage("User not found or password incorrect.")
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
    public void showNoConnectionAlert(String error){
    	final String errortext = error;
        LoginActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Login Error.");
                builder.setMessage(errortext)
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

}
