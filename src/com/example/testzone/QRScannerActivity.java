package com.example.testzone;

import com.appzone.zone.orchestra.engine.qrcodescanner.QRCodeScannerView;
import com.appzone.zone.orchestra.engine.qrcodescanner.QRCodeScannerView.ResultHandler;
import com.google.zxing.Result;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class QRScannerActivity extends Activity implements ResultHandler {

	QRCodeScannerView mScannerView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mScannerView = new QRCodeScannerView(this);
		setContentView(mScannerView);
	}

	
	
	@Override
	public void onResume() {
		super.onResume();
		mScannerView.setResultHandler(this); // Register activity as a handler scan results.
		mScannerView.startCamera(); // Start camera on resume
	}

	@Override
	public void onPause() {
		super.onPause();
		mScannerView.stopCamera(); // Stop camera on pause
	}

	@Override
	public void handleResult(Result rawResult) {
		// TODO Auto-generated method stub
		
		new AlertDialog.Builder(this)
				.setTitle("Success!!")
				.setMessage(
						"Result : " + rawResult.getText() + "\n\n"
								+ "enCode type : "
								+ rawResult.getBarcodeFormat().toString())
				.create().show();

	}
}
