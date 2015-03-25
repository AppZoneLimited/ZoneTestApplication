package com.example.testzone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.json.JSONObject;

import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;
import com.appzone.zone.orchestra.engine.interfaces.StepResultCallback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class FlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);

		MobileFlow mf = new MobileFlow(loadJson(this)); // Create flow object
														// using json
		// from file. An alternative
		// would be new MobileFlow(data)
		// Where data is a string;
		StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
		// of the steps
		// abstraction which
		// handles steps
		try {
			
			sa.setStepResultCallBack(new StepResultCallback() {
				
				@Override
				public void onStepResult(Step step, JSONObject result) {
					// TODO Auto-generated method stub
					Log.e("StepId", step.getStepId());
					if (result == null) {
						Log.e("Null", result + "");
					} else {
						Log.e("Not Null", result.toString());
					}
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * This is a method for loading string from text file
	 */
	private String loadJson(Context ctx) {
		String json = null;
		InputStream is = ctx.getResources().openRawResource(R.raw.dejavujson);
		Writer writer = new StringWriter();
		char[] buffer = new char[1024];
		Reader reader = null;
		try {

			try {
				reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int n;
			try {
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		json = writer.toString();
		return json;
	}

}
