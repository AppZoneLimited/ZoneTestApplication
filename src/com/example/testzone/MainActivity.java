package com.example.testzone;

import org.json.JSONException;

import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsEnum;
import com.appzone.zone.orchestra.engine.phonefunctions.PhoneOptionsFactory;
import com.appzone.zone.orchestra.engine.phonefunctions.options.PhoneContacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

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
//		mScannerView.setResultHandler(this); // Register activity as a handler scan results.
//		mScannerView.startCamera(); // Start camera on resume
	}

	@Override
	public void onPause() {
		super.onPause();
		//mScannerView.stopCamera(); // Stop camera on pause
	}

//	@Override
//	public void handleResult(Result rawResult) {
//		// TODO Auto-generated method stub
//		Log.e(TAG, rawResult.getText()); // Prints scan results
//		Log.e(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan
//
//		new AlertDialog.Builder(this)
//				.setTitle("Success!!")
//				.setMessage(
//						"Result : " + rawResult.getText() + "\n"
//								+ "enCode type : "
//								+ rawResult.getBarcodeFormat().toString())
//				.create().show();
//
//	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add("QRCode Scanner").setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "QRCode scanner", Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

}
