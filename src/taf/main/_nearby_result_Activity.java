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


public class _nearby_result_Activity extends ListActivity {
    /** Called when the activity is first created. */
	
	
    Bundle bundle = getIntent().getExtras();
    String category = bundle.getString("category");
    
    double lat = bundle.getDouble("lat"); 
    double log = bundle.getDouble("log"); 
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listplaceholder);
       
        
    ArrayList<HashMap<String, String>> myArraylist = new ArrayList<HashMap<String,String>>();
       
    //The following method get lon and lat 
        
		JSONObject json3 =
				new _jsonparser().getjson_from_url("http://local.yahooapis.com/LocalSearchService/V3/localSearch?appid=YahooDemo&query=restaurants&output=json&zip=94306&result=5callback=ws_re");
		
		JSONObject json2 =
			new _jsonparser().getjson_from_url("http://upcoming.yahooapis.com/services/rest/?api_key=679c86c2bd&format=json&country=malaysia&method=event.search&search_text=killers&metro_id=1");
		JSONObject json =
			new _jsonparser().getjson_from_url("https://maps.googleapis.com/maps/api/place/search/json?location="+lat+","+log+"&radius=500&types=food&sensor=false&key=AIzaSyC9h2VdbDA57knJ3rkPUjrxWtNli7ytQfk");
		
		try {
			
			JSONArray event = json.getJSONArray("results"); 
			
			
			for (int i=0; i<event.length(); i++){
				
				HashMap<String, String> map = new HashMap<String, String>();
				
				JSONObject e = event.getJSONObject(i); 
				
				map.put("id", String.valueOf(i));
				map.put("title", "Title" + e.getString("name"));
				map.put("address", "Address: " +  e.getString("id"));
				map.put("refrence", e.getString("reference"));
				myArraylist.add(map); 
				
				

			}
			}catch (JSONException e){e.toString();}
			
			 ListAdapter adptor = new SimpleAdapter(this,  myArraylist , R.layout.textcustom,
					 new String[] { "title", "address" }, 
                     new int[] { R.id.item_title, R.id.item_subtitle }); 
			 
			 setListAdapter(adptor);
			 
			 final ListView lv = getListView();
		        lv.setTextFilterEnabled(true);	
		        lv.setOnItemClickListener(new OnItemClickListener() {
		        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {        		
		        		@SuppressWarnings("unchecked")
						HashMap<String, String> o = (HashMap<String, String>) lv.getItemAtPosition(position);	      
		        		
		        		Toast.makeText(_nearby_result_Activity.this, "ID '" + o.get("id") +"\n"+ o.get("address")+"' was clicked.", Toast.LENGTH_LONG).show(); 

					}
				});
			 
	}
	

    }
