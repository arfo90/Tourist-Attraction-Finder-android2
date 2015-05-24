package taf.main;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText; 
import android.os.Bundle;


public class search_place_Activity extends Activity{

	
	@Override 
	public void onCreate (Bundle Icicsp){
		super.onCreate(Icicsp); 
		setContentView(R.layout.search_place_layout); 
		
		
		final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        final EditText addressfield = (EditText) findViewById(R.id.address);

        final Button button = (Button) findViewById(R.id.launchmap);
        button.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View v) {
                try {
                    // Perform action on click
                    String address = addressfield.getText().toString();
                    address = address.replace(' ', '+');
                    Intent geoIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address));
                    startActivity(geoIntent);
                } catch (Exception e) {

                    AlertDialog ad = adb.create();
                    ad.setMessage("Failed to Launch");
                    ad.show();

                }
            }
        });

		
	}
	
}
