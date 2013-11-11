package com.timkonieczny.yuome;

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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends Activity {
    Button sign_up_button;
    EditText username, password, repeat_password;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> new_user;
    ProgressDialog signing_up_dialog = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_button = (Button)findViewById(R.id.sign_up_button);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        repeat_password = (EditText)findViewById(R.id.repeat_password);

        sign_up_button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(password.getText().toString().trim().equals(repeat_password.getText().toString().trim())){
            		signing_up_dialog = ProgressDialog.show(SignUpActivity.this, "","Registrieren...", true);
            		new Thread(new Runnable(){public void run(){login();}}).start();
            	}else{
                   	Toast.makeText(SignUpActivity.this,"Passwörter stimmen nicht überein", Toast.LENGTH_SHORT).show(); 
                };
            }
        });
    }

    void login(){
        try{
        	httpclient=new DefaultHttpClient();
            httppost= new HttpPost("http://www.timbotombo.heliohost.org/add_user.php");
           
            new_user = new ArrayList<NameValuePair>(2);
           
            new_user.add(new BasicNameValuePair("username",username.getText().toString().trim())); 
            new_user.add(new BasicNameValuePair("password",password.getText().toString().trim()));
            httppost.setEntity(new UrlEncodedFormEntity(new_user));

            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            System.out.println("test");
            final String response = httpclient.execute(httppost, responseHandler);					//add_user.php gibt keine Response zurück???
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                public void run() {
            
                	System.out.println("Response from PHP : " + response);
                    signing_up_dialog.dismiss();
                }
            });
            if(response.equalsIgnoreCase("Success")){
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(SignUpActivity.this,"SignUp Success", Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(SignUpActivity.this, FirstActivity.class));
            }else{
                showAlert();
            }
        }catch(Exception e){
            signing_up_dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
    public void showAlert(){
    	SignUpActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("Signup Error.");
                System.out.println(response.toString());
                builder.setMessage(response.toString())
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