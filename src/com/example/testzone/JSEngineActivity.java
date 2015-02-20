package com.example.testzone;

import java.util.ArrayList;

import com.appzone.zone.orchestra.engine.script.interpeter.JSInterpreterEngine;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class JSEngineActivity extends Activity {

	Button eval, evalfunc;
	EditText script;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jsengine);

		eval = (Button)this.findViewById(R.id.eval);
		evalfunc = (Button)this.findViewById(R.id.evalfunc);
		script = (EditText)this.findViewById(R.id.script);

		eval.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String scriptStr = script.getText().toString();

				if(!TextUtils.isEmpty(scriptStr)){
					JSInterpreterEngine jsEngine = new JSInterpreterEngine(JSEngineActivity.this);
					jsEngine.evaluateScript().evaluate(scriptStr, new JsCallback() {

						@Override
						public void onResult(String arg0) {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(JSEngineActivity.this).setMessage(arg0).create().show();;
						}
					});

				}else{
					new AlertDialog.Builder(JSEngineActivity.this).setMessage("Cannot evaluate empty string").create().show();;
				}

			}
		});

		evalfunc.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//String scriptStr = "function myFunction(a, b){ return a * b; };";
				
				JSInterpreterEngine jse = new JSInterpreterEngine(JSEngineActivity.this);
				
				ArrayList<Object> argsArray = new ArrayList<Object>();
				argsArray.add("4");
				argsArray.add("4");
				argsArray.add("4");
				argsArray.add("4");

				String funcName = "evaluateValues";
				String execScript = "return parseInt(a)+parseInt(b)+parseInt(c)+parseInt(d);";
				String args = "a, b, c, d";
				
				Object[] m = jse.generateArgs(argsArray.size(), argsArray);
				
				String funcScript = jse.generateFunction(funcName, execScript, args);
				
				if(!TextUtils.isEmpty(funcScript)){
					jse.evaluateScript().callFunction(funcScript, new JsCallback() {

						@Override
						public void onResult(String arg0) {
							// TODO Auto-generated method stub
							new AlertDialog.Builder(JSEngineActivity.this).setMessage(arg0).create().show();;
						}
					}, funcName, m);

				}else{
					new AlertDialog.Builder(JSEngineActivity.this).setMessage("Cannot evaluate empty string").create().show();;
				}

			}
		});

	}

}
