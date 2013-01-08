package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmi);
		
		// listen for button clicks
		Button button = (Button) findViewById(R.id.submit);
		button.setOnClickListener(calcBMI);
	}

	private OnClickListener calcBMI = new OnClickListener(){
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");
			
			EditText fieldHeight = (EditText) findViewById(R.id.height);
			EditText fieldWeight = (EditText) findViewById(R.id.weight);
			double height = Double.parseDouble(fieldHeight.getText().toString())/100;
			double weight = Double.parseDouble(fieldWeight.getText().toString());
			double BMI = weight / (height * height);
			
			TextView result = (TextView) findViewById(R.id.result);
			result.setText("Your BMI is " + nf.format(BMI));
			
			// give the health advice
			TextView fieldSuggest = (TextView) findViewById(R.id.suggest);
			if (BMI > 25) {
				fieldSuggest.setText(R.string.advice_heavy);
			} else if (BMI < 20) {
				fieldSuggest.setText(R.string.advice_light);
			} else {
				fieldSuggest.setText(R.string.advice_average);
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bmi, menu);
		return true;
	}

}
