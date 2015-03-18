package com.example.testzone;


import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FLowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);
		MobileFlow mf = new MobileFlow(this);
		
		StepsAbstraction sa = mf.getstepAbstractionion(); //Returns an object of the steps abstraction which handles steps
		
		Log.e("Step", sa.getInitialStep().getStepId());
		
		Step nextStep = sa.getNextStep();
		
		while(nextStep != null){
			Log.e("NextStep", nextStep.getStepId());
			nextStep = sa.getNextStep();
		}
		
		
		
	}
	
}
