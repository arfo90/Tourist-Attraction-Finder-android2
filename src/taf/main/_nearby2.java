package taf.main;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import taf.android.service._jsonparser; 


public class _nearby2 extends ListActivity {
    /** Called when the activity is first created. */
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 5000; 
	protected LocationManager locationManager;
	
	double lat ; 
    double log ; 
	
   /* Bundle bundle = getIntent().getExtras();
    String category = bundle.getString("category");
    */ 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
        
        Bundle bundle = getIntent().getExtras();
        String category = bundle.getString("category");
        
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates(
        		locationManager.GPS_PROVIDER,
        		MINIMUM_TIME_BETWEEN_UPDATES,
        		MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
        		new MyLocationListener()); 
        
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    String message = String.format(
                            "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                            log =  location.getLongitude(),lat = location.getLatitude());}

       
        
    ArrayList<HashMap<String, String>> myArraylist = new ArrayList<HashMap<String,String>>();
    String address;    
    //The following method get lon and lat 
        
		JSONObject json3 =
				new _jsonparser().getjson_from_url("http://local.yahooapis.com/LocalSearchService/V3/localSearch?appid=YahooDemo&query=restaurants&output=json&zip=94306&result=5callback=ws_re");
		
		JSONObject json2 =
			new _jsonparser().getjson_from_url("http://upcoming.yahooapis.com/services/rest/?api_key=679c86c2bd&format=json&country=malaysia&method=event.search&search_text=killers&metro_id=1");
		JSONObject json =
			new _jsonparser().getjson_from_url("https://maps.googleapis.com/maps/api/place/search/json?location="+lat+","+log+"&radius=500&types="+category+"&sensor=false&key=AIzaSyC9h2VdbDA57knJ3rkPUjrxWtNli7ytQfk");
		
		try {
			
			JSONArray event = json.getJSONArray("results"); 
			//JSONArray event2 = json.getJSONArray("location");
			
			for (int i=0; i<event.length(); i++){
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				JSONObject e = event.getJSONObject(i); 
				//JSONObject e2 = event2.getJSONObject(i); 
				
				map.put("id", String.valueOf(i));
				map.put("title", "" + e.getString("name"));
				map.put("location", "" + e.getString("geometry"));
				map.put("refrence", e.getString("reference"));
				map.put("Address", "Address: " + e.getString("vicinity"));
				map.put("address", "Address: " +  e.getString("id"));
				map.put("refrence", e.getString("reference"));
				map.put("cat","Category: " + category); 
				
				myArraylist.add(map); 
				
				//double log2 = e2.getDouble("lat"); 
				//String logs = getString(log2); 
				//map.put("log", logs);

			}
			}catch (JSONException e){e.toString();}
			
			 ListAdapter adptor = new SimpleAdapter(this,  myArraylist , R.layout.textcustom,
					 new String[] { "title", "cat" }, 
                     new int[] { R.id.item_title, R.id.item_subtitle }); 
			 
			 setListAdapter(adptor);
			 
			 final ListView lv = getListView();
		        lv.setTextFilterEnabled(true);	
		        lv.setOnItemClickListener(new OnItemClickListener() {
		        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
		        		@SuppressWarnings("unchecked")
		        		
						HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	      
		        		
		        		 
		        		Toast.makeText(_nearby2.this, o.get("Category:")+ o.get("cat")+"\n" + o.get("Address"),  Toast.LENGTH_LONG).show(); 
		        	    
		        		 Intent i = new Intent(_nearby2.this ,deatels_Activity.class); 
		                 
		                  Bundle bundle1 = new Bundle();
		                  bundle1.putString("refrenc", o.get("refrence"));
		                  bundle1.putString("title", o.get("title"));
		                  bundle1.putString("address", o.get("Address"));
		                  i.putExtras(bundle1); 
		                  startActivity(i);  

					}
				});
		        
		    	
       		
			 
	}
	
	
	
		        private String getString(double log2) {
	// TODO Auto-generated method stub
	return null;
}



				private class MyLocationListener implements LocationListener{
		        	
		        	 public void onLocationChanged(Location location) {
		        		 
		        		             String message = String.format(
		        		 
		        		                     "New Location \n Longitude: %1$s \n Latitude: %2$s",
		        		 
		        		                     location.getLongitude(), location.getLatitude()
		        		 
		        		             );
		        		 
		        		             Toast.makeText(_nearby2.this, message, Toast.LENGTH_LONG).show();
		        		 
		        		         }
		        		 
		        		         public void onStatusChanged(String s, int i, Bundle b) {
		        		 
		        		             Toast.makeText(_nearby2.this, "Provider status changed",
		        		 
		        		                     Toast.LENGTH_LONG).show();
		        		 
		        		         }
		        		 
		        		         public void onProviderDisabled(String s) {
		        		 
		        		             Toast.makeText(_nearby2.this,
		        		 
		        		                     "Provider disabled by the user. GPS turned off",
		        		
		        		                     Toast.LENGTH_LONG).show();
		        		 
		        		         }
		        		
		        		         public void onProviderEnabled(String s) {
		        		
		        		             Toast.makeText(_nearby2.this,
		        		
		        		                     "Provider enabled by the user. GPS turned on",
		        		
		        		                     Toast.LENGTH_LONG).show();
		        		         }
		        }
		  
		        
    }