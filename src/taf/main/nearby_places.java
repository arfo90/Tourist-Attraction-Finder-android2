package taf.main;


import android.app.Activity; 
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.content.Intent; 


public class nearby_places extends Activity{
  
	//String[] items = getResources().getStringArray(R.array.category);//{"Resturant", "bus stop", "Tourist Area", "Shoping Mall", "sd","sd","sd"};  
	String category; 
	ListView lv; 
	TextView selection; 
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; 
	protected LocationManager locationManager;
	
	double log;
	double lat;
	String Category; 
	
	@Override 
	public void onCreate(Bundle Icicnp) {
        super.onCreate(Icicnp);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.nearby_layout);
        
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
        
        lv =(ListView)findViewById(R.id.nb_listview); 
        
        final String[] items = getResources().getStringArray(R.array.category);
        
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
       // selection = (TextView)findViewById(R.id.nearby_menu_text);
         	   
        
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
            /*
            category = items[position].toString(); 
            Intent i = new Intent(nearby_places.this , Taf.class); 
            startActivity(i); 
            */
             
            	
            switch(position){
            case 0: Intent i = new Intent(nearby_places.this , _nearby2.class); 
            category = "restaurant";
            Bundle bundle = new Bundle();
            bundle.putDouble("log", log); 
            bundle.putDouble("lat", lat); 
            bundle.putString("category", category);
            i.putExtras(bundle); 
            startActivity(i); 
            break; 
            
            case 1: Intent i1 = new Intent(nearby_places.this , touristAttraction_Activity.class); 
            startActivity(i1); 
            break;
            
            case 2: Intent i2 = new Intent(nearby_places.this , _nearby2.class); 
            category = "shopping_mall";
            Bundle bundle2 = new Bundle(); 
            
            bundle2.putString("category", category);
            i2.putExtras(bundle2); 
            startActivity(i2); 
            break;
            
            case 3: Intent i3 = new Intent(nearby_places.this , transportation_Activity.class); 
            category = "Hotel";
            Bundle bundle3 = new Bundle();
            
            bundle3.putDouble("log", log); 
            bundle3.putDouble("lat", lat); 
            
            bundle3.putString("category", category);
            i3.putExtras(bundle3); 
            startActivity(i3); 
            break;
            
            case 4: Intent i4 = new Intent(nearby_places.this , transportation_Activity.class); 
            startActivity(i4); 
            break;
            
            default: //nothing
               }
   			
            }
          });
        
	}
	
	private class MyLocationListener implements LocationListener{
    	
   	 public void onLocationChanged(Location location) {
   		 
   		             String message = String.format(
   		 
   		                     "New Location \n Longitude: %1$s \n Latitude: %2$s",
   		 
   		                     location.getLongitude(), location.getLatitude()
   		 
   		             );
   		 
   		             Toast.makeText(nearby_places.this, message, Toast.LENGTH_LONG).show();
   		 
   		         }
   		 
   		         public void onStatusChanged(String s, int i, Bundle b) {
   		 
   		             Toast.makeText(nearby_places.this, "Provider status changed",
   		 
   		                     Toast.LENGTH_LONG).show();
   		 
   		         }
   		 
   		         public void onProviderDisabled(String s) {
   		 
   		             Toast.makeText(nearby_places.this,
   		 
   		                     "Provider disabled by the user. GPS turned off",
   		
   		                     Toast.LENGTH_LONG).show();
   		 
   		         }
   		
   		         public void onProviderEnabled(String s) {
   		
   		             Toast.makeText(nearby_places.this,
   		
   		                     "Provider enabled by the user. GPS turned on",
   		
   		                     Toast.LENGTH_LONG).show();
   		         }
   }
	
}
