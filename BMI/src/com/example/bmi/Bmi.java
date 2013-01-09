package com.example.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
		findViews();
		setListeners();
	}

	private Button button_calc;
	private EditText field_height;
	private EditText field_weight;
	private TextView view_result;
	private TextView view_suggest;
	
	private void findViews(){
		button_calc = (Button) findViewById(R.id.submit);
		field_height = (EditText) findViewById(R.id.height);
		field_weight = (EditText) findViewById(R.id.weight);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);
	}
	
	private void setListeners() {
		// listen for button clicks
		button_calc.setOnClickListener(calcBMI);
	}
	
	private OnClickListener calcBMI = new OnClickListener(){
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");
			
			double height = Double.parseDouble(field_height.getText().toString())/100;
			double weight = Double.parseDouble(field_weight.getText().toString());
			double BMI = weight / (height * height);
			
			view_result.setText("Your BMI is " + nf.format(BMI));
			
			// give the health advice
			if (BMI > 25) {
				view_suggest.setText(R.string.advice_heavy);
			} else if (BMI < 20) {
				view_suggest.setText(R.string.advice_light);
			} else {
				view_suggest.setText(R.string.advice_average);
			}
			
			openOptionsDialog();
			
		}
				
	};

	private void openOptionsDialog() {
//		Toast popup = Toast.makeText(Bmi.this, R.string.about_title, Toast.LENGTH_SHORT);
//		popup.show();
		AlertDialog.Builder dialog = new AlertDialog.Builder(Bmi.this);
		dialog.setTitle(R.string.about_title);
		dialog.setMessage(R.string.about_msg);
		dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
		dialog.setNegativeButton(R.string.homepage_label, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse(getString(R.string.homepage_uri));
				Intent intent = new Intent(Intent.ACTION_VIEW,uri);
				startActivity(intent);
			}
		});
		dialog.show();
	}
	
	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_QUIT = MENU_ABOUT + 1;
	protected static final int MENU_SETTINGS = MENU_QUIT + 1;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_bmi, menu);
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, R.string.menu_about);
		menu.add(0, MENU_QUIT, 0, R.string.menu_quit);
		menu.add(0, MENU_SETTINGS, 0, R.string.menu_settings);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ABOUT:
			openOptionsDialog();
			break;
		case MENU_QUIT:
			finish();
			break;
		case MENU_SETTINGS:
			break;
		}
		return true;
	}
	
}
