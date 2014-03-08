package com.timkonieczny.yuomeclickdummy;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class PHPConnector {
	
	public static String response;
	public static HttpClient httpclient = new DefaultHttpClient();
	public static HttpGet httpget;
	public static HttpPost httppost;
	public static ArrayList<NameValuePair> nameValuePairs;
	public static ResponseHandler<String> responseHandler;
	
	public static String getResponse(String url){
		try {
			httpget = new HttpGet(url);
			responseHandler = new BasicResponseHandler();
			response = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	public static String getLoginResponse(String url, String user, String pass) throws ClientProtocolException, IOException{
             httppost = new HttpPost(url);
             nameValuePairs = new ArrayList<NameValuePair>(2);
             nameValuePairs.add(new BasicNameValuePair("username",user));  
             nameValuePairs.add(new BasicNameValuePair("password",pass));
             httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
             ResponseHandler<String> responseHandler = new BasicResponseHandler();
             response = httpclient.execute(httppost, responseHandler);
		
		return response;
	}
	public static void logOff() throws ClientProtocolException, IOException{
		System.out.println("blaaa");
		httpget = new HttpGet("http://andibar.dyndns.org:5678/Yuome/log_off.php");
		httpclient.execute(httpget);

	}
}
