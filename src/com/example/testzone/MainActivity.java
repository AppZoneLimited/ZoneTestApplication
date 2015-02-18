package com.example.testzone;

import org.json.JSONException;

import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsEnum;
import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsFactory;
import com.appzone.zone.orchestra.engine.phonefunctions.options.PhoneContacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;


public class MainActivity extends Activity{


	//private String TAG = MainActivity.class.getSimpleName();
	
	TextView s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //Set contentview with instance
		
		PhoneContacts p = (PhoneContacts)new PhoneOptionsFactory(this).createPhoneOptionObject(PhoneOptionsEnum.PHONE_CONTACTS);
		s = ((TextView)findViewById(R.id.textView));
		try {
			s.setText("Phone Contacts :\n\n"+p.getContactsJSONArray().toString(4));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("QRCode Scanner").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, QRScannerActivity.class));
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

}
