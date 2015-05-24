package taf.main;

//import junit.framework.Protectable;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.MapActivity;


import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class navigating_user extends MapActivity {

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; //in meters
	private static final long MiNIMUM_TIME_BETWEEN_UPDATES =1000; // in Milliseconds
	
	protected LocationManager locationManager;
	
	protected Button retriveLocationButton; 
	
	protected boolean isRouteDisplayed() {
		return false;
		}
	
	@Override 
	public void onCreate(Bundle icicnu){
		super.onCreate(icicnu); 
		super.setContentView(R.layout.mapview_for_userfinder); 
		
		retriveLocationButton = (Button)findViewById(R.id.retrieve_location_button);
		
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER,
					MiNIMUM_TIME_BETWEEN_UPDATES,
					MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, 
					new MyLocationListener()
					
			);
		
		retriveLocationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showCurrentLocation(); 
			}
		});	
		
	}
	
	
	protected void showCurrentLocation(){
		
		Location location = 
			locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		if (location != null){
			 
			double lat = location.getLatitude();
			double lng = location.getLongitude();
			Geocoder gc = new Geocoder(this, Locale.getDefault());
			
			String addressString = "No address found";
			
			try {
				List<Address> addresses =gc.getFromLocation(lat, lng, 1);
				StringBuilder sb = new StringBuilder();
				  if (addresses.size() > 0){
					  Address address = addresses.get(0);
					  
					  for (int i = 0; i<address.getMaxAddressLineIndex(); i++){
						  
						  sb.append(address.getAddressLine(i)).append("\n");
						  
						  sb.append(address.getLocality()).append("\n");
						  sb.append(address.getPostalCode()).append("\n");
						  sb.append(address.getCountryName());
					  }
					  addressString = sb.toString(); 
				  } 
				  }
				  catch(IOException e){}
				  
				  
			String message = String.format(
			 "Current Location \n Longitude: %1$s \n Latitude: %2$s"+"\n"+addressString,
			 location.getLongitude(), location.getLatitude());
			
			Toast.makeText(navigating_user.this, message, 
					Toast.LENGTH_LONG).show();
		}
		
		}
	
	
	private  class MyLocationListener implements LocationListener {
		
		public void onLocationChanged(Location location){
			String message = String.format("Current Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
			
			Toast.makeText(navigating_user.this, message, 
					Toast.LENGTH_LONG).show();
			
		}
		
		public void onStatusChanged (String s, int i, Bundle b){
			Toast.makeText(navigating_user.this, "Provider status changed", 
				   	Toast.LENGTH_LONG).show(); 
		}
		
		
		public void onProviderDisabled(String s){
			Toast.makeText(navigating_user.this, 
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show(); 
		}
		
		
		public void onProviderEnabled(String s){
			Toast.makeText(navigating_user.this, 
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show(); 
			
		   }

		
		
		}
		
		
	}

