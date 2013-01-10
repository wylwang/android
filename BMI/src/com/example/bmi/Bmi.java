package com.example.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Bmi extends Activity {

	private static final String Tag = "Bmi";
    public static final String PREF = "BMI_PREF";
    public static final String PREF_HEIGHT = "BMI_Height";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(Tag,"onCreate");
		setContentView(R.layout.activity_bmi);
		findViews();
		restorePrefs();
		setListeners();
	}

	private void restorePrefs() {
		SharedPreferences settings = getSharedPreferences(PREF,0);
		String pref_height = settings.getString(PREF_HEIGHT, "");
		if (!"".equals(pref_height)) {
			field_height.setText(pref_height);
			field_weight.requestFocus();
		}
	}
	
	public void onStart() {
		super.onStart();
		Log.v(Tag,"onStart");
	}
	
	public void onResume() {
		super.onResume();
		Log.v(Tag,"onResume");
	}
	
	public void onPause() {
		super.onPause();
		Log.v(Tag,"onPause");
	}
	
	public void onStop() {
		super.onStop();
		Log.v(Tag,"onStop");
		SharedPreferences settings = getSharedPreferences(PREF,0);
		settings.edit()
		.putString(PREF_HEIGHT, field_height.getText().toString())
		.commit();
	}
	
	public void onRestart() {
		super.onRestart();
		Log.v(Tag,"onRestart");
	}
	
	public void onDestroy() {
		super.onDestroy();
		Log.v(Tag,"onDestroy");
	}
	
	private Button button_calc;
	private EditText field_height;
	private EditText field_weight;
//	private TextView view_result;
//	private TextView view_suggest;
	
	private void findViews(){
		button_calc = (Button) findViewById(R.id.submit);
		field_height = (EditText) findViewById(R.id.height);
		field_weight = (EditText) findViewById(R.id.weight);
//		view_result = (TextView) findViewById(R.id.result);
//		view_suggest = (TextView) findViewById(R.id.suggest);
	}
	
	private void setListeners() {
		// listen for button clicks
		button_calc.setOnClickListener(calcBMI);
	}
	
	private OnClickListener calcBMI = new OnClickListener(){
		public void onClick(View v) {
			//Switch to report page
			Intent intent = new Intent();
			intent.setClass(Bmi.this, Report.class);
			
			Bundle bundle = new Bundle();
			bundle.putString("KEY_HEIGHT", field_height.getText().toString());
			bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
			intent.putExtras(bundle);
			startActivity(intent);
//			DecimalFormat nf = new DecimalFormat("0.00");
//			
//			double height = Double.parseDouble(field_height.getText().toString())/100;
//			double weight = Double.parseDouble(field_weight.getText().toString());
//			double BMI = weight / (height * height);
//			
//			view_result.setText("Your BMI is " + nf.format(BMI));
//			
//			// give the health advice
//			if (BMI > 25) {
//				view_suggest.setText(R.string.advice_heavy);
//			} else if (BMI < 20) {
//				view_suggest.setText(R.string.advice_light);
//			} else {
//				view_suggest.setText(R.string.advice_average);
//			}
//			
//			openOptionsDialog();
			
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
