package taf.main;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import taf.android.service.GeoCoder;
import taf.main.lbs.model.GeoCodeResult;

public class user_finder extends Activity{
	
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
	
	private GeoCoder geoCoder = new GeoCoder();
	
	protected LocationManager locationManager;
	protected Location currentLocation;
	
	protected Button retrieveLocationButton;
	protected Button reverseGeocodingButton;
	protected Button show_on_the_map; 
	protected TextView Address_Text; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_finder_layout);
        
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        retrieveLocationButton = (Button) findViewById(R.id.retrieve_location_button);
        reverseGeocodingButton = (Button) findViewById(R.id.reverse_geocoding_button);
        show_on_the_map = (Button)findViewById(R.id.map_button);
        Address_Text = (TextView)findViewById(R.id.address_text); 
        
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
       
        
        locationManager.requestLocationUpdates(
        		LocationManager.GPS_PROVIDER, 
        		MINIMUM_TIME_BETWEEN_UPDATES, 
        		MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
        		new MyLocationListener()
        );
        
        
        
		retrieveLocationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showCurrentLocation();
			}
		});
		
		reverseGeocodingButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {				
				performReverseGeocodingInBackground();
				
			}
		});
        
		
		
		show_on_the_map.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {				
				try {
                    // Perform action on click
                    double log = getLog(); 
                    double lan = getLat ();
                    String _lat=getString(log); 
                    String _lon=getString(lan); 
                    
                    Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0"));
                    startActivity(geoIntent);
                } catch (Exception e) {

                    AlertDialog ad = adb.create();
                    ad.setMessage("Failed to Launch");
                    ad.show();

                }
            }

			private String getString(double log) {
				// TODO Auto-generated method stub
				return null;
			}
        });
    }    

	protected void performReverseGeocodingInBackground() {
		showCurrentLocation();
		new ReverseGeocodeLookupTask().execute((Void[])null);
	}

	protected void showCurrentLocation() {

		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (currentLocation != null) {
			String message = String.format(
					"Current Location \n Longitude: %1$s \n Latitude: %2$s",
					currentLocation.getLongitude(), currentLocation.getLatitude()
			);
			Toast.makeText(user_finder.this, message,
					Toast.LENGTH_LONG).show();
		}

	}   
	
	public double getLat (){
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double lat = 0.0;
		if (currentLocation != null) {
			lat = currentLocation.getLatitude();
		}
			
		return currentLocation.getLatitude(); 
	} 
	
	
	public double getLog (){
		currentLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		double log = 0.0;
		if (currentLocation != null) {
			log = currentLocation.getLongitude();
		}
			
		return currentLocation.getLongitude(); 
	} 

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude()
			);
			Toast.makeText(user_finder.this, message, Toast.LENGTH_LONG).show();
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			Toast.makeText(user_finder.this, "Provider status changed",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(user_finder.this,
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show();
		}

		public void onProviderEnabled(String s) {
			Toast.makeText(user_finder.this,
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}
	
	public class ReverseGeocodeLookupTask extends AsyncTask <Void, Void, GeoCodeResult> {
		
		private ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			this.progressDialog = ProgressDialog.show(
					user_finder.this,
					"Please wait...contacting Yahoo!", // title
					"Requesting reverse geocode lookup", // message
					true // indeterminate
			);
		}

		@Override
		protected GeoCodeResult doInBackground(Void... params) {
			return geoCoder.reverseGeoCode(currentLocation.getLatitude(), currentLocation.getLongitude());
		}

		@Override
		protected void onPostExecute(GeoCodeResult result) {
			this.progressDialog.cancel();
			Toast.makeText(user_finder.this, result.toString(), Toast.LENGTH_LONG).show();	
			Address_Text.setText(result.toString());
		}
		
	}

}