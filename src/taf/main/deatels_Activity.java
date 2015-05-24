package taf.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class deatels_Activity extends Activity {


	String _title = null; 
	String _address = null; 
	
	//final AlertDialog.Builder adb = new AlertDialog.Builder(this);
	
    @Override
	public void onCreate (Bundle Icidet){
		super.onCreate(Icidet); 
		setContentView(R.layout.details_layout);
		
		Bundle bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String Address = bundle.getString("address");
        String Refrence = bundle.getString("refrenc"); 
        
		TextView titleTV = (TextView)findViewById(R.id.title1_text);
		TextView addressTV = (TextView)findViewById(R.id.address1_text);
		
		Button mapBT = (Button)findViewById(R.id.map_poi_button);
		
		
		titleTV.setText("Name:" + title);
		addressTV.setText("Address:" +  Address);
	
		
		_title = title;
		_address = Address;
		
	mapBT.setOnClickListener(new OnClickListener() {			
		@Override
		public void onClick(View v) {				
			
			try {
                // Perform action on click
                String address = _title+ "," +_address;
                //address = address.replace(' ', '+');
                Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                startActivity(geoIntent);
            } catch (Exception e) {

                //AlertDialog ad = adb.create();
               // ad.setMessage("Failed to Launch");
                //ad.show();

            } 
		}
	});
	
	
	
	}
	
}
