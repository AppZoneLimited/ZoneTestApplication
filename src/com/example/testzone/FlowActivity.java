package com.example.testzone;

import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class FlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);

		MobileFlow mf = new MobileFlow(this); // Create flow object using json
												// from file. An alternative
												// would be new MobileFlow(data)
												// Where data is a string;
		StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
															// of the steps
															// abstraction which
															// handles steps

		Step initialStep = sa.getInitialStep();

		try {
			Log.e("StepId : " + initialStep.getStepId() + " > Command :",
					initialStep.getCommands().toString(2) + "");
			Log.e("StepId : " + initialStep.getStepId() + " > Data :",
					initialStep.getData().toString(2) + "");

			Step nextStep = sa.getNextStep();

			while (nextStep != null) {
				Log.e("StepId : " + nextStep.getStepId() + " > Command :",
						nextStep.getCommands().toString(2) + "");
				Log.e("StepId : " + nextStep.getStepId() + " > Data :",
						nextStep.getData().toString(2) + "");
				nextStep = sa.getNextStep();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
