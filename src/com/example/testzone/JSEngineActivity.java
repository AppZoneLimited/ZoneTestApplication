package com.example.testzone;

import java.util.ArrayList;

import com.appzone.zone.orchestra.engine.script.interpeter.JSInterpreterEngine;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;


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
							new AlertDialog.Builder(JSEngineActivity.this).setMessage(arg0).create().show();
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
				showFuncGenerator();
				//execFunction("evaluateValues", "var s = parseInt(a)+parseInt(b)+parseInt(c)+parseInt(d); return s;", "a, b, c, d");
			}
		});

	}
	
	
	private void showFuncGenerator(){
		final AlertDialog.Builder d = new AlertDialog.Builder(this);
		final FrameLayout fm = new FrameLayout(this);
		final LinearLayout.LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		fm.setLayoutParams(lparams);
		d.setView(fm);
		
		d.setTitle("Generate Your Function");
		final AlertDialog alertDialog = d.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View view = inflater.inflate(R.layout.view_function, fm);
		
		final EditText funcName = (EditText)view.findViewById(R.id.funcName);
		final EditText funcBody = (EditText)view.findViewById(R.id.funcBody);
		final EditText funcArg = (EditText)view.findViewById(R.id.funcArg);
		final Button genFunc = (Button)view.findViewById(R.id.genFunc);
		
		genFunc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String funcNameString = funcName.getText().toString().trim();
				final String scriptString = funcBody.getText().toString().trim();
				final String argsString = funcArg.getText().toString().trim();
				if(TextUtils.isEmpty(funcNameString)&&TextUtils.isEmpty(scriptString)&&TextUtils.isEmpty(argsString)){
					Toast.makeText(JSEngineActivity.this, "Missing Values", Toast.LENGTH_SHORT).show();
				}else{
					execFunction(funcNameString, scriptString, argsString);
				}
			}
		});
		
		alertDialog.show();
	}
	
	private void execFunction(final String funcNameString, final String scriptString, final String argsString){
		final JSInterpreterEngine jse = new JSInterpreterEngine(JSEngineActivity.this);
		
		final String funcScript = jse.generateFunction(funcNameString, scriptString, argsString);
		
		final AlertDialog.Builder d = new AlertDialog.Builder(this);
		final FrameLayout fm = new FrameLayout(this);
		final LinearLayout.LayoutParams lparams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		fm.setLayoutParams(lparams);
		d.setView(fm);

		d.setTitle("Execute function");
		final AlertDialog alertDialog = d.create();
		LayoutInflater inflater = alertDialog.getLayoutInflater();
		View view = inflater.inflate(R.layout.view_enter_actual_args, fm);
		
		final TextView generatedFunc = (TextView)view.findViewById(R.id.generatedFunc);
		final EditText argsEditText = (EditText)view.findViewById(R.id.funcRealArgs);
		final Button evalFunc = (Button)view.findViewById(R.id.evalFunc);
		
		generatedFunc.setText(funcScript);
		
		evalFunc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String argsValues = argsEditText.getText().toString();
			
				if(TextUtils.isEmpty(argsValues)){
					Toast.makeText(JSEngineActivity.this, "Empty arguments", Toast.LENGTH_SHORT).show();
				}else{
					ArrayList<Object> argsArray = JSInterpreterEngine.generateArrayList(argsValues);
					Object[] argsObject = jse.generateArgs(argsArray.size(), argsArray);
					if(!TextUtils.isEmpty(funcScript)){
						jse.evaluateScript().callFunction(funcScript, new JsCallback() {

							@Override
							public void onResult(String arg0) {
								// TODO Auto-generated method stub
								new AlertDialog.Builder(JSEngineActivity.this).setMessage(arg0).create().show();;
							}
						}, funcNameString, argsObject);

					}else{
						new AlertDialog.Builder(JSEngineActivity.this).setMessage("Cannot evaluate empty string").create().show();;
					}
				}
			}
		});
		
		alertDialog.show();
		
		
	}

}
