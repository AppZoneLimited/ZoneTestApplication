package com.example.testzone;

import java.util.Arrays;

import com.appzone.zone.orchestra.engine.MobileFlow;
import com.appzone.zone.orchestra.engine.datatypes.Step;
import com.appzone.zone.orchestra.engine.datatypes.StepsAbstraction;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class FlowActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flow);
		MobileFlow mf = new MobileFlow(this);

		StepsAbstraction sa = mf.getstepAbstractionion(); // Returns an object
															// of the steps
															// abstraction which
															// handles steps

		String initialStepId = sa.getInitialStep().getStepId();

		Step nextStep = sa.getNextStep();

		String[] stepsId = new String[sa.noOfSteps()];

		stepsId[0] = initialStepId;

		int i = 1;
		while (nextStep != null) {
			stepsId[i] = nextStep.getStepId();
			i++;
			nextStep = sa.getNextStep();

		}

		new AlertDialog.Builder(this).setTitle("Flow Parsed Successfully")
				.setMessage("Step Ids ->\n" + Arrays.asList(stepsId).toString()).create()
				.show();
		;

	}

}
