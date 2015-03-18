package com.example.testzone;

import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsEnum;
import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsFactory;
import com.appzone.zone.orchestra.engine.phonefunctions.options.PhoneContacts;
import com.example.testzone.adapters.ContactsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.ListView;


public class MainActivity extends Activity{


	//private String TAG = MainActivity.class.getSimpleName();

	ListView contactsList;
	ContactsAdapter dualAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contactsList = (ListView)findViewById(R.id.contacts);
		PhoneContacts p = (PhoneContacts)new PhoneOptionsFactory(this).createPhoneOptionObject(PhoneOptionsEnum.PHONE_CONTACTS);
		dualAdapter = new ContactsAdapter(this, R.layout.contacts_item, p.getContactsArray());
		contactsList.setAdapter(dualAdapter);
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

		menu.add("JS Engine").setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, JSEngineActivity.class));
				return false;
			}
		});

		menu.add("Encryption Module").setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, EncryptActivity.class));
				return false;
			}
		});
		
		menu.add("Flow Engine").setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this, FLowActivity.class));
				return false;
			}
		});
		
		return super.onCreateOptionsMenu(menu);
	}

}
