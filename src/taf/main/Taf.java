package taf.main;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.view.*;
import android.widget.Button;

import android.widget.TextView;



public class Taf extends Activity {
    /** Called when the activity is first created. */
	
	TextView main_menu_Text;
	Button find_nearby; 
	Button search_places; 
	Button user_place_navigate; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        
        main_menu_Text = (TextView)findViewById(R.id.mm_text); 
        
       find_nearby = (Button)findViewById(R.id.find_nearby);
       search_places = (Button)findViewById(R.id.serach_place);
       user_place_navigate = (Button)findViewById(R.id.navigation);  
  
       find_nearby.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nearbyIntent = new Intent(Taf.this , nearby_places.class); 
				Taf.this.startActivity(nearbyIntent); 
			}
			
			
		});
        
        
        
        search_places.setOnClickListener(new View.OnClickListener() { 	
			@Override 
			public  void onClick(View v1){
				//TODO Auto-generated method stub 
				Intent search_places = new Intent(Taf.this , search_place_Activity.class);
				Taf.this.startActivity(search_places); 
				
			 }}); 
        
        user_place_navigate.setOnClickListener(new View.OnClickListener() { 	
			@Override 
			public  void onClick(View v){
				//TODO Auto-generated method stub 
				Intent I = new Intent(Taf.this , user_finder.class);
				Taf.this.startActivity(I); 
				
			 }}); 
     
    }

	
}