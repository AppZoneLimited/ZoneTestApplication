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
import com.appzone.zone.orchestra.engine.enums.StepTypeEnum;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;


public class FlowActivity extends Activity {

	final String TAG = this.getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);

		MobileFlow mf = new MobileFlow(loadJson(this));
		// Create flow object
														// using json
		// from file. An alternative
		// would be new MobileFlow(data)
		// Where data is a string;
		StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
		// of the steps
		// abstraction which
		// handles steps
		
		final String resultString = loadResultJson(this);
		
		try {
			//Null is based on the assumption that the result has been set for the each step
			Step initStep = sa.getNextStep();
			
			//Do whatever you want with step here\
			
			//Below log shows the name of commandname in step, get CommandObject with step.getCommandName();
			//Log.e(TAG, initStep.getCommandName().getCommandNameString());
			
			//Below log shows length of sections
			//Log.e(TAG, initStep.getCommandName().getSections().size()+"");
			
			//Dummy result
			final JSONObject result = new JSONObject(resultString);
			
			//Set step result
			initStep.setStepResultCallBack(result, sa, new StepResultCallback() {
				
				@Override
				public void onStepResult(StepsAbstraction stepAbstraction, Step s,
						JSONObject result) {
					// TODO returns current step abstraction, current step and current step result
					//Any manipulation you want can be done here with the objects
					
					//Log.e("StepAttachedCommands", s.getEvents().getAttachedCommands().get(0).getCommandMappingsList().get(0).getJson().toString());
					
					return;
				}
				
				@Override
				public void onGetNextStep(Step nextStep, JSONObject prevStepData, StepTypeEnum e, boolean canrollBack) {
					// TODO Auto-generated method stub
					//Returns next step, you can proceed from here
					//Step nextStepNow = nextStep;
					Log.e("OnGetNextStep2", nextStep.getStepId());
					Log.e("PreviousStepResultFor1", nextStep.getPrevStepResult().toString());
					
					Log.e("PrevStepData", prevStepData.toString());
					
//					HashMap<String, String> sm = new HashMap<>();
//					sm.put("name", "GIRL");
//					
//					JSONObject cresult = new JSONObject(sm);
					
//					nextStep.setStepResultCallBack(cresult, nextStep.getStepAbstract(), new StepResultCallback() {
//						
//						@Override
//						public void onStepResult(StepsAbstraction stepAbstraction, Step s,
//								JSONObject result) {
//							// TODO Auto-generated method stub
//							
//						}
//						
//						@Override
//						public void onGetNextStep(Step nextStep) {
//							// TODO Auto-generated method stub
//							Log.e("OnGetNextStep3", nextStep.getStepId());
//							Log.e("PreviousStepResultFor2", nextStep.getPrevStepResult().toString());
//						}
//					});
					
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
		InputStream is = ctx.getResources().openRawResource(R.raw.walenewjson);
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

	private String loadResultJson(Context ctx) {
		String json = null;
		InputStream is = ctx.getResources().openRawResource(R.raw.result);
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
