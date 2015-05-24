package taf.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class transportation_Activity extends Activity{

	String category; 
	ListView lv; 
	TextView selection; 
	
	
	String Category; 
	
	@Override 
	public void onCreate(Bundle Icicnp) {
        super.onCreate(Icicnp);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.nearby_layout);
        
        
       
        
        lv =(ListView)findViewById(R.id.nb_listview); 
        
        final String[] items = getResources().getStringArray(R.array.transportation_cat);
        
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
            case 0: Intent i = new Intent(transportation_Activity.this , _nearby2.class); 
            category = "bus_station";
            Bundle bundle = new Bundle();
            
            bundle.putString("category", category);
            i.putExtras(bundle); 
            startActivity(i); 
            break; 
            
            case 1: Intent i1 = new Intent(transportation_Activity.this , _nearby2.class); 
            category = "taxi_stand";
            Bundle bundle1 = new Bundle();
            bundle1.putString("category", category);
            i1.putExtras(bundle1); 
            startActivity(i1); 
            break;
            
            case 2: Intent i2 = new Intent(transportation_Activity.this , _nearby2.class); 
            category = "subway_station";
            Bundle bundle2 = new Bundle();
            
            bundle2.putString("category", category);
            i2.putExtras(bundle2); 
            startActivity(i2); 
            break;
           
            
            default: //nothing
               }
   			
            }
          });
        
	}
	
	
}
