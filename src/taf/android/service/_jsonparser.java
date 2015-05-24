package taf.android.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class _jsonparser {

	 public static JSONObject getjson_from_url (String url){JSONObject jArray = null; 
 	InputStream is =null; 
	String result = ""; 
	
	// Http request 
	try{
	HttpClient httpclient = new DefaultHttpClient(); 
	HttpPost httppost = new HttpPost(url); 
	HttpResponse response = httpclient.execute(httppost); 
	HttpEntity entity = response.getEntity(); 
	is = entity.getContent(); 
	}catch (IOException e){Log.e("log_tag", "Error in http connection "+e.toString());}; 
	
	
	//Converting response to String
	
	try{
	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"),8);
	StringBuilder sb = new StringBuilder(); 
	String line = null; 
	while ((line = reader.readLine()) != null){
		
		sb.append(line +"\n");
	}
	is.close();
	result = sb.toString(); 
	}catch(IOException e){}; 

	//converting string to json
	try {
	jArray = new JSONObject(result);
	}catch(JSONException e){Log.e("log_tag", "Error in http connection "+e.toString());}
	
	return jArray; 
	
	 } 
	
  }
