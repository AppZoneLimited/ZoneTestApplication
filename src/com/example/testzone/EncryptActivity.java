package com.example.testzone;

import com.appzone.zone.orchestra.engine.script.encryption.modules.EncryptionAlgorithms;
import com.appzone.zone.orchestra.engine.script.encryption.modules.EncryptorHelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EncryptActivity extends Activity {

	EditText toEncrypt, encrypted;
	Button encryptButton, decryptButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encrypt);

		toEncrypt = (EditText)findViewById(R.id.toEncrypt);
		encrypted = (EditText)findViewById(R.id.encrypted);

		toEncrypt.setHint("Enter String to be encrypted!");
		encrypted.setHint("Encrypted String");
		
		encryptButton = (Button)findViewById(R.id.encryptButton);
		decryptButton = (Button)findViewById(R.id.decryptButton);
		
		encryptButton.setText("Encrypt");
		decryptButton.setText("Decrypt");

		encryptButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String text = toEncrypt.getText().toString().trim();
				toEncrypt.setText("");
				helpEncrptoClass(text);
			}
		});

		decryptButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String text = encrypted.getText().toString().trim();
				encrypted.setText("");
				decryptHelpEncrptoClass(text);
			}

		});
	}

	int selected = -1;

	void helpEncrptoClass(String text){
		final String toEncryptStr = text;
		String[] s = {"AES", "SHA-256", "MD5"};
		new AlertDialog.Builder(this).setTitle("Select Encryption/Hashing Algorithm").setItems(s, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selected = which;
				switch (selected) {
				case 0:
					Toast.makeText(EncryptActivity.this, "AES Selected!", Toast.LENGTH_SHORT).show();
					encrypted.setText(EncryptorHelper.encryptString(toEncryptStr, EncryptionAlgorithms.AES, null, "appzonekey"));
					break;
				case 1:
					Toast.makeText(EncryptActivity.this, "SHA-256 Selected!", Toast.LENGTH_SHORT).show();
					encrypted.setText(EncryptorHelper.encryptString(toEncryptStr, EncryptionAlgorithms.SHA_256, null, null));
					break;
				case 2:
					Toast.makeText(EncryptActivity.this, "MD5 Selected!", Toast.LENGTH_SHORT).show();
					encrypted.setText(EncryptorHelper.encryptString(toEncryptStr, EncryptionAlgorithms.MD5, null, null));
					break;
				}

			}
		}).create().show();


	}
	
	void decryptHelpEncrptoClass(String text){
		final String toDecryptStr = text;
		String[] s = {"AES", };
		new AlertDialog.Builder(this).setTitle("Select Decryption Algorithm").setItems(s, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				selected = which;
				switch (selected) {
				case 0:
					Toast.makeText(EncryptActivity.this, "AES Selected!", Toast.LENGTH_SHORT).show();
					encrypted.setText("Decrypted :\n----------\n" + EncryptorHelper.decryptString(toDecryptStr, EncryptionAlgorithms.AES, null, "appzonekey"));
					break;
				}

			}
		}).create().show();


	}



}
