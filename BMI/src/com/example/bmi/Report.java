package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Report extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		findViews();
		setListeners();
		showResults();
		
	}
	
	private Button button_back;
	private TextView view_result;
	private TextView view_suggest;
	
	private void findViews(){
		button_back = (Button) findViewById(R.id.back);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);
	}
	
	private void setListeners(){
		button_back.setOnClickListener(backMain);
	}
	
	private Button.OnClickListener backMain = new Button.OnClickListener() {
		public void onClick(View v) {
			// Close this activity.
			Report.this.finish();
		}
	};
	
	private void showResults(){
		DecimalFormat df = new DecimalFormat("0.00");
		
		Bundle bundle = this.getIntent().getExtras();
		double height = Double.parseDouble(bundle.getString("KEY_HEIGHT"))/100;
		double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
		double BMI = weight / (height * height);
		
		view_result.setText("Your BMI is " + df.format(BMI));
		
		// give the health advice
		if (BMI > 25) {
			view_suggest.setText(R.string.advice_heavy);
		} else if (BMI < 20) {
			view_suggest.setText(R.string.advice_light);
		} else {
			view_suggest.setText(R.string.advice_average);
		}
	}
	
}
